package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G5_21608_����ʵ��б� {
    //�ùķ��̼�

    /*
    �����ϴ�? : |r1-r2| + |c1-c2| = 1
    1. ����ִ� ĭ �߿��� �����ϴ� �л��� ������ ĭ�� ���� ���� ĭ���� �ڸ��� ���Ѵ�.
    2. 1�� �����ϴ� ĭ�� ���� ���̸�, ������ ĭ �߿��� ����ִ� ĭ�� ���� ���� ĭ���� �ڸ��� ���Ѵ�.
    3. 2�� �����ϴ� ĭ�� ���� ���� ��쿡�� ���� ��ȣ�� ���� ���� ĭ����,
        �׷��� ĭ�� ���� ���̸� ���� ��ȣ�� ���� ���� ĭ���� �ڸ��� ���Ѵ�.
     */

    static int N;  //N*N�� �ڸ�
    static int M;  //�л� ��
    static int[][] seats;
    static ArrayList<Integer>[] favorits;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Queue<Seat> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = N * N;  //�л� ��

        seats = new int[N + 1][N + 1];  //N*N�迭�� ���� �ɾҴ��� ������ int�� �迭
        favorits = new ArrayList[M+1];  //�� �л��� �����ϴ� �л��� list

        for(int i=1;i<=M;i++){
            favorits[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            queue = new PriorityQueue<>();
            st = new StringTokenizer(br.readLine());
            int me = Integer.parseInt(st.nextToken());
            int f1 = Integer.parseInt(st.nextToken());
            int f2 = Integer.parseInt(st.nextToken());
            int f3 = Integer.parseInt(st.nextToken());
            int f4 = Integer.parseInt(st.nextToken());

            //me�� favorits����Ʈ�� �����ϴ� ģ�� 4�� �߰�
            favorits[me].add(f1);
            favorits[me].add(f2);
            favorits[me].add(f3);
            favorits[me].add(f4);


            favoritCount(f1, f2, f3, f4);

//            System.out.println(queue);


            while (!queue.isEmpty()) {  //�ڸ��� ������ �� ���� queue�� ��ȸ
                Seat cur = queue.poll();  //�� �տ� �ִ°� �̾Ƽ� (���ǿ� ���� �´� �ڸ�)
                if (seats[cur.r][cur.c] == 0) {  //�� �ڸ��� ���������
                    seats[cur.r][cur.c] = me;  //�� �ڸ��� ���� (seats�� ����)
                    break;  //�ڸ� �����ߴٸ� while�� break
                }
            }
        }

//        for (int i = 1; i <= N; i++) {
//            for (int j = 1; j <= N; j++) {
//                System.out.print(seats[i][j] + " ");
//            }
//            System.out.println();
//        }

        System.out.println(satisfy());  //�� �л��� �������� �� ���

    }

    private static int satisfy(){
        int result = 0;

        for(int r=1;r<=N;r++){
            for(int c=1;c<=N;c++){
                int who = seats[r][c];  //seats[r][c]�� �ɾ��ִ� �л�
                int cnt = 0;  //������ �ֺ��� �����ϴ� �л��� ��

                for(int d=0;d<4;d++){
                    int nr = r+dr[d];
                    int nc = c+dc[d];

                    if(!isIn(nr, nc)) continue;
                    if(favorits[who].contains(seats[nr][nc])){  //who�� ģ��ģ�� ����Ʈ�� seats[nr][nc]�� �ɾ��ִ� ģ���� �ִٸ�
                        cnt++;  //cnt�� ����
                    }
                }

                //���ǿ� �°� ������ ����
                if(cnt==0) result += 0;
                else if(cnt==1) result += 1;
                else if(cnt==2) result += 10;
                else if(cnt==3) result += 100;
                else if(cnt==4) result += 1000;

            }
        }


        return result;
    }

    /**
     * n*n �迭�� ��� ���� �� ��ġ �ֺ��� �����ϴ� ģ���� ������ �ֺ��� �� �ڸ��� ������ �켱����ť�� ����
     * @param f1
     * @param f2
     * @param f3
     * @param f4
     */
    private static void favoritCount(int f1, int f2, int f3, int f4) {
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                int like = 0;
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (!isIn(nr, nc)) continue;
                    if (seats[nr][nc] == f1 || seats[nr][nc] == f2 || seats[nr][nc] == f3 || seats[nr][nc] == f4) {
                        //r, c��ġ���� seats[nr][nc]�� ģ�� ģ���� ������ like++
                        like++;
                    }
                }
                //seats[r][c]
//                queue.add(new Seat(r, c, like, 0));
                queue.add(new Seat(r, c, like, emptyCount(r, c)));  //�켱����ť�� �� ��ġ, like�� ��, empty�� ���� ����
            }
        }
    }

    /**
     * seats[r][c]��ġ �ֺ��� �� ĭ�� �� ������ ��ȯ�ϴ� int�� �޼ҵ�
     * @param r
     * @param c
     * @return
     */
    private static int emptyCount(int r, int c) {
        int count = 0;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (!isIn(nr, nc)) continue;
            if (seats[nr][nc] == 0) count++;
        }

        return count;
    }

//    private static void emptyCount() {
//        for (int r = 1; r <= N; r++) {
//            for (int c = 1; c <= N; c++) {
//                int empty = 0;
//                for (int d = 0; d < 4; d++) {
//                    int nr = r + dr[d];
//                    int nc = c + dc[d];
//
//                    if (!isIn(nr, nc)) continue;
//                    if (seats[nr][nc] == 0) empty++;
//                }
//                for (Seat q : queue) {
//                    if (q.r == r && q.c == c) {
//                        q.empty = empty;
//                    }
//                }
//            }
//        }
//    }


    private static boolean isIn(int r, int c) {
        return r > 0 && c > 0 && r <= N && c <= N;
    }

    private static class Seat implements Comparable<Seat> {
        int r;
        int c;
        int like;
        int empty;

        public Seat(int r, int c, int like, int empty) {
            this.r = r;
            this.c = c;
            this.like = like;
            this.empty = empty;
        }

        @Override
        public int compareTo(Seat o) {
            if (like == o.like) {
                if (empty == o.empty) {
                    if (r == o.r) {
                        return c - o.c;  // 4) like�� ���� empty�� ���� ���� ������ -> ���� ���� ������ ��������
                    } else {
                        return r - o.r;  // 3) like�� ���� empty�� ������ -> ���� ���� ������ ��������
                    }
                } else {
                    return (empty - o.empty) * -1;  // 2) like�� ������ empty�� ū ������ ��������
                }
            } else {
                return (like - o.like) * -1;  // 1) like�� ū ������ ��������
            }
        }

        @Override
        public String toString() {
            return "Seat{" +
                    "r=" + r +
                    ", c=" + c +
                    ", like=" + like +
                    ", empty=" + empty +
                    '}';
        }
    }
}

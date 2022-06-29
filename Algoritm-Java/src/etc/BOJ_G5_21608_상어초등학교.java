package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G5_21608_상어초등학교 {
    //시뮬레이션

    /*
    인접하다? : |r1-r2| + |c1-c2| = 1
    1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
    2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
    3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로,
        그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
     */

    static int N;  //N*N의 자리
    static int M;  //학생 수
    static int[][] seats;
    static ArrayList<Integer>[] favorits;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Queue<Seat> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = N * N;  //학생 수

        seats = new int[N + 1][N + 1];  //N*N배열에 누가 앉았는지 저장할 int형 배열
        favorits = new ArrayList[M+1];  //각 학생의 좋아하는 학생들 list

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

            //me의 favorits리스트에 좋아하는 친구 4명 추가
            favorits[me].add(f1);
            favorits[me].add(f2);
            favorits[me].add(f3);
            favorits[me].add(f4);


            favoritCount(f1, f2, f3, f4);

//            System.out.println(queue);


            while (!queue.isEmpty()) {  //자리를 차지할 때 까지 queue를 조회
                Seat cur = queue.poll();  //젤 앞에 있는거 뽑아서 (조건에 가장 맞는 자리)
                if (seats[cur.r][cur.c] == 0) {  //그 자리가 비어있으면
                    seats[cur.r][cur.c] = me;  //그 자리를 차지 (seats에 저장)
                    break;  //자리 차지했다면 while문 break
                }
            }
        }

//        for (int i = 1; i <= N; i++) {
//            for (int j = 1; j <= N; j++) {
//                System.out.print(seats[i][j] + " ");
//            }
//            System.out.println();
//        }

        System.out.println(satisfy());  //각 학생의 만족도의 합 출력

    }

    private static int satisfy(){
        int result = 0;

        for(int r=1;r<=N;r++){
            for(int c=1;c<=N;c++){
                int who = seats[r][c];  //seats[r][c]에 앉아있는 학생
                int cnt = 0;  //인접한 주변에 좋아하는 학생의 수

                for(int d=0;d<4;d++){
                    int nr = r+dr[d];
                    int nc = c+dc[d];

                    if(!isIn(nr, nc)) continue;
                    if(favorits[who].contains(seats[nr][nc])){  //who의 친한친구 리스트에 seats[nr][nc]에 앉아있는 친구가 있다면
                        cnt++;  //cnt를 증가
                    }
                }

                //조건에 맞게 만족도 증가
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
     * n*n 배열을 모두 돌며 각 위치 주변에 좋아하는 친구의 개수와 주변에 빈 자리의 개수를 우선순위큐에 저장
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
                        //r, c위치에서 seats[nr][nc]중 친한 친구가 있으면 like++
                        like++;
                    }
                }
                //seats[r][c]
//                queue.add(new Seat(r, c, like, 0));
                queue.add(new Seat(r, c, like, emptyCount(r, c)));  //우선순위큐에 각 위치, like의 수, empty의 수를 저장
            }
        }
    }

    /**
     * seats[r][c]위치 주변에 빈 칸이 몇 개인지 반환하는 int형 메소드
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
                        return c - o.c;  // 4) like가 같고 empty가 같고 행이 같으면 -> 열이 작은 순으로 오름차순
                    } else {
                        return r - o.r;  // 3) like가 같고 empty가 같으면 -> 행이 작은 순으로 오름차순
                    }
                } else {
                    return (empty - o.empty) * -1;  // 2) like가 같으면 empty가 큰 순으로 내림차순
                }
            } else {
                return (like - o.like) * -1;  // 1) like가 큰 순으로 내림차순
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

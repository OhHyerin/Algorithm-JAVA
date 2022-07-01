package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G4_20056_������������̾ {
    //�ù�

    static int N;
    static int M;
    static int K;
    static List<Pos>[][] map;
    static List<Pos> list = new ArrayList<>();
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

//            map[r][c].add(new Pos(r, c, m, s, d));  //map�� ����
            list.add(new Pos(r, c, m, d, s));  //fireball����Ʈ�� ���� ����
        }

        for (int i = 0; i < K; i++) {  //�̵��� K�� �Ѵ�.
            //��� ���̾�� �ڽ��� �������� �ӷ¸�ŭ �̵��Ѵ�
//            System.out.println(list);
            move();
            //2�� �̻��� ���̾�� �ִ� ĭ �˻�
            check();
        }

        int result = 0;
        for (Pos cur : list) {
            result += cur.m;
        }

        System.out.println(result);


    }

    static void move() {
        for (Pos cur : list) {
            int nr = (cur.r+N+dr[cur.d]*(cur.s%N))%N;
            int nc = (cur.c+N+dc[cur.d]*(cur.s%N))%N;

            cur.r = nr;
            cur.c = nc;
            map[nr][nc].add(cur);


////            Pos cur = list.get(i);
//            int nr = (cur.r + dr[cur.d] * (cur.s))% N;
//            int nc = (cur.c + dc[cur.d] * (cur.s)) % N;
//
////            nr = nr > N ? nr - N : (nr < 1 ? nr + N : nr);
////            nc = nc > N ? nc - N : (nc < 1 ? nc + N : nc);
//
//            nr = nr<1 ? nr+N : nr;
//            nc = nc<1 ? nc+N : nc;
//
//            System.out.println("cur : " + cur.r + ", " + cur.c);
//            System.out.println("nr : " + nr + ", " + nc);
//
////            cur.r = nr;
////            cur.c = nc;
//            map[nr][nc].add(new Pos(nr, nc, cur.m, cur.d, cur.s));  //cur������Ʈ �����ϱ� �ٽ� ������Ʈ
////            cur = null;
//            list.remove(cur);
        }

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(map[i][j].size() + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("-------");
    }

    static void check() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j].size()==1){
                    //�Ѱ��� �� �������� ������ �� �ֱ� ������ map[i][j]�� clear����
                    map[i][j].clear();
                    continue;
                }
                if (map[i][j].size() == 0) {
                    continue;  //������ �� �н�
                }

                //������� ���̾�� 2��
                boolean isEven = map[i][j].get(0).d % 2 == 0;  //��� ¦���ΰ�?
                boolean isOdd = map[i][j].get(0).d % 2 != 0;  //��� Ȧ���ΰ�?
                int sumM = 0;  //������ ��
                int sumS = 0;  //�ӷ��� ��
                int countF = map[i][j].size();  //������ fireball�� ����

                //����
                for (Pos cur : map[i][j]) {
                    if (isEven && cur.d % 2 == 0) {
                        //���� ¦���� ���� ���� ¦����
                        isOdd = false;
                    } else{
                        isEven = false;
                    } if (isOdd && cur.d % 2 != 0) {
                        //���� Ȧ���� ���� ���� Ȧ����
                        isEven = false;
                    } else{
                        isOdd = false;
                    }
                }

                for (Pos cur : map[i][j]) {
                    //map[i][j]�� �ִ� ��� fireball
                    sumM += cur.m;
                    sumS += cur.s;
                    list.remove(cur);  //���̾ ����Ʈ���� ����
                }

                map[i][j].clear();  //���� ���̾ �����ϰ�
                if (sumM / 5 == 0) {
                    //���� 0�̸� ������
                    continue;
                }

                if (isEven || isOdd) {  //��� ���� ¦���ų� Ȧ����
                    for (int d = 0; d < 8; d += 2) {
                        list.add(new Pos(i, j, sumM / 5, d, sumS / countF));
                    }
                } else {
                    for (int d = 1; d < 8; d += 2) {
                        list.add(new Pos(i, j, sumM / 5, d, sumS / countF));
                    }
                }
            }
        }
    }

    static class Pos {
        int r;
        int c;
        int m;  //����
        int d;  //����
        int s;  //�ӷ�

        public Pos(int r, int c, int m, int d, int s) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.d = d;
            this.s = s;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "r=" + r +
                    ", c=" + c +
                    ", m=" + m +
                    ", d=" + d +
                    ", s=" + s +
                    '}';
        }
    }
}

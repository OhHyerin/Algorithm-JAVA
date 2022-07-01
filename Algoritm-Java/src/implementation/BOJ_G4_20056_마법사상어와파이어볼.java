package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G4_20056_마법사상어와파이어볼 {
    //시뮬

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

//            map[r][c].add(new Pos(r, c, m, s, d));  //map에 저장
            list.add(new Pos(r, c, m, d, s));  //fireball리스트에 따로 저장
        }

        for (int i = 0; i < K; i++) {  //이동을 K번 한다.
            //모든 파이어볼이 자신의 방향으로 속력만큼 이동한다
//            System.out.println(list);
            move();
            //2개 이상의 파이어볼이 있는 칸 검사
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
//            map[nr][nc].add(new Pos(nr, nc, cur.m, cur.d, cur.s));  //cur업데이트 했으니까 다시 업데이트
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
                    //한개면 뒷 과정에서 합쳐질 수 있기 때문에 map[i][j]를 clear해줌
                    map[i][j].clear();
                    continue;
                }
                if (map[i][j].size() == 0) {
                    continue;  //없으면 걍 패스
                }

                //여기부터 파이어볼이 2개
                boolean isEven = map[i][j].get(0).d % 2 == 0;  //모두 짝수인가?
                boolean isOdd = map[i][j].get(0).d % 2 != 0;  //모두 홀수인가?
                int sumM = 0;  //질량의 합
                int sumS = 0;  //속력의 합
                int countF = map[i][j].size();  //합쳐진 fireball의 개수

                //방향
                for (Pos cur : map[i][j]) {
                    if (isEven && cur.d % 2 == 0) {
                        //원래 짝수고 현재 수도 짝수면
                        isOdd = false;
                    } else{
                        isEven = false;
                    } if (isOdd && cur.d % 2 != 0) {
                        //원래 홀수고 현재 수도 홀수면
                        isEven = false;
                    } else{
                        isOdd = false;
                    }
                }

                for (Pos cur : map[i][j]) {
                    //map[i][j]에 있는 모든 fireball
                    sumM += cur.m;
                    sumS += cur.s;
                    list.remove(cur);  //파이어볼 리스트에서 삭제
                }

                map[i][j].clear();  //기존 파이어볼 삭제하고
                if (sumM / 5 == 0) {
                    //질량 0이면 없어짐
                    continue;
                }

                if (isEven || isOdd) {  //모든 수가 짝수거나 홀수면
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
        int m;  //질량
        int d;  //방향
        int s;  //속력

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

package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G3_2513_통학버스 {
    //그리디


    static int N;  //아파트 단지의 수 (2<=N<=30000)
    static int K;  //통학버스의 정원 (1<=K<=2000)
    static int S;  //학교의 위치
    static Queue<Pos> left;
    static Queue<Pos> right;
    static int dist;
    static int vacanct;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        left = new PriorityQueue<>();
        right = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int pos = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());

            if (pos < S) {
                left.add(new Pos(pos, cnt, "left"));
            } else {
                right.add(new Pos(pos, cnt, "right"));
            }

        }
        dist += fillSeat(left);
        vacanct = 0;
        dist += fillSeat(right);

        System.out.println(dist);

    }

    private static int fillSeat(Queue<Pos> list) {
        int move = 0;
        while (!list.isEmpty()) {
            Pos cur = list.poll();
            while (cur.cnt > 0) { //해당 아파트의 학생들 모두 태울때까지 반복
                if (vacanct == 0) {  //남은자리 없으면
                    //버스 새로 보내야 함 (현재가 가장 학교에서 먼 거리니까)
                    move += Math.abs(cur.pos - S)*2;
                    vacanct = K;  //모두 내리니까 K만큼의 좌석 생김
                    continue;
                }
                if (cur.cnt > vacanct) {  //몇 명만 탈 수 있다면
                    cur.cnt -= vacanct;  //남은 자리만큼만 학생들을 태움
                    vacanct = 0;  //남은자리 0
                } else if (cur.cnt < vacanct) {  //학생 모두 태울 수 있다면
                    vacanct -= cur.cnt;  //공석 학생수만큼 채움
                    cur.cnt = 0;  //모두 탐
                }
            }
        }
        return move;
    }

    private static class Pos implements Comparable<Pos> {
        int pos;
        int cnt;
        String relpos;

        public Pos(int pos, int cnt, String relpos) {
            this.pos = pos;
            this.cnt = cnt;
            this.relpos = relpos;
        }

        @Override
        public int compareTo(Pos o) {
            if (relpos.equals("left")) {
                return this.pos - o.pos;
            } else {
                return (this.pos - o.pos) * -1;
            }
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "pos=" + pos +
                    ", cnt=" + cnt +
                    ", relpos='" + relpos + '\'' +
                    '}';
        }
    }
}

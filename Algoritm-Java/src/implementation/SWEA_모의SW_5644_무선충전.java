package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_모의SW_5644_무선충전 {
    //시뮬레이션 문제

    //사용자 이동 -> 좌표변경
    //해당 지점에서 접근 가능한 BC목록
    //BC를 P우선순위로 관리 -> PriorityQueue

   static StringBuilder output = new StringBuilder();
    static int M, A;
    static User[] pathA, pathB;
    static int[] dr = {0, -1, 0, 1, 0};
    static int[] dc = {0, 0, 1, 0, -1};
    static BC[] bcs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            //0초부터 M초까지 관리해야하기때문에 시작부터 1초 시작함
            M = Integer.parseInt(st.nextToken()) + 1;
            A = Integer.parseInt(st.nextToken());

            pathA = new User[M];
            pathB = new User[M];
            //문제에서 주어진 출발점 (1,1), (10,10)
            pathA[0] = new User(1, 1);
            pathB[0] = new User(10, 10);
            // user이동
            updateUserPath(br.readLine(), pathA);
            updateUserPath(br.readLine(), pathB);

            bcs = new BC[A];
            for (int a = 0; a < A; a++) {
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                bcs[a] = new BC(r, c, d, p);
            }
            //-------------입력 완료--------------
            // BC등록
            checkBatteryCharger();
            // 충전
            output.append(String.format("#%d %d%n", t, charge()));
        }
        System.out.println(output);
    }

    private static int charge() {
        int power = 0;
        for (int i = 0; i < M; i++) {
            BC bca = pathA[i].charger.poll();
            BC bcb = pathB[i].charger.poll();

            // 둘다 없는 경우
            if(bca==null && bcb==null) {
                continue;
            }
            // 한쪽만 있는 경우
            if(bca!=null && bcb==null) {
                power+=bca.perform;
            }else if(bca==null && bcb!=null) {
                power+=bcb.perform;
            }
            // 둘 다 있으면
            else {
                if(bca.equals(bcb)) {
                    // 우선 하나 꺼냄
                    power+= bca.perform;
                    // 각 후보 poll
                    BC bca2 = pathA[i].charger.poll();
                    BC bcb2 = pathB[i].charger.poll();
                    // a와 b 둘 다 후보 도출
                    if(bca2!=null && bcb2!=null) {
                        power+= Math.max(bca2.perform, bcb2.perform);
                    }else {
                        power+=(bca2==null? (bcb2==null?0: bcb2.perform): bca2.perform);
                    }
                }else {
                    power+=(bca.perform + bcb.perform);
                }
            }
        }
        return power;
    }

    private static void checkBatteryCharger() {
        for (int i = 0; i < M; i++) {
            User a = pathA[i];
            User b = pathB[i];

            for (BC bc : bcs) {
                if (a.canUse(bc)) a.charger.add(bc);
                if (b.canUse(bc)) b.charger.add(bc);
            }
        }
    }

    private static void updateUserPath(String str, User[] users) {
        StringTokenizer tokens = new StringTokenizer(str);
        for (int i = 1; i < M; i++) {
            int dir = Integer.parseInt(tokens.nextToken());
            User prev = users[i - 1];
            users[i] = new User(prev.r + dr[dir], prev.c + dc[dir]);
        }
    }

    static class User {
        int r, c;
        PriorityQueue<BC> charger = new PriorityQueue<>();

        public User(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public boolean canUse(BC charger) {
            return (Math.abs(this.r - charger.r) + Math.abs(this.c - charger.c)) <= charger.cover;
        }

    }

    static class BC implements Comparable<BC> {
        int r, c, cover, perform;

        public BC(int r, int c, int cover, int perform) {
            super();
            this.r = r;
            this.c = c;
            this.cover = cover;
            this.perform = perform;
        }
        @Override
        public int compareTo(BC o) {
            return Integer.compare(this.perform, o.perform) * -1; //내림차순
        }

    }

}

package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G5_2174_로봇시뮬레이션 {
    //시뮬레이션, 구현

    /*
    문제 설명
    1. L : 로봇이 향하고 있는 방향을 기준으로 왼쪽으로 90도 회전
    2. R : 로봇이 향하고 있는 방향을 기준으로 오른쪽으로 90도 회전
    3. F : 로봇이 향하고 있는 방향을 기준으로 앞으로 한 칸 움직인다.

    잘못된 명령
    1. Robot X crashes into the wall : X번 로봇이 벽에 충돌하는 경우이다. 즉, 주어진 땅의 밖으로 벗어나는 경우가 된다.
    2. Robot X crashes into robot Y : X번 로봇이 움직이다가 Y번 로봇에 충돌하는 경우이다.
     */

    static int R, C;  // R*C
    static int N, M;  // N : 로봇개수, M : 명령개수
    static int[] dr = {-1, 0, 1, 0};  //북동남서
    static int[] dc = {0, 1, 0, -1};
    static List<Robot> robots;  //robot정보 담은 list
    static Queue<Quest> quests;  //quest정보 담은 queue
    static int[][] map;
//    static String result = "OK";
    static Queue<String> result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        robots = new ArrayList<>();
        quests = new LinkedList<>();
        map = new int[R][C];
        result = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Math.abs(Integer.parseInt(st.nextToken()) - R);
            char dir = st.nextToken().charAt(0);
            int d = dir == 'N' ? 0 : (dir == 'E' ? 1 : (dir == 'S' ? 2 : 3));

            map[y][x] = i;

            robots.add(new Robot(y, x, d));
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int who = Integer.parseInt(st.nextToken());
            char order = st.nextToken().charAt(0);
            int cnt = Integer.parseInt(st.nextToken());

            quests.add(new Quest(who, order, cnt));
        }

        //------------------입력완료-------------------------

        playOrder();

//        System.out.println(result);
        if(result.isEmpty()){  //queue에 둘어온게 없으면
            System.out.println("OK");  //OK출력
        }else{  //queue에 들어온게 있으면
            System.out.println(result.poll());  //제일 먼저 들어온 결과 출력
        }

    }

    private static void playOrder() {
        for (Quest quest : quests) {
            Robot robot = robots.get(quest.who - 1);  //order내릴 robot 번호
            char order = quest.order;  //order 종류
            int repeat = quest.repeat%4;  //반복횟수 (방향 바꿀 때만 %4)

            switch (order) {
                case 'L':
                    robot.d = (robot.d - repeat + 4) % 4;
                    break;
                case 'R':
                    robot.d = (robot.d + repeat) % 4;
                    break;
                case 'F':
//                    robot.r = robot.r + dr[robot.d] * repeat;
//                    robot.c = robot.c + dc[robot.d] * repeat;
                    bfs(quest.who-1, quest.repeat);  //이동할 땐 quest로 들어온 robot번호와 반복횟수 ( %4 안한 것 )
//                    break;
            }

//            System.out.println(robot.d);



        }

    }

    private static void bfs(int curIdx, int repeat){
        int curR = robots.get(curIdx).r;
        int curC = robots.get(curIdx).c;
        int dir = robots.get(curIdx).d;

        for(int i=0;i<repeat;i++){
            int nr = curR + dr[dir];
            int nc = curC + dc[dir];

            if(!isIn(nr, nc)){  //범위 벗어났을 때
                StringBuilder sb = new StringBuilder();
                sb.append("Robot ").append(curIdx+1).append(" crashes into the wall");
                result.add(sb.toString());
                break;
            }

            else if(map[nr][nc]!=0){  //다른 robot에 부딪혔을 때
                StringBuilder sb = new StringBuilder();
                sb.append("Robot ").append(curIdx+1).append(" crashes into robot ").append(map[nr][nc]);
                result.add(sb.toString());
                break;
            }

            map[curR][curC] = 0;
            map[nr][nc] = curIdx;

            curR = nr;
            curC = nc;
            robots.get(curIdx).r = nr;
            robots.get(curIdx).c = nc;
        }

    }

    //map안쓰고 하려했는데 가는 길에 못찾아서 실패 (워프,,)
    private static int isRobot(Robot cur) {
        int isHit = -1;

        for (int i = 0; i < robots.size(); i++) {
            if (robots.get(i) == cur) continue;
            if (robots.get(i).r == cur.r && robots.get(i).c == cur.c) {
                isHit = i;
                break;
            }
        }

        return isHit;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }

    private static class Robot {
        int r;
        int c;
        int d;

        public Robot(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    private static class Quest {
        int who;
        char order;
        int repeat;

        public Quest(int who, char order, int repeat) {
            this.who = who;
            this.order = order;
            this.repeat = repeat;
        }
    }
}

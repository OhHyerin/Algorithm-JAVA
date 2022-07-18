package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G5_2174_�κ��ùķ��̼� {
    //�ùķ��̼�, ����

    /*
    ���� ����
    1. L : �κ��� ���ϰ� �ִ� ������ �������� �������� 90�� ȸ��
    2. R : �κ��� ���ϰ� �ִ� ������ �������� ���������� 90�� ȸ��
    3. F : �κ��� ���ϰ� �ִ� ������ �������� ������ �� ĭ �����δ�.

    �߸��� ���
    1. Robot X crashes into the wall : X�� �κ��� ���� �浹�ϴ� ����̴�. ��, �־��� ���� ������ ����� ��찡 �ȴ�.
    2. Robot X crashes into robot Y : X�� �κ��� �����̴ٰ� Y�� �κ��� �浹�ϴ� ����̴�.
     */

    static int R, C;  // R*C
    static int N, M;  // N : �κ�����, M : ��ɰ���
    static int[] dr = {-1, 0, 1, 0};  //�ϵ�����
    static int[] dc = {0, 1, 0, -1};
    static List<Robot> robots;  //robot���� ���� list
    static Queue<Quest> quests;  //quest���� ���� queue
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

        //------------------�Է¿Ϸ�-------------------------

        playOrder();

//        System.out.println(result);
        if(result.isEmpty()){  //queue�� �Ѿ�°� ������
            System.out.println("OK");  //OK���
        }else{  //queue�� ���°� ������
            System.out.println(result.poll());  //���� ���� ���� ��� ���
        }

    }

    private static void playOrder() {
        for (Quest quest : quests) {
            Robot robot = robots.get(quest.who - 1);  //order���� robot ��ȣ
            char order = quest.order;  //order ����
            int repeat = quest.repeat%4;  //�ݺ�Ƚ�� (���� �ٲ� ���� %4)

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
                    bfs(quest.who-1, quest.repeat);  //�̵��� �� quest�� ���� robot��ȣ�� �ݺ�Ƚ�� ( %4 ���� �� )
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

            if(!isIn(nr, nc)){  //���� ����� ��
                StringBuilder sb = new StringBuilder();
                sb.append("Robot ").append(curIdx+1).append(" crashes into the wall");
                result.add(sb.toString());
                break;
            }

            else if(map[nr][nc]!=0){  //�ٸ� robot�� �ε����� ��
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

    //map�Ⱦ��� �Ϸ��ߴµ� ���� �濡 ��ã�Ƽ� ���� (����,,)
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

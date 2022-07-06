package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G3_23288_주사위굴리기2 {
    //구현, 시뮬레이션, 그래프탐색

    /*
    문제 이해 :
    1. 주사위가 이동 방향으로 한 칸 굴러간다. 만약, 이동 방향에 칸이 없다면, 이동 방향을 반대로 한 다음 한 칸 굴러간다.
    2. 주사위가 도착한 칸(x, y)에 대한 점수를 획득한다.
    3. 주사위의 아랫면에 있는 정수 A와 주사위가 있는 칸(x,y)에 있는 정수 B를 비교해 이동 방향을 결정한다.
        1) A>B인 경우 이동방향을 90도 시계방향으로 회전시킨다.  --> dr, dc를 +1
        2) A<B인 경우 이동방향을 90도 반시계방향으로 회전시킨다  --> dr, dc를 -1
        3) A=B인 경우 이동방향에 변화는 없다.
     */

    static int R, C;  //R*C
    static int K; //이동하는 횟수
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};  //북->동->남->서
    static int[] dc = {0, 1, 0, -1};
    //앞면 : dice[1][1], 뒷면 : dice[3][1];
    static int[][] dice = {{0, 2, 0}, {4, 1, 3}, {0, 5, 0}, {0, 6, 0}};  //4*3의 전개도 모양
    /*
    0 2 0
    4 1 3
    0 5 0
    0 6 0
     */
    static int curDir, curR, curC;  //초기 방향과 위치
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());  //이동하는 횟수

        map = new int[R + 1][C + 1];

        for (int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //----------------입력완료--------------------------
        curDir = 1;
        curR = 1;
        curC = 1;
        for (int k = 0; k < K; k++) {
            move();  //다음 갈 위치와 방향 설정, 주사위 돌리기
        }
        System.out.println(result);

    }

    private static void move() {
        //nr, nc : 한칸 갈 다음 위치 (범위 밖이면 반대방향으로)
        int nr = curR + dr[curDir];
        int nc = curC + dc[curDir];

        if (!isIn(nr, nc)) {
            //범위를 벗어나면 반대방향으로
            curDir = (curDir + 2) % 4;
            nr = curR + dr[curDir];
            nc = curC + dc[curDir];
        }

        //curDir 방향대로 주사위 이동
        rotate(curDir);

        //curR, curC 다시 설정
        curR = nr;
        curC = nc;

        //bfs 사용해서 그 자리에서 얻을 수 있는 점수 획득
        result += getScores();

        //다음 갈 방향과 위치 설정
        int A = dice[3][1];  //주사위 아랫면
        int B = map[curR][curC];  //주사위가 있는 칸에 있는 정수
        if (A > B) {
            curDir = (curDir + 1) % 4;
        } else if (A < B) {
            curDir = (curDir - 1 + 4) % 4;
        }

    }

    private static int getScores() {
        int B = map[curR][curC];
        int c = 0;

        Queue<Pos> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R + 1][C + 1];

        queue.add(new Pos(curR, curC));
        visited[curR][curC] = true;

        while (!queue.isEmpty()) {
            Pos cur = queue.poll();
            c++;
            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (!isIn(nr, nc)) continue; //범위 벗어나면 continue
                if (visited[nr][nc]) continue; //이미 방문한 곳이면 continue
                if (map[nr][nc] != B) continue;  //지도 위 숫자가 B와 다르면 continue

                queue.add(new Pos(nr, nc));
                visited[nr][nc] = true;
            }
        }

        return B * c;
    }


    private static void rotate(int type) {
        /* 초기값
        0 2 0
        4 1 3
        0 5 0
        0 6 0
         */
        if (type == 0) rotateUp();
        /*  북쪽으로 굴러감
        0 1 0
        4 5 3
        0 6 0
        0 2 0
         */
        else if (type == 1) rotateRight();
        /* 동쪽으로 굴러감
        0 2 0
        6 4 1
        0 5 0
        0 3 0
        */
        else if (type == 2) rotateDown();
        /* 남쪽으로 굴러감
        0 6 0
        4 2 3
        0 1 0
        0 5 0
         */
        else if (type == 3) rotateLeft();
        /* 서쪽으로 굴러감
        0 2 0
        1 3 6
        0 5 0
        0 4 0
         */
    }

    private static void rotateUp() {
        int temp = dice[0][1];
        for (int i = 0; i < 3; i++) {
            dice[i][1] = dice[i + 1][1];
        }
        dice[3][1] = temp;
    }

    private static void rotateDown() {
        int temp = dice[3][1];
        for (int i = 3; i > 0; i--) {
            dice[i][1] = dice[i - 1][1];
        }
        dice[0][1] = temp;
    }

    private static void rotateLeft() {
        int temp = dice[3][1];
        dice[3][1] = dice[1][0];
        dice[1][0] = dice[1][1];
        dice[1][1] = dice[1][2];
        dice[1][2] = temp;
    }

    private static void rotateRight() {
        int temp = dice[3][1];
        dice[3][1] = dice[1][2];
        dice[1][2] = dice[1][1];
        dice[1][1] = dice[1][0];
        dice[1][0] = temp;
    }

    private static boolean isIn(int r, int c) {
        return r > 0 && c > 0 && r <= R && c <= C;
    }

    private static class Pos {
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}

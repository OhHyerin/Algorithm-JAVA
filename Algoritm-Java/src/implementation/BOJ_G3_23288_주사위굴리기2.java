package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G3_23288_�ֻ���������2 {
    //����, �ùķ��̼�, �׷���Ž��

    /*
    ���� ���� :
    1. �ֻ����� �̵� �������� �� ĭ ��������. ����, �̵� ���⿡ ĭ�� ���ٸ�, �̵� ������ �ݴ�� �� ���� �� ĭ ��������.
    2. �ֻ����� ������ ĭ(x, y)�� ���� ������ ȹ���Ѵ�.
    3. �ֻ����� �Ʒ��鿡 �ִ� ���� A�� �ֻ����� �ִ� ĭ(x,y)�� �ִ� ���� B�� ���� �̵� ������ �����Ѵ�.
        1) A>B�� ��� �̵������� 90�� �ð�������� ȸ����Ų��.  --> dr, dc�� +1
        2) A<B�� ��� �̵������� 90�� �ݽð�������� ȸ����Ų��  --> dr, dc�� -1
        3) A=B�� ��� �̵����⿡ ��ȭ�� ����.
     */

    static int R, C;  //R*C
    static int K; //�̵��ϴ� Ƚ��
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};  //��->��->��->��
    static int[] dc = {0, 1, 0, -1};
    //�ո� : dice[1][1], �޸� : dice[3][1];
    static int[][] dice = {{0, 2, 0}, {4, 1, 3}, {0, 5, 0}, {0, 6, 0}};  //4*3�� ������ ���
    /*
    0 2 0
    4 1 3
    0 5 0
    0 6 0
     */
    static int curDir, curR, curC;  //�ʱ� ����� ��ġ
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());  //�̵��ϴ� Ƚ��

        map = new int[R + 1][C + 1];

        for (int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //----------------�Է¿Ϸ�--------------------------
        curDir = 1;
        curR = 1;
        curC = 1;
        for (int k = 0; k < K; k++) {
            move();  //���� �� ��ġ�� ���� ����, �ֻ��� ������
        }
        System.out.println(result);

    }

    private static void move() {
        //nr, nc : ��ĭ �� ���� ��ġ (���� ���̸� �ݴ��������)
        int nr = curR + dr[curDir];
        int nc = curC + dc[curDir];

        if (!isIn(nr, nc)) {
            //������ ����� �ݴ��������
            curDir = (curDir + 2) % 4;
            nr = curR + dr[curDir];
            nc = curC + dc[curDir];
        }

        //curDir ������ �ֻ��� �̵�
        rotate(curDir);

        //curR, curC �ٽ� ����
        curR = nr;
        curC = nc;

        //bfs ����ؼ� �� �ڸ����� ���� �� �ִ� ���� ȹ��
        result += getScores();

        //���� �� ����� ��ġ ����
        int A = dice[3][1];  //�ֻ��� �Ʒ���
        int B = map[curR][curC];  //�ֻ����� �ִ� ĭ�� �ִ� ����
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

                if (!isIn(nr, nc)) continue; //���� ����� continue
                if (visited[nr][nc]) continue; //�̹� �湮�� ���̸� continue
                if (map[nr][nc] != B) continue;  //���� �� ���ڰ� B�� �ٸ��� continue

                queue.add(new Pos(nr, nc));
                visited[nr][nc] = true;
            }
        }

        return B * c;
    }


    private static void rotate(int type) {
        /* �ʱⰪ
        0 2 0
        4 1 3
        0 5 0
        0 6 0
         */
        if (type == 0) rotateUp();
        /*  �������� ������
        0 1 0
        4 5 3
        0 6 0
        0 2 0
         */
        else if (type == 1) rotateRight();
        /* �������� ������
        0 2 0
        6 4 1
        0 5 0
        0 3 0
        */
        else if (type == 2) rotateDown();
        /* �������� ������
        0 6 0
        4 2 3
        0 1 0
        0 5 0
         */
        else if (type == 3) rotateLeft();
        /* �������� ������
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

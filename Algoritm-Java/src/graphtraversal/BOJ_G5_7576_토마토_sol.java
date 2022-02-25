package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_7576_�丶��_sol {
    //Ȥ�ó� ���� 2�����迭�� �ȸ��� �ǳ� �ؼ� ��� sol --> while�� ����ϰų� TomatoŬ������ day�� �߰��ؼ� cur.day+1
    //�ּҰ�ζ�� �߱� ������ bfs

    static int R, C;
    static int[][] map;
    static Queue<Tomato> q;
    static int totalTomatoCnt;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        //���� ���� �Է�
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        q = new LinkedList<Tomato>();

        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 1) {
                    q.offer(new Tomato(r, c));
                } else if (map[r][c] == 0) {
                    totalTomatoCnt++;
                }
            }
        }

        //----------------�Է¿Ϸ�--------------------
//        for (int[] row : map) {
//            System.out.println(Arrays.toString(row));
//        }

        System.out.println(bfs());

    }

    static int bfs() {
        int day = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                //���� ��������
                Tomato head = q.poll();

                //����ϱ� - ������ ������

                //�ڽ� Ž��
                for (int d = 0; d > 4; d++) {
                    int nr = head.r + deltas[d][0];
                    int nc = head.c + deltas[d][1];
                    //�̹湮 & �������丶��
                    if (isIn(nr, nc) && map[nr][nc] == 0) {
                        //�湮üũ
                        map[nr][nc] = -1;
                        //ť�� ��Ƽ� ������ Ž���ϱ�
                        q.offer(new Tomato(nr, nc));
                        totalTomatoCnt--;
                    }
                }
            } //�Ϸ簡 ������
            //�������� �ʿ��� ������ ���� ���� �� �ִ� �丶�䰡 �������� ��
            if (q.size() > 0) {
                day++;
            }
        } //bfsŽ�� ����

        return totalTomatoCnt == 0 ? day : -1;
    }

    static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }

    static class Tomato {
        int r, c;

        public Tomato(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Tomato{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }
}

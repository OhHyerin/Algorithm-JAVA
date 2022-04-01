package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G1_1194_�����������ٰ��� {
    //�׷���Ž�� - bfs
    //��Ʈ����ŷ (�Ǵ� 8���� �迭)
    //�ٽ� Ǯ���

    /*
    ��ĭ : ������ �̵��� �� �ִ� (.)
    �� : ���� �̵��� �� ����(#)
    ���� : ������ �̵��� �� �ִ�. �� ���� ó�� ���� ���踦 ��´�. (a, b, c, d, e, f)
    �� : �����ϴ� ���谡 ���� ���� �̵��� �� �ִ�.(A, B, C, D, E, F)
    �ν����� ���� ��ġ : �� ���̰�, �ν��̰� ���� �� �ִ� ���̴�.(0)
    �ⱸ : ���� �������� ������, �ν��̰� �����ϴ� ���̴�. �� ���� ���� �̷θ� Ż���Ѵ�.(1)
     */


    static int R, C;
    static char[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Pos ms;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == '0') {
                    ms = new Pos(i, j, 0);
                }
            }
        }

        System.out.println(bfs());

    }

    private static int bfs() {
        Queue<Pos> queue = new LinkedList<>();
        boolean visited[][][] = new boolean[R][C][1 << 6];

        queue.offer(ms);
        visited[ms.r][ms.c][ms.key] = true;

        int depth = 0;
        while (!queue.isEmpty()) {
            Pos cur = queue.poll();

            if (map[cur.r][cur.c] == '1') {
                //Ż��
                return depth;
            }

            //������ ��
            if (map[cur.r][cur.c] >= 'a' && map[cur.r][cur.c] <= 'f') {
                // |�����ڷ� ���� nk�� map���� ��ħ
//                cur.key |= (1<<(map[cur.r][cur.c]-'a'));
                cur.updateKey(map[cur.r][cur.c]);
//                visited[cur.r][cur.c][cur.key] = true;
//                queue.offer(new Pos(cur.r, cur.c, cur.key));
            }


            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if(isIn(nr, nc) && !visited[nr][nc][cur.key]){
                    if(map[nr][nc]=='#') continue;
                    //���� ���� Ű�� ���� ��
                    if(map[nr][nc]>='A' && map[nr][nc]<='F' && !cur.hasKey(map[nr][nc])) continue;

                    //Ű�� ������ �� �� ����
                    visited[nr][nc][cur.key] = true;
                    queue.offer(new Pos(nr, nc, cur.key));
                }
            }//d
            depth++;
        }
        return -1;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }

    private static class Pos {
        int r;
        int c;
        int key;

        public Pos(int r, int c, int key) {
            this.r = r;
            this.c = c;
            this.key = key;
        }

        public boolean hasKey(char gate){
            return (key & ( 1<< (gate-'A') ) ) > 0;
        }

        public void updateKey(char key){
            key |= ( 1 << (key-'a') );
        }
    }

}

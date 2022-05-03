package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G4_4179_�� {
    //�׷���Ž��, bfs
    //while(true)�ȿ� ���� while���� ������ while���� �� �ٴ� ���� �޶���

    /*
7 7
#######
#J#####
#.....#
#.#.#.#
#.#.#.#
F.#.#.#
##F.#.#
     */

    static int R, C;
    static char[][] map;
    static Pos JH;
    //    static List<Pos> fires;
    static Queue<Pos> fires;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int time;
    static boolean isEscape = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
//        fires = new ArrayList<>();
        fires = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'J') {
                    JH = new Pos(i, j);  //���� ��ġ ����
                } else if (map[i][j] == 'F') {
                    fires.add(new Pos(i, j));  //�� list�߰�
                }
            }
        }

        bfs();

        if (isEscape) {
            System.out.println(time);
        } else {
            System.out.println("IMPOSSIBLE");
        }


    }

    private static void bfs() {
        Queue<Pos> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];

        queue.add(JH);
        visited[JH.r][JH.c] = true;

        while (true) {  //�� ���� �� �̵� -> ������ �� �̵�

            if (queue.isEmpty()) {  //���࿡ ������ ���̻� �� ���� ������ ����
                return;
            }

            time++;  //turn

            //�� ��Ʈ����
            int qSize = fires.size();  //���� ���� ������ (for�� ���鼭 ����ؼ� fires�� �߰����ֱ⶧���� �̸� ����� ����ؾ� ��)
            while (qSize-- > 0) {
//                Pos curFire = fires.get(i);
                Pos curFire = fires.poll();

                for (int d = 0; d < 4; d++) {
                    int nrF = curFire.r + dr[d];
                    int ncF = curFire.c + dc[d];

                    if (!isIn(nrF, ncF)) continue; //���� ����� continue
                    if (map[nrF][ncF] == '#') continue;  //���̸� continue
                    if (map[nrF][ncF] == 'F') continue;  //�̹� �� ��ġ�� continue
                    if (map[nrF][ncF] == 'J') continue;  //������ġ�� �켱 continue. �����Ͽ� �� ���� ��!
                    map[nrF][ncF] = 'F';
                    fires.add(new Pos(nrF, ncF));
                }
            }

            int jSize = queue.size();  //�����̰� �� ��ġ�� ������ (�Ұ� ���� ������ �̸� ���)
            while (jSize-- > 0) {
                //���� �̵�
                Pos cur = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if (!isIn(nr, nc)) {
                        isEscape = true;
                        return;  //���� ����� Ż�� ����
                    }
                    if (map[nr][nc] == '#' || map[nr][nc] == 'F') continue; //���̳� ���̸� continue
                    if (visited[nr][nc]) continue; //�湮 �ߴ� ���̸� continue

                    queue.add(new Pos(nr, nc));
                    visited[nr][nc] = true;
                    map[nr][nc] = 'J';  //��� ��! �̵� Ȯ���غ��� ���� ��
                }

//                System.out.println("time : " + time);
//                for (int i = 0; i < R; i++) {
//                    for (int j = 0; j < C; j++) {
//                        System.out.print(map[i][j]);
//                    }
//                    System.out.println();
//                }
//                System.out.println("--------------------");
            }
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
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

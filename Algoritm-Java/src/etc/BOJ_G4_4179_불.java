package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G4_4179_불 {
    //그래프탐색, bfs
    //while(true)안에 불의 while문과 지훈의 while문이 돈 다는 점이 달랐음

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
                    JH = new Pos(i, j);  //지훈 위치 저장
                } else if (map[i][j] == 'F') {
                    fires.add(new Pos(i, j));  //불 list추가
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

        while (true) {  //불 먼저 다 이동 -> 지훈이 다 이동

            if (queue.isEmpty()) {  //만약에 지훈이 더이상 갈 곳이 없으면 리턴
                return;
            }

            time++;  //turn

            //불 퍼트리기
            int qSize = fires.size();  //현재 불의 사이즈 (for문 돌면서 계속해서 fires에 추가해주기때문에 미리 사이즈를 명시해야 함)
            while (qSize-- > 0) {
//                Pos curFire = fires.get(i);
                Pos curFire = fires.poll();

                for (int d = 0; d < 4; d++) {
                    int nrF = curFire.r + dr[d];
                    int ncF = curFire.c + dc[d];

                    if (!isIn(nrF, ncF)) continue; //범위 벗어나면 continue
                    if (map[nrF][ncF] == '#') continue;  //벽이면 continue
                    if (map[nrF][ncF] == 'F') continue;  //이미 불 위치면 continue
                    if (map[nrF][ncF] == 'J') continue;  //지훈위치면 우선 continue. 다음턴에 불 퍼질 것!
                    map[nrF][ncF] = 'F';
                    fires.add(new Pos(nrF, ncF));
                }
            }

            int jSize = queue.size();  //지훈이가 갈 위치의 사이즈 (불과 같은 이유로 미리 명시)
            while (jSize-- > 0) {
                //지훈 이동
                Pos cur = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if (!isIn(nr, nc)) {
                        isEscape = true;
                        return;  //범위 벗어나면 탈출 성공
                    }
                    if (map[nr][nc] == '#' || map[nr][nc] == 'F') continue; //벽이나 불이면 continue
                    if (visited[nr][nc]) continue; //방문 했던 곳이면 continue

                    queue.add(new Pos(nr, nc));
                    visited[nr][nc] = true;
                    map[nr][nc] = 'J';  //없어도 됨! 이동 확인해보려 찍은 것
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

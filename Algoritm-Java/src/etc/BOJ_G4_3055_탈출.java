package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_3055_탈출 {
    //bfs

    /*
    . : 비어있는 곳
    * : 물이 차있는 곳
    X : 돌
    D : 비버의 굴 (도착점)
    S : 고슴도치의 위치 (시작점)
     */


    static int R, C;
    static char[][] map;
    static Queue<Pos> water;
    static Queue<Pos> queue;  //고슴도치
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int count;
    static boolean[][] visited;
    static boolean isCollision;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];

        water = new LinkedList<>();
        queue = new LinkedList<>();
        Pos S = null;

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'S') {
                    queue.add(new Pos(i, j, 0));
                    visited[i][j] = true;
                } else if (map[i][j] == '*') {
                    water.add(new Pos(i, j, 0));
                }
            }
        }

        bfs();

        if (count == -1) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(count);
        }

    }

    private static void bfs() {
        while (true) {
//            count++;

            //물 퍼트리기
            int waterSize = water.size();
            for (int i = 0; i < waterSize; i++) {
                Pos w = water.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int nwr = w.r + dr[dir];
                    int nwc = w.c + dc[dir];

                    if (!isIn(nwr, nwc)) continue;
                    if (map[nwr][nwc] == '.') { //빈공간이면
                        map[nwr][nwc] = '*';
                        water.add(new Pos(nwr, nwc, 0));
                    }
                }
            }

            int sSize = queue.size();
            if(sSize==0){
                count = -1;
                return;
            }

            for (int i = 0; i < sSize; i++) {
                Pos cur = queue.poll();
                if (map[cur.r][cur.c] == 'D') {
                    count = cur.cnt;
                    return;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if (!isIn(nr, nc)) continue;
                    if(visited[nr][nc]) continue;
                    if(map[nr][nc]=='.' || map[nr][nc]=='D'){
                        //고슴도치 이동
                        map[cur.r][cur.c] = '.';
                        if(map[nr][nc]=='.'){
                            map[nr][nc] = 'S';
                        }
                        queue.add(new Pos(nr, nc, cur.cnt + 1));
                    }



                }


            }

        }

    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }

    private static class Pos {
        int r;
        int c;
        int cnt;

        public Pos(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }

}

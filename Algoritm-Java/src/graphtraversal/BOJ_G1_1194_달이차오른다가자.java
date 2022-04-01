package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G1_1194_달이차오른다가자 {
    //그래프탐색 - bfs
    //비트마스킹 (또는 8차원 배열)
    //다시 풀어보기

    /*
    빈칸 : 언제나 이동할 수 있다 (.)
    벽 : 절대 이동할 수 없다(#)
    열쇠 : 언제나 이동할 수 있다. 이 곳에 처음 들어가면 열쇠를 잡는다. (a, b, c, d, e, f)
    문 : 대응하는 열쇠가 있을 때만 이동할 수 있다.(A, B, C, D, E, F)
    민식이의 현재 위치 : 빈 곳이고, 민식이가 현재 서 있는 곳이다.(0)
    출구 : 달이 차오르기 때문에, 민식이가 가야하는 곳이다. 이 곳에 오면 미로를 탈출한다.(1)
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
                //탈출
                return depth;
            }

            //열쇠일 때
            if (map[cur.r][cur.c] >= 'a' && map[cur.r][cur.c] <= 'f') {
                // |연산자로 기존 nk와 map값을 합침
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
                    //문에 대한 키가 없을 때
                    if(map[nr][nc]>='A' && map[nr][nc]<='F' && !cur.hasKey(map[nr][nc])) continue;

                    //키가 있으면 갈 수 있음
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

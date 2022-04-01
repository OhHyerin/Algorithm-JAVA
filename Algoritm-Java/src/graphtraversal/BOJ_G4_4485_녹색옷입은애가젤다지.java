package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_4485_³ì»ö¿ÊÀÔÀº¾Ö°¡Á©´ÙÁö {
    //±×·¡ÇÁÅ½»ö
    /*
3
5 5 4
3 9 1
3 2 7
0
     */

    static int N;
    static int[][] map;
    static int[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int p = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            sb.append("Problem ").append(p).append(": ");

            map = new int[N][N];
            visited = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    visited[i][j] = Integer.MAX_VALUE;
                }
            }

            int answer = bfs();
            sb.append(answer).append("\n");
            p++;
        }//while
        System.out.println(sb);
    }

    private static int bfs() {
        Queue<Pos> queue = new PriorityQueue<>();

        queue.add(new Pos(0, 0, map[0][0]));
        visited[0][0] = map[0][0];

        while (!queue.isEmpty()) {
            Pos cur = queue.poll();

            if(cur.r==N-1 && cur.c==N-1){
                return visited[N-1][N-1];
            }

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (!isIn(nr, nc)) continue;
                if (visited[nr][nc] <= visited[cur.r][cur.c] + map[nr][nc]) continue;

                visited[nr][nc] = Math.min(visited[cur.r][cur.c]+map[nr][nc], visited[nr][nc]);
                queue.add(new Pos(nr, nc, visited[nr][nc]));

            }

        }
        return -1;
    }


    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }


    private static class Pos implements Comparable<Pos>{
        int r;
        int c;
        int cost;

        public Pos(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pos o) {
            return cost-o.cost;
        }
    }
}

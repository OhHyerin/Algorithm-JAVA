package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1953_탈주범검거 {
    //모의 SW 역량테스트

    static int R, C; // R*C
    static int mR, mC;  //맨홀 뚜껑 위치 (mR, mC);
    static int L; //탈출 후 소요된 시간
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Queue<Pos> queue;
    static boolean[][] visited;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            st = new StringTokenizer(br.readLine());

            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            mR = Integer.parseInt(st.nextToken());
            mC = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            map = new int[R][C];
            visited = new boolean[R][C];
            queue = new LinkedList<>();

            answer = 1;

            for (int i = 0; i < R; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < C; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bfs();
            sb.append(answer).append("\n");
        }//t
        System.out.println(sb);
    }

    private static void bfs() {
        queue.offer(new Pos(mR, mC));
        visited[mR][mC] = true;
        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            time++;
            if (time >= L) return;

            for (int i = 0; i < size; i++) {
                Pos cur = queue.poll();
                int pipe = map[cur.r][cur.c];

                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if (!isIn(nr, nc)) continue;
                    if (visited[nr][nc]) continue;
                    if (map[nr][nc] == 0) continue;

                    int n = map[nr][nc];
                    if (d == 0) {
                        if (pipe == 1 || pipe == 2 || pipe == 4 || pipe == 7) {
                            if (n == 1 || n == 2 || n == 5 || n == 6) {
                                visited[nr][nc] = true;
                                queue.add(new Pos(nr, nc));
                                answer++;
                            }
                        }
                    } else if (d == 1) {
                        if (pipe == 1 || pipe == 2 || pipe == 5 || pipe == 6) {
                            if (n == 1 || n == 2 || n == 4 || n == 7) {
                                visited[nr][nc] = true;
                                queue.add(new Pos(nr, nc));
                                answer++;
                            }
                        }
                    } else if (d == 2) {
                        if (pipe == 1 || pipe == 3 || pipe == 6 || pipe == 7) {
                            if (n == 1 || n == 3 || n == 4 || n == 5) {
                                visited[nr][nc] = true;
                                queue.add(new Pos(nr, nc));
                                answer++;
                            }
                        }
                    } else if (d == 3) {
                        if (pipe == 1 || pipe == 3 || pipe == 4 || pipe == 5) {
                            if (n == 1 || n == 3 || n == 6 || n == 7) {
                                visited[nr][nc] = true;
                                queue.add(new Pos(nr, nc));
                                answer++;
                            }
                        }
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

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}

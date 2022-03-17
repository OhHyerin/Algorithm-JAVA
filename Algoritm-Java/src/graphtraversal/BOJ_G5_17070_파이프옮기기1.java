package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_17070_파이프옮기기1 {
    //백준 삼성 A형 문제집

    static int N;
    static int[][] map;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(1, 2, 1);
        System.out.println(count);

    }


    private static void dfs(int r, int c, int state) {
        if (r == N && c == N) {
            count++;
            return;
        }

        if (state == 1) {
            //가로
            if (isIn(r, c + 1) && map[r][c + 1] == 0) {
                dfs(r, c + 1, 1);
            }

        } else if (state == 2) {
            //세로
            if (isIn(r + 1, c) && map[r + 1][c] == 0) {
                dfs(r + 1, c, 2);
            }

        } else if (state == 3) {
            //대각선
            if (isIn(r, c + 1) && map[r][c + 1] == 0) {
                dfs(r, c + 1, 1);
            }
            if (isIn(r + 1, c) && map[r + 1][c] == 0) {
                dfs(r + 1, c, 2);
            }
        }
        //대각선방향
        if (isIn(r + 1, c + 1) && map[r][c + 1] == 0 && map[r + 1][c] == 0 && map[r + 1][c + 1] == 0) {
            dfs(r + 1, c + 1, 3);
        }
    }


    private static boolean isIn(int r, int c) {
        return r > 0 && c > 0 && r <= N && c <= N;
    }

}

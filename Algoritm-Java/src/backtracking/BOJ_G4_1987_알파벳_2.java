package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_G4_1987_알파벳_2 {
    //dfs, 백트래킹

    static int R, C;
    static char[][] map;
    static HashSet<Character> hashSet;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int maxCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        hashSet = new HashSet<>();

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        //-----------------------입력완료--------------------
        maxCount = Integer.MIN_VALUE;

        dfs(0, 0, new HashSet<>(), 0);

        System.out.println(maxCount);


    }

    private static void dfs(int r, int c, HashSet<Character> hashSet, int count) {
        if (hashSet.contains(map[r][c])) {
            maxCount = Math.max(maxCount, count);
            return;
        }

        hashSet.add(map[r][c]);
//        System.out.println(hashSet);

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (!isIn(nr, nc)) continue;

            dfs(nr, nc, hashSet, count+1);
        }
        hashSet.remove(map[r][c]);

    }



    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }
}

package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_2096_내려가기 {
    //dp, dfs

    static int N;
    static int[][] map;
    static int[] dr = {1, 1, 1};
    static int[] dc = {-1, 0, 1};
    static int minAnswer = Integer.MAX_VALUE;
    static int maxAnswer = Integer.MIN_VALUE;
    static Pos[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new int[N][3];
        dp = new Pos[N][3];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<3;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = new Pos(Integer.MAX_VALUE, Integer.MIN_VALUE);

            }
        }

        for(int i=0;i<3;i++){
            dp[0][i] = new Pos(map[0][i], map[0][i]);
            dfs(0, i, dp);
        }

        System.out.println(maxAnswer+" "+minAnswer);
//        System.out.println(minAnswer);

    }

    private static void dfs(int r, int c, Pos[][] dp){
        if(r==(N-1)){
            minAnswer = Math.min(minAnswer, dp[r][c].min);
            maxAnswer = Math.max(maxAnswer, dp[r][c].max);
            return;
        }

        for(int d=0;d<3;d++){
            int nr = r+dr[d];
            int nc = c+dc[d];

            if(isIn(nr, nc)){
                int mn = Math.min(dp[r][c].min+map[nr][nc], dp[nr][nc].min);
                int mx = Math.max(dp[r][c].max+map[nr][nc], dp[nr][nc].max);
                dp[nr][nc] = new Pos(mn, mx);
//                System.out.println(dp[nr][nc]);
                dfs(nr,nc,dp);
            }
        }
    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<N && c<3;
    }

    private static class Pos{
        int min;
        int max;

        public Pos(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "min=" + min +
                    ", max=" + max +
                    '}';
        }
    }

}

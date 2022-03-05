package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S2_14430_자원캐기 {
    //백준 실버2
    //오늘의문제

    static int R, C;
    static int[][] map;
    static int[][] dp;
    static int[] dr = {0, 1};
    static int[] dc = {1, 0};
    static int maxCount = Integer.MIN_VALUE;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        dp = new int[R][C];

        for(int i=0;i<R;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<C;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = Integer.MIN_VALUE;
            }
        }

        dfs(0, 0, map[0][0]);
        System.out.println(maxCount);

    }

    static void dfs(int r, int c, int count){
//        System.out.println("r : "+r+" c : "+c+" count : "+count);

        if(r==(R-1) && c==(C-1)){
            maxCount = Math.max(maxCount, count);
//            System.out.println(count);
            return;
        }
        if(dp[r][c]!=Integer.MIN_VALUE){
            return;
        }

        dp[r][c] = map[r][c];
        for(int d=0;d<2;d++){
            int nr = r+dr[d];
            int nc = c+dc[d];

            if (isIn(nr, nc) && dp[nr][nc]<count+map[nr][nc]) {
                System.out.println("nr:"+nr+" nc:"+nc);
                dfs(nr, nc, count+map[nr][nc]);
            }

        }
    }

    static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }

    static class Pos{
        int r;
        int c;
        int count;

        public Pos(int r, int c, int count) {
            this.r = r;
            this.c = c;
            this.count = count;
        }
    }
}

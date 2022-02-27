package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_S2_11060_점프점프 {
    //백준 실버2
    //dp

    static int n;
    static int[] maze;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        maze = new int[n+1];
        dp = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++){
            maze[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;

        for (int i=1;i<=n;i++){
            if(dp[i]==Integer.MAX_VALUE) continue;
            int jump = maze[i];
            for(int j = 1;j<=jump;j++){
                if(i+j<=n){
                    dp[i+j] = Math.min(dp[i]+1, dp[i+j]);
                }
            }
        }

        if(dp[n]==Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(dp[n]);
        }


    }




}

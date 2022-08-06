package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_2225_ÇÕºÐÇØ {
    //dp

    static int N, K;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[K+1][N+1];

        for(int n=0;n<=N;n++){
            dp[1][n] = 1;
        }

        for(int k=1;k<=K;k++){
            dp[k][0] = 1;
        }


        for(int k=2;k<=K;k++){
            for(int n=1;n<=N;n++){
                dp[k][n] = dp[k-1][n] + dp[k][n-1];
                dp[k][n] %=1000000000;
            }
        }

        System.out.println(dp[K][N]);


    }

}

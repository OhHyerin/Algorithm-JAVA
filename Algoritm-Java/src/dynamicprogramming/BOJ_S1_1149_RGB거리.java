package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_1149_RGB거리 {
    //dp

    static int N;  //집의 개수
    static int R = 0, G = 1, B = 2;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        dp = new int[N][3];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            dp[i][R] = Integer.parseInt(st.nextToken());
            dp[i][G] = Integer.parseInt(st.nextToken());
            dp[i][B] = Integer.parseInt(st.nextToken());
        }


        for(int i=1;i<N;i++){
            dp[i][R] = Math.min(dp[i-1][G], dp[i-1][B]) + dp[i][R];
            dp[i][G] = Math.min(dp[i-1][R], dp[i-1][B]) + dp[i][G];
            dp[i][B] = Math.min(dp[i-1][R], dp[i-1][G]) + dp[i][B];
        }

        int result = Math.min(dp[N-1][R], Math.min(dp[N-1][G], dp[N-1][B]));

        System.out.println(result);

    }
}

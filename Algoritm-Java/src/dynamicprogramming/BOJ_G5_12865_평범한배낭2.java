package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_12865_평범한배낭2 {
    //dp, 배낭문제

    static int N;  //물품의 수
    static int K;  //버틸수있는 무게
    static int[] weight;
    static int[] value;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        weight = new int[N+1];
        value = new int[N+1];
        dp = new int[N+1][K+1];

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());

            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        for(int n=1;n<=N;n++){
            for(int k=1;k<=K;k++){
                if(weight[n]<=k){  //배낭에 들어갈 수 있으면
                    //배낭에 안담았을때 가치와 담았을때 가치중 최댓값
                    dp[n][k] = Math.max(dp[n-1][k], dp[n-1][k-weight[n]]+value[n]);
                }else{
                    //현재 무게를 배낭에 담을 수 없으면
                    dp[n][k] = dp[n-1][k];
                }
            }
        }

//        for(int i=1;i<=N;i++){
//            for(int j=1;j<=K;j++){
//                System.out.print(dp[i][j]+" ");
//            }
//            System.out.println();
//        }

        System.out.println(dp[N][K]);

    }



}

package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_12865_평범한배낭 {
    //백준 골드5
    //스터디 - dp
    //기존 부분집합 문제로 풀었을 때 시간초과


    static int N;  //N : 물품의 수
    static int K;  //K : 준서가 버틸 수 있는 무게
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
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            weight[i] = w;
            value[i] = v;
        }

        for(int i=1;i<=N;i++){
            for(int j=1;j<=K;j++){
                //현재 weight를 가진 물건을 가방에 넣을 수 있는 경우(wegith가 K보다 작은 경우)
                if(weight[i]<=j){
                    //
                    dp[i][j] = Math.max(value[i]+dp[i-1][j-weight[i]], dp[i-1][j]);
                }else{
                    //현재 weight를 가진 물건을 가방에 넣을 수 없는 경우
                    dp[i][j] = dp[i-1][j];
                }
            }
        }


        System.out.println(dp[N][K]);
    }
}

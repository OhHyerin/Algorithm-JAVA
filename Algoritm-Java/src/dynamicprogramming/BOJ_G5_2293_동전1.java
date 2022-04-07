package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_2293_동전1 {

    static int N; //동전 종류
    static int K; //가치의 합
    static int[] coin;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        coin = new int[N];
        dp = new int[K+1];
        for(int i=0;i<N;i++){
            int temp = Integer.parseInt(br.readLine());
            if(temp<=K){
                coin[i] = temp;
            }
        }

        for(int c=0;c<coin.length;c++){
            int curCoin = coin[c];
//            System.out.println(curCoin);
            dp[curCoin] += 1;
            for(int i=curCoin+1;i<=K;i++){
                dp[i] += dp[i-curCoin];
            }
//            System.out.println(Arrays.toString(dp));
        }

        System.out.println(dp[K]);
    }
}

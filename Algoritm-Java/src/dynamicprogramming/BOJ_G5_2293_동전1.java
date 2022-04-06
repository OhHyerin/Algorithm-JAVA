package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
            coin[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(Arrays.toString(coin));


    }
}

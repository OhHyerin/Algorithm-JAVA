package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_9465_스티커 {
    //백준 실버1
    //스터디 - DP

    //dp배열로 현재 위치까지 계산될 수 있는 값 중 가장 큰 값을 저장
    //현재 위치 기준으로 [row-1][col-1]과 [row-1][col-2]의 누적값 중 큰 값을 [row][col]에 저장
    //N위치까지 다 돌았으면 마지막에
    //dp[0][N]과 dp[1][N]중 큰 값을 답으로 채택

    static int N;
    static int [][] stickers;
    static int [][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            stickers = new int[2][N+1];
            dp = new int[2][N+1];

            for(int i=0; i<2;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=1;j<=N;j++){
                    stickers[i][j] = Integer.parseInt(st.nextToken());
                    dp[i][j] = stickers[i][j];
                }
            }

            //---- dp배열 크기를 N개로 했을 때-----//
            //---- 테케에서 N의 크기가 1또는 2일 때 인덱스 오류남----//
//            dp[0][1] = stickers[0][1]+stickers[1][0];
//            dp[1][1] = stickers[1][1]+stickers[0][0];

            for(int j=2;j<=N;j++){
                dp[0][j] = stickers[0][j]+Math.max(dp[1][j-1], dp[1][j-2]);
                dp[1][j] = stickers[1][j]+Math.max(dp[0][j-1], dp[0][j-2]);
            }


            sb.append(Math.max(dp[0][N], dp[1][N])).append("\n");
        } //t
        System.out.println(sb);

    }


}

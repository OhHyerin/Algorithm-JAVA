package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_9465_��ƼĿ {
    //���� �ǹ�1
    //���͵� - DP

    //dp�迭�� ���� ��ġ���� ���� �� �ִ� �� �� ���� ū ���� ����
    //���� ��ġ �������� [row-1][col-1]�� [row-1][col-2]�� ������ �� ū ���� [row][col]�� ����
    //N��ġ���� �� �������� ��������
    //dp[0][N]�� dp[1][N]�� ū ���� ������ ä��

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

            //---- dp�迭 ũ�⸦ N���� ���� ��-----//
            //---- ���ɿ��� N�� ũ�Ⱑ 1�Ǵ� 2�� �� �ε��� ������----//
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

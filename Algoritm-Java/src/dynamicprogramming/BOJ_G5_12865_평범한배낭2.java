package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_12865_����ѹ賶2 {
    //dp, �賶����

    static int N;  //��ǰ�� ��
    static int K;  //��ƿ���ִ� ����
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
                if(weight[n]<=k){  //�賶�� �� �� ������
                    //�賶�� �ȴ������ ��ġ�� ������� ��ġ�� �ִ�
                    dp[n][k] = Math.max(dp[n-1][k], dp[n-1][k-weight[n]]+value[n]);
                }else{
                    //���� ���Ը� �賶�� ���� �� ������
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

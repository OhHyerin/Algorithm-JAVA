package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_12865_����ѹ賶 {
    //���� ���5
    //���͵� - dp
    //���� �κ����� ������ Ǯ���� �� �ð��ʰ�


    static int N;  //N : ��ǰ�� ��
    static int K;  //K : �ؼ��� ��ƿ �� �ִ� ����
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
                //���� weight�� ���� ������ ���濡 ���� �� �ִ� ���(wegith�� K���� ���� ���)
                if(weight[i]<=j){
                    //
                    dp[i][j] = Math.max(value[i]+dp[i-1][j-weight[i]], dp[i-1][j]);
                }else{
                    //���� weight�� ���� ������ ���濡 ���� �� ���� ���
                    dp[i][j] = dp[i-1][j];
                }
            }
        }


        System.out.println(dp[N][K]);
    }
}

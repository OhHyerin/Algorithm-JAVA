package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_4811_�˾� {
    //dp


    static int N;  //���� ������
    static long[][] dp = new long[31][31];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;  //0�� �ԷµǸ� ����

            sb.append(pill(N, 0)+"\n");
        }

        System.out.println(sb.toString());

    }

    public static long pill(int hole, int half){
        if(hole==0){
            //��ü �˾� ������ 0����
            return 1;
        }
        if(dp[hole][half]>0){
            //�˾��� ���� ����������
            return dp[hole][half];
        }
        dp[hole][half] = pill(hole-1, half+1);  //��ü �ϳ� ������ ���� �ٽ� ����
        if(half>0){
            dp[hole][half] += pill(hole, half-1);  //�ݰ�������
        }
        return dp[hole][half];
    }



}

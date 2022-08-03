package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_4811_알약 {
    //dp


    static int N;  //약의 개수수
    static long[][] dp = new long[31][31];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;  //0이 입력되면 종료

            sb.append(pill(N, 0)+"\n");
        }

        System.out.println(sb.toString());

    }

    public static long pill(int hole, int half){
        if(hole==0){
            //전체 알약 개수가 0개면
            return 1;
        }
        if(dp[hole][half]>0){
            //알약이 아직 남아있으면
            return dp[hole][half];
        }
        dp[hole][half] = pill(hole-1, half+1);  //전체 하나 꺼내서 반은 다시 넣음
        if(half>0){
            dp[hole][half] += pill(hole, half-1);  //반개꺼내기
        }
        return dp[hole][half];
    }



}

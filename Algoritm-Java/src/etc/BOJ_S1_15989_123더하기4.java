package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_S1_15989_123¥ı«œ±‚4 {
    //dp

    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){

            N = Integer.parseInt(br.readLine());
            dp = new int[N+1];

            if(N==1){
                sb.append(0).append("\n");
                break;
            }
            else if(N==2){
                sb.append(1).append("\n");
                break;
            }else if(N==3){
                sb.append(2).append("\n");
                break;
            }

//            dp[1] = 1;
//            dp[2] = 1;
//            dp[3] = 2;
//            for(int i=1;i<=3;i++){ //i : 1,2,3
//                dp[i+1] += 1;
//                for(int j=i+2;j<=N;j++){
//                    if(j-i<=0) continue;
//                    dp[j] += dp[j-i];
//                }
//            }

            Arrays.fill(dp, 1);
            dp[2] += 1;
            for(int i=3;i<=N;i++){
                dp[i] += dp[i-2];
            }

            dp[3] += 1;
            for(int i=4;i<=N;i++){
                dp[i] += dp[i-3];
            }

            sb.append(dp[N]).append("\n");

        }//t

        System.out.println(sb);

    }
}

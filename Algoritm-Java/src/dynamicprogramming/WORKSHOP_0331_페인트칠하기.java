package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WORKSHOP_0331_페인트칠하기 {
    //데일리 워크샵 연습문제1

    /*
    아파트를 각 층별로 파란색 또는 노란색 페인트로 칠하되 다음과 같은 규칙으로 칠하려고 한다.
    노란색은 인접한 두 층에 연속하여 사용할 수 있다.
    파란색을 인접한 두 층에 연속하여 사용할 수 없다.

    이와같은 규칙으로 층의 아파트를 칠할 수 있는 방법의 수를 f(n)이라 하면 다음 그림과 같이 f(1) = 2, f(2) = 3이다.
    f(8)은 얼마인가?
     */

    static int N;
    static int result;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new long[9];
        dp[1] = 2;
        dp[2] = 3;

        System.out.println(topDown(N));
        System.out.println(bottomUp(N));

    }

    private static long topDown(int n){
        if(dp[n]>0){
            return dp[n];
        }
       return dp[n] = topDown(n-1) + topDown(n-2);
    }

    private static long bottomUp(int n){
        long [] dp2 = new long[n+1];
        dp2[1] = 2;
        dp2[2] = 3;
        for(int i=3;i<=n;i++){
            dp2[i] = dp2[i-1] + dp2[i-2];
        }

        return dp2[n];
    }

}

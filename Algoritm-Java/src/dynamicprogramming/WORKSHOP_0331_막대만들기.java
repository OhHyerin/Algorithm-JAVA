package dynamicprogramming;

public class WORKSHOP_0331_막대만들기 {
    //데일리 워크샵 연습문제2

    /*
    1cm 짜리 파란 막대와 1cm짜리 노란 막대, 그리고 2cm짜리 빨간 막대가 있다.
    이 막대들을 연결하여 길이가 n cm인 막대를 만드는 방법의 수를 f(n)이라 하자.

    예를 들면 2cm 막대를 만드는 방법은
    (파란 막대, 파란 막대)
    (파란 막대, 노란 막대)
    (노란 막대, 파란 막대)
    (노란 막대, 노란 막대)
    (빨간 막대)
    5가지 이므로 f(2) = 5이다.
    f(6)의 값은?
     */

    //구해진 점화식 f(n) = 2*f(n-1) + f(n-2)

    static long[] memo;

    public static void main(String[] args) {
        memo = new long[7];
        memo[1] = 2;
        memo[2] = 5;

        System.out.println(topDown(6));
        System.out.println(bottomUp(6));
        System.out.println(bottomUp2(6));
    }

    private static long topDown(int n){
        if(memo[n]>0){
            return memo[n];
        }
        return topDown(n-1) * 2 + topDown(n-2);
    }

    //dp는 배열을 만들 때 배열에 무엇을 저장할 것인가를 잘 기억해야 함.
    private static long bottomUp(int n){
        long[] dp = new long[n+1];
        dp[1] = 2;
        dp[2] = 5;
        for(int i=3;i<=n;i++){
            dp[i] = 2*dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    //n cm를 만들기 위한 B, Y, R의 가지 수는?
    private static long bottomUp2(int n){
        int B=0, Y=1, R=2;
        long [][] dp = new long[n+1][3];
        dp[1][B] = 1;
        dp[1][Y] = 1;
        dp[1][R] = 0;

        dp[2][B] = dp[1][B] + dp[1][Y] + dp[1][R];
        dp[2][Y] = dp[1][B] + dp[1][Y] + dp[1][R];
        dp[2][R] = 1; //초기값인 상태

        for(int i=3;i<=n;i++){
            dp[i][B] = dp[i-1][B] + dp[i-1][Y] + dp[i-1][R];
            dp[i][Y] = dp[i-1][B] + dp[i-1][Y] + dp[i-1][R];
            dp[i][R] = dp[i-1][B] + dp[i-1][Y] + dp[i-1][R];
        }
        return dp[n][B] + dp[n][Y] + dp[n][R];
    }




}

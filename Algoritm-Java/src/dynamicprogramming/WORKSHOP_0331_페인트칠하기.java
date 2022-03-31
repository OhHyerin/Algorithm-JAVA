package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WORKSHOP_0331_����Ʈĥ�ϱ� {
    //���ϸ� ��ũ�� ��������1

    /*
    ����Ʈ�� �� ������ �Ķ��� �Ǵ� ����� ����Ʈ�� ĥ�ϵ� ������ ���� ��Ģ���� ĥ�Ϸ��� �Ѵ�.
    ������� ������ �� ���� �����Ͽ� ����� �� �ִ�.
    �Ķ����� ������ �� ���� �����Ͽ� ����� �� ����.

    �̿Ͱ��� ��Ģ���� ���� ����Ʈ�� ĥ�� �� �ִ� ����� ���� f(n)�̶� �ϸ� ���� �׸��� ���� f(1) = 2, f(2) = 3�̴�.
    f(8)�� ���ΰ�?
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

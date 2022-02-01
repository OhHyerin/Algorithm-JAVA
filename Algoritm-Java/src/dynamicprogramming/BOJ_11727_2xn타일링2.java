package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11727_2xn타일링2 {
	//백준
	//2xn 타일링 2
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());		
		long[] dp = new long[n+1];
		
		dp[1] = 1;
		if(n>1) {
			dp[2] = 3;
		}
		for(int i=3;i<=n;i++) {
			dp[i] = dp[i-1]+dp[i-2]*2;
			if(dp[i]>10007) {
				dp[i] %= 10007;
			}
		}
		
		//DP문제에서 종종 결과를 어떤 수로 나눈 결과를 출력하라는 내용이 들어가있는 경우가 많다.
		//이는 단지 결괏값이 굉장히 커질 수 있기 때문에 그런 것이다.
		System.out.println(dp[n]);

	}

}

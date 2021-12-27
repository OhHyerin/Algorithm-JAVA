package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class A9095 {
	//1,2,3더하기

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] tc = new int[n];
		int[] dp = new int[11];
		
		for(int i=0;i<n;i++) {
			tc[i] = Integer.parseInt(br.readLine());
		}
		Arrays.fill(dp,0);
		
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		for(int i=4;i<11;i++) { 
			//3번째 전부터 1번째 전까지의 경우의 수 합
			dp[i] = dp[i-1]+dp[i-2]+dp[i-3];
		}
		
		for(int i=1;i<=n;i++) {
			System.out.println(dp[tc[i-1]]);
		}
		
		
	}

}

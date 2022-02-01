package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1912_연속합 {
	//백준
	//연속합
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int result = 0;
		
		
		int[] arr = new int[n+1];
		int[] dp = new int[n+1];		
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		
		
		for(int i=1;i<=n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[1] = arr[1];
		int max = dp[1];
		
		for(int i=2;i<=n;i++) {
			dp[i] = Math.max(dp[i-1]+arr[i], arr[i]);
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(max);		
		
	}

}

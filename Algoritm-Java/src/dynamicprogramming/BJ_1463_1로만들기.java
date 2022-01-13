package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1463_1로만들기 {
	//백준
	//1로 만들기
	
	static int[] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		dp = new int[n+1];
		
		dp[1]=0;
		for(int i=2;i<=n;i++) {
			dp[i] = dp[i-1]+1; //1을 뺀다.
			if(i%3==0) { //3으로 나누어 떨어지면, 3으로 나눈다. 
				//1뺀것과 3으로 나눈것의 횟수 중 최소 선택, 그리고 횟수 1 증가
				dp[i] = Math.min(dp[i], dp[i/3]+1);
			}if(i%2==0) { //2로 나누어 떨어지면, 2로 나눈다.
				//1뺀것과 2로 나눈것의 횟수 중 최소 선택, 그리고 횟수 1 증가
				dp[i] = Math.min(dp[i], dp[i/2]+1);
			}
		}
		
		System.out.println(dp[n]);
		
	}

}

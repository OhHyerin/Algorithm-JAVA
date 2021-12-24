package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class A2579 {
//계단 오르기
	
	static Integer[] score;
	static Integer[] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int n = Integer.parseInt(br.readLine()); //n입력
		score = new Integer[n];  //계단 값 선언 및 입력
		for(int i=0;i<n;i++) {
			score[i] = Integer.parseInt(br.readLine());
		}
		
		dp = new Integer[n];
		Arrays.fill(dp, 0);
		dp[0] = score[0];  //dp첫 번째
		if(n>1) {  //n입력값 1이상일 때 (RoQ : 입력값 1일 시, 런타임에러)
			dp[1] = score[0]+score[1];  //dp두번째는 무조건 첫번째+두번째
			if(n>2) { //(RoQ : 입력값 2일 시, 런타임에러)
				dp[2] = Math.max(score[0]+score[2], score[1]+score[2]);
				for(int i=3;i<n;i++) {
					int stepbystep = dp[i-3]+score[i-1]+score[i]; //i-1과 i(현재시점 전에 이어짐)
					int jumpstep = dp[i-2]+score[i]; //i-2와 i (현재시점 전에 점프)
					dp[i] = Math.max(stepbystep, jumpstep);
				}
			}
		}
		
		System.out.println(dp[n-1]);
		
	}
	

}

package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class A1463 {
//1�� �����
	
	static int[] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		dp = new int[n+1];
		
		dp[1]=0;
		for(int i=2;i<=n;i++) {
			dp[i] = dp[i-1]+1; //1�� ����.
			if(i%3==0) { //3���� ������ ��������, 3���� ������. 
				//1���Ͱ� 3���� �������� Ƚ�� �� �ּ� ����, �׸��� Ƚ�� 1 ����
				dp[i] = Math.min(dp[i], dp[i/3]+1);
			}if(i%2==0) { //2�� ������ ��������, 2�� ������.
				//1���Ͱ� 2�� �������� Ƚ�� �� �ּ� ����, �׸��� Ƚ�� 1 ����
				dp[i] = Math.min(dp[i], dp[i/2]+1);
			}
		}
		
		System.out.println(dp[n]);
		
	}

}

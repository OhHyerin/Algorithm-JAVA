package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2579_��ܿ����� {
	//����
	//��� ������
	
	static Integer[] score;
	static Integer[] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int n = Integer.parseInt(br.readLine()); //n�Է�
		score = new Integer[n];  //��� �� ���� �� �Է�
		for(int i=0;i<n;i++) {
			score[i] = Integer.parseInt(br.readLine());
		}
		
		dp = new Integer[n];
		Arrays.fill(dp, 0);
		dp[0] = score[0];  //dpù ��°
		if(n>1) {  //n�Է°� 1�̻��� �� (RoQ : �Է°� 1�� ��, ��Ÿ�ӿ���)
			dp[1] = score[0]+score[1];  //dp�ι�°�� ������ ù��°+�ι�°
			if(n>2) { //(RoQ : �Է°� 2�� ��, ��Ÿ�ӿ���)
				dp[2] = Math.max(score[0]+score[2], score[1]+score[2]);
				for(int i=3;i<n;i++) {
					int stepbystep = dp[i-3]+score[i-1]+score[i]; //i-1�� i(������� ���� �̾���)
					int jumpstep = dp[i-2]+score[i]; //i-2�� i (������� ���� ����)
					dp[i] = Math.max(stepbystep, jumpstep);
				}
			}
		}
		
		System.out.println(dp[n-1]);
		
	}
	

}

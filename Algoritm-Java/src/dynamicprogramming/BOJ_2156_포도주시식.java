package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2156_�����ֽý� {
	//����
	//������ �ý�
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());  //n�Է�
		
		Integer[] wine = new Integer[n];  //wine�迭 ����
		Integer[] dp = new Integer[n]; //dp�迭 ����
		
		for(int i=0;i<n;i++) {  //wine�迭 �Է�
			wine[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.fill(dp, 0); //�ִ� ã�� ������ �ּڰ����� �ʱ�ȭ
		if(n>=1) {
			dp[0] = wine[0];
		}
		if(n>=2) {
			dp[1] = wine[0]+wine[1];
		}
		
		if(n>=3) {
			//index=2�� �� oox, oxo, xoo�� �� ���� ���
			int oox = wine[0]+wine[1];
			int oxo = wine[0]+wine[2];
			int xoo = wine[1]+wine[2];
			dp[2] = Math.max(oox, Math.max(oxo,xoo));
		}
		for(int i=3;i<n;i++) {
			//����������� ������ �� �ִ� 3���� ���
			int oox = dp[i-1]; 
			int oxo = dp[i-2]+wine[i];
			int xoo = dp[i-3]+wine[i-1]+wine[i];
			dp[i] = Math.max(oox, Math.max(oxo, xoo));
		}
		
		//dp�迭 �������� ���� �� �ִ� ���
		Arrays.sort(dp);
		System.out.println(dp[dp.length-1]);
		
		
	}

}

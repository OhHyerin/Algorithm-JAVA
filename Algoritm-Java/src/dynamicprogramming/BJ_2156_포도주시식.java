package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_2156_포도주시식 {
	//백준
	//포도주 시식
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());  //n입력
		
		Integer[] wine = new Integer[n];  //wine배열 선언
		Integer[] dp = new Integer[n]; //dp배열 선언
		
		for(int i=0;i<n;i++) {  //wine배열 입력
			wine[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.fill(dp, 0); //최댓값 찾는 문제니 최솟값으로 초기화
		if(n>=1) {
			dp[0] = wine[0];
		}
		if(n>=2) {
			dp[1] = wine[0]+wine[1];
		}
		
		if(n>=3) {
			//index=2일 때 oox, oxo, xoo의 세 가지 경우
			int oox = wine[0]+wine[1];
			int oxo = wine[0]+wine[2];
			int xoo = wine[1]+wine[2];
			dp[2] = Math.max(oox, Math.max(oxo,xoo));
		}
		for(int i=3;i<n;i++) {
			//현재시점에서 선택할 수 있는 3가지 경우
			int oox = dp[i-1]; 
			int oxo = dp[i-2]+wine[i];
			int xoo = dp[i-3]+wine[i-1]+wine[i];
			dp[i] = Math.max(oox, Math.max(oxo, xoo));
		}
		
		//dp배열 오름차순 정렬 후 최댓값 출력
		Arrays.sort(dp);
		System.out.println(dp[dp.length-1]);
		
		
	}

}

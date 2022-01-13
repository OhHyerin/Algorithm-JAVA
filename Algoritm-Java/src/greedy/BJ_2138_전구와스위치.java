package greedy;

import java.util.Arrays;
import java.util.Scanner;

public class BJ_2138_전구와스위치 {
	//백준
	
	static int[] input_o;  //현재 전구 상태_첫 번째 선택O
	static int[] input_x;  //현재 전구 상태_첫 번째 선택X
	static int[] output; //원하는 전구 상태
	static int n; //전구 갯수

	public static void main(String[] args) {
				
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		input_o = new int[n];   //input 배열 선언
		input_x = new int[n];   
		output = new int[n];  //output 배열 선언
		
		int count_o=0, count_x=0;
		
		String strInput = in.next();  //input 입력받기
		String strOutput = in.next(); //output 입력받기
		
		//input 배열에 String input 하나씩 대입
		for(int i=0;i<n;i++) {
			input_o[i] = strInput.charAt(i)-'0';
			input_x[i] = strInput.charAt(i)-'0';
			output[i] = strOutput.charAt(i)-'0';
			//charAt : String으로 저장된 문자열 중에서 
			//한글자만 선택해서 char타입으로 변환해주는 역할.
			//-'0' : 아스키코드 사용해서 char -> int 변환
			//-'0' : -48
		}
		
		switch_bulb(input_o, 0); //첫 번째 바꿈
		count_o++; //첫번째 바꿨으니까 count_o 하나 업
		
		for(int i=1;i<n;i++){
			if(input_o[i-1]!=output[i-1]) {
				switch_bulb(input_o, i);
				count_o++;
			}
			if(input_x[i-1]!=output[i-1]) {
				switch_bulb(input_x, i);
				count_x++;
			}
		}
		
		
		if(Arrays.equals(input_o, output) && Arrays.equals(input_x, output)) {
			//두 가지 경우 모두 가능할 때 둘 중 최솟값
			System.out.println(Math.min(count_o, count_x));
		}
		else if(Arrays.equals(input_o, output) && input_x!=output) {
			System.out.println(count_o);
		}
		else if(input_o!=output && Arrays.equals(input_x, output)) {
			System.out.println(count_x);
		}
		else {
			System.out.println("-1");
		}

	}
	
	
	public static void switch_bulb(int[] arr, int i) {
		if(i==n-1) {
			//마지막 전구 선택했을 때
			arr[i-1] = change(arr[i-1]);
			arr[i] = change(arr[i]);
		}else if(i==0){
			//첫 번째 전구 선택했을 때
			arr[i] = change(arr[i]);
			arr[i+1] = change(arr[i+1]);
		}
		else {
			arr[i-1] = change(arr[i-1]);
			arr[i] = change(arr[i]);
			arr[i+1] = change(arr[i+1]);
		}
	}
	
	public static int change(int x) {
		if(x==0) {
			x=1;
		}else {
			x=0;
		}
		return x;
	}

}

package greedy;

import java.util.Scanner;

public class BOJ_14916_거스름돈풀이2 {
	//백준

	public static void main(String[] args) {
		
		int n;
		int result=0;
		
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		
		while(n>0) {
			if(n%5==0) {  //5로 나눠지면
				n = n-5;  //n에서 5 빼고
				result++; //갯수 1 증가
			}else {       //5로 안나누어지면
				n = n-2;  //n에서 2 빼고
				result++; //갯수 1 증가
			}
		}
		
		if(n<0) {  //마지막에 0으로 안끝난 수(ex.1, 3)
			System.out.println(-1);
		}else {
			System.out.println(result);
		}
		
	}

}

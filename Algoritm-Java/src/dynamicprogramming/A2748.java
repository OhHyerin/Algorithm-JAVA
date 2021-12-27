package dynamicprogramming;

import java.util.Scanner;

public class A2748 {
	//피보나치 수2

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		long[] fibo = new long[91];
		
		fibo[0] = 0;
		fibo[1] = 1;
		for(int i=2;i<=90;i++) {
			fibo[i] = fibo[i-1]+fibo[i-2];
		}
		
		System.out.println(fibo[n]);

	}

}

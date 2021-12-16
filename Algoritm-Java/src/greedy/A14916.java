package greedy;

import java.util.Scanner;

public class A14916 {
	public static void main(String args[]) {

		int n; //거스름돈 액수
		int x=0, y=0; //x:5갯수, y:2갯수
		int result = 0; //x+y:동전갯수
		
		Scanner in = new Scanner(System.in); 
		n = in.nextInt();
		
		if(n%5==0) { //5의 배수
			x = n/5;
			result=x;
		}else if(n%2==0) {  //짝수일 때 
			if(n<10) {  //x가 0일 때 예외처리 (ex.6,8)
				x=0;
			}
			else { 
				x = n/5;
			}
			y = (n-5*x)/2;
			result=x+y;
		}else if(n%2!=0 && n>=5) { //홀수면서 5이상(예외처리 1,3)
			if(n<10) {
				x = n/5;
			}
			else {
				x = n/5-1;
			}
			y = (n-5*x)/2;
			result=x+y;
		}else{
			result=-1;
		}
		
		System.out.print(result);
	}
}

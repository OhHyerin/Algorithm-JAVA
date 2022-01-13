package greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class BJ_1758_알바생강호 {
	//백준

	public static void main(String[] args) {
		int n=0;
		Integer[] tip;  //Collections.reverseOrder()사용하기위해 int가 아닌 Integer형으로 선언한다.
		long result=0;  //최대값으로 넣었을 때 21억이 넘어가므로 int형이 아닌 long으로 선언해주어야 한다.
		
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		tip = new Integer[n];
		
		for(int i=0;i<n;i++) {
			tip[i] = in.nextInt();
		}
		
		Arrays.sort(tip, Collections.reverseOrder());
		//내림차순으로 정렬 후 받은등수 차감
		for(int i=0;i<n;i++) {  
			if(tip[i]-i >= 0) {
				result += tip[i]-i;
			}else {
				break;
			}
		}
		
		System.out.println(result);

	}

}

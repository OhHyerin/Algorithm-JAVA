package greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class BOJ_1758_�˹ٻ���ȣ {
	//����

	public static void main(String[] args) {
		int n=0;
		Integer[] tip;  //Collections.reverseOrder()����ϱ����� int�� �ƴ� Integer������ �����Ѵ�.
		long result=0;  //�ִ밪���� �־��� �� 21���� �Ѿ�Ƿ� int���� �ƴ� long���� �������־�� �Ѵ�.
		
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		tip = new Integer[n];
		
		for(int i=0;i<n;i++) {
			tip[i] = in.nextInt();
		}
		
		Arrays.sort(tip, Collections.reverseOrder());
		//������������ ���� �� ������� ����
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

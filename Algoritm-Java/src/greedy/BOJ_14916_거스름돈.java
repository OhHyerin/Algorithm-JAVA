package greedy;

import java.util.Scanner;

public class BOJ_14916_�Ž����� {
	//����
	public static void main(String args[]) {

		int n; //�Ž����� �׼�
		int x=0, y=0; //x:5����, y:2����
		int result = 0; //x+y:��������
		
		Scanner in = new Scanner(System.in); 
		n = in.nextInt();
		
		if(n%5==0) { //5�� ���
			x = n/5;
			result=x;
		}else if(n%2==0) {  //¦���� �� 
			if(n<10) {  //x�� 0�� �� ����ó�� (ex.6,8)
				x=0;
			}
			else { 
				x = n/5;
			}
			y = (n-5*x)/2;
			result=x+y;
		}else if(n%2!=0 && n>=5) { //Ȧ���鼭 5�̻�(����ó�� 1,3)
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

package greedy;

import java.util.Scanner;

public class BOJ_14916_�Ž�����Ǯ��2 {
	//����

	public static void main(String[] args) {
		
		int n;
		int result=0;
		
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		
		while(n>0) {
			if(n%5==0) {  //5�� ��������
				n = n-5;  //n���� 5 ����
				result++; //���� 1 ����
			}else {       //5�� �ȳ���������
				n = n-2;  //n���� 2 ����
				result++; //���� 1 ����
			}
		}
		
		if(n<0) {  //�������� 0���� �ȳ��� ��(ex.1, 3)
			System.out.println(-1);
		}else {
			System.out.println(result);
		}
		
	}

}

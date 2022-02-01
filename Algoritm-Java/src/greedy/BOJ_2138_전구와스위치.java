package greedy;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2138_�����ͽ���ġ {
	//����
	
	static int[] input_o;  //���� ���� ����_ù ��° ����O
	static int[] input_x;  //���� ���� ����_ù ��° ����X
	static int[] output; //���ϴ� ���� ����
	static int n; //���� ����

	public static void main(String[] args) {
				
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		input_o = new int[n];   //input �迭 ����
		input_x = new int[n];   
		output = new int[n];  //output �迭 ����
		
		int count_o=0, count_x=0;
		
		String strInput = in.next();  //input �Է¹ޱ�
		String strOutput = in.next(); //output �Է¹ޱ�
		
		//input �迭�� String input �ϳ��� ����
		for(int i=0;i<n;i++) {
			input_o[i] = strInput.charAt(i)-'0';
			input_x[i] = strInput.charAt(i)-'0';
			output[i] = strOutput.charAt(i)-'0';
			//charAt : String���� ����� ���ڿ� �߿��� 
			//�ѱ��ڸ� �����ؼ� charŸ������ ��ȯ���ִ� ����.
			//-'0' : �ƽ�Ű�ڵ� ����ؼ� char -> int ��ȯ
			//-'0' : -48
		}
		
		switch_bulb(input_o, 0); //ù ��° �ٲ�
		count_o++; //ù��° �ٲ����ϱ� count_o �ϳ� ��
		
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
			//�� ���� ��� ��� ������ �� �� �� �ּڰ�
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
			//������ ���� �������� ��
			arr[i-1] = change(arr[i-1]);
			arr[i] = change(arr[i]);
		}else if(i==0){
			//ù ��° ���� �������� ��
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

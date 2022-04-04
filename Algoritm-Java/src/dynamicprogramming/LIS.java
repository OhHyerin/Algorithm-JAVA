package dynamicprogramming;

import java.util.Scanner;

public class LIS {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); //������ ũ��
        int[] arr = new int[N];  //������ ���Ҹ� ����
        int[] LIS = new int[N];  //�ڽ��� ������ �ϴ� LIS����

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        int max = 0;  //�ش� ������ LIS �������
        for (int i = 0; i < N; i++) {  //��� ���ҿ� ���� �ڽ��� ������ �ϴ� LIS���� ���
            LIS[i] = 1; //�ڽ� ȥ�� LIS ������ ���� ���� 1�� �ʱ�ȭ
            for (int j = 0; j < i; j++) {  //ù ���Һ��� i���� �������� ��
                if (arr[j] < arr[i] && LIS[i] < LIS[j] + 1) {  //arr[j]<arr[i] : ���������� ���
                    LIS[i] = LIS[j] + 1;
                }
            }
            if (max < LIS[i]) max = LIS[i];
        }
        System.out.println(max);
    }
}

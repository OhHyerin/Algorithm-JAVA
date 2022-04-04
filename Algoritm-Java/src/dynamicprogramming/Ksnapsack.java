package dynamicprogramming;

import java.util.Scanner;

public class Ksnapsack {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int W = sc.nextInt();

        int[] weights = new int[N + 1];
        int[] profits = new int[N + 1];

        int[][] results = new int[N + 1][W + 1];

        //i=0�� 0�״�� ��
        for (int i = 1; i <= N; i++) {
            weights[i] = sc.nextInt();
            profits[i] = sc.nextInt();
        }
        int itemWeight = 0, itemBenefit = 0;

        //��� �����ۿ� ���ؼ� �ݺ�
        for (int item = 1; item <= N; item++) {
            itemWeight = weights[item]; //�� �������� ����
            itemBenefit = profits[item];  //�� �������� ��ġ

            //�� �������� 1���� ��ǥ���� ������ ��ġ���̺��� �����
            for (int weight = 1; weight <= W; weight++) {
                if (itemWeight <= weight) {
                    results[item][weight] = Math.max(results[item - 1][weight], itemBenefit + results[item - 1][weight - itemWeight]);
                } else {
                    results[item][weight] = results[item - 1][weight];
                }
            }
        }

        System.out.println(results[N][W]);
        sc.close();
    }
}

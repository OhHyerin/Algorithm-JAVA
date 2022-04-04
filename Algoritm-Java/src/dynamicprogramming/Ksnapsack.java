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

        //i=0은 0그대로 둠
        for (int i = 1; i <= N; i++) {
            weights[i] = sc.nextInt();
            profits[i] = sc.nextInt();
        }
        int itemWeight = 0, itemBenefit = 0;

        //모든 아이템에 대해서 반복
        for (int item = 1; item <= N; item++) {
            itemWeight = weights[item]; //현 아이템의 무게
            itemBenefit = profits[item];  //현 아이템의 가치

            //현 아이템의 1부터 목표무게 까지의 가치테이블을 만든다
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

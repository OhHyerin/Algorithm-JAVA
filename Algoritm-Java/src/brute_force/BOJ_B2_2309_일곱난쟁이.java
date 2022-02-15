package brute_force;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_B2_2309_일곱난쟁이 {
    //백준 브론즈2
    //백준 브론즈2 3040 백설공주와 일곱난쟁이와 문제 똑같음
    //IM 대비

    static int[] height = new int[9];
    static int sum;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(int i=0;i<9;i++){
            height[i] = sc.nextInt();
        }

        Arrays.sort(height);
        combination(7, new int[7], 0, 0);

        System.out.println(sb);
    }

    static boolean isFinished = false;
    private static void combination(int toChoose, int[] choosed, int startIdx, int sum){
        //base part
        if(isFinished) return;
        if(toChoose==0){
            if(sum==100){
                for(int i=0;i<choosed.length;i++){
                    sb.append(choosed[i]).append("\n");
                    isFinished = true;
                }
                return;
            }
            return;
        }
        //inductive part
        for(int i=startIdx;i<height.length;i++){
            choosed[choosed.length-toChoose] = height[i];
            combination(toChoose-1, choosed, i+1, sum+height[i]);
        }

    }
}

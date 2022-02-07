package brute_force;

import java.util.Scanner;

public class BOJ_6603_로또 {
    //백준 실버2
    //완전탐색 - 조합

    static int N;
    static int[] input;
    static boolean[] isSelected;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            N = sc.nextInt();
            if(N==0){
                break;
            }
            input = new int[N];
            isSelected = new boolean[N];
            for(int i=0;i<N;i++) {
                input[i] = sc.nextInt();
            }
            combination(0,0);
            System.out.println();
        }

    }

    public static void combination(int cnt, int start){
        if(cnt==6){
            for (int i = 0; i < N; i++) {
                if(isSelected[i])
                    System.out.print(input[i]+" ");
            }
            System.out.println();
            return;
        }

        for(int i=start;i<N;i++){
            isSelected[i] = true;
            combination(cnt+1, i+1);
            isSelected[i] = false;
        }
    }

}

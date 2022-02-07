package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ_5568_카드놓기 {
    //백준 실버5
    //완전탐색 - 순열

    static int N, K;
    static int[] card;
    static Set<String> numbers;
    static boolean[] isSelected;
    static int totalCount;
    static String str;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        card = new int[N];
        numbers = new HashSet<String>();
        isSelected = new boolean[N];

        for(int i=0;i<N;i++){
            card[i] = Integer.parseInt(br.readLine());
        }

        permutation(0, "0");
        System.out.println(numbers.size());

    }

    static void permutation(int cnt, String num){
        if(cnt==K){
            numbers.add(num);
            return;
        }

        for(int i=0;i<N;i++){
            if(isSelected[i]) continue;
            isSelected[i] = true;
            String temp = num;
            temp += String.valueOf(card[i]);
            permutation(cnt+1, temp);
            isSelected[i] = false;
        }

    }



}

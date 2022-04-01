package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_18429_근손실 {
    //순열, 완탐, 백트래킹


    static int N; //운동 키트 개수
    static int K; //몇일 동안?
    static int[] kit; //운동 키트
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        kit = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            kit[i] = Integer.parseInt(st.nextToken());
        }

        permutaion(0, new int[N], new boolean[N]);

        System.out.println(count);

    }

    private static void permutaion(int cnt, int[] selected, boolean[] isSelected){
        //base part
        if(cnt==N){
//            System.out.println(Arrays.toString(selected));
            int mus = 500;
            for(int i=0;i<selected.length;i++){
                mus += selected[i];
                mus -= K;
                if(mus<500){
//                    System.out.println("실패!");
                    return;
                }
            }
            count++;
//            System.out.println("성공! count : "+count);
            return;
        }

        //inductive part
        for(int i=0;i<kit.length;i++){
            if(isSelected[i]) continue;

            selected[cnt] = kit[i];
            isSelected[i] = true;
            permutaion(cnt+1, selected, isSelected);
            isSelected[i] = false;
        }
    }
}

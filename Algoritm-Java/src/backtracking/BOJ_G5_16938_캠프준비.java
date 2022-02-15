package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G5_16938_캠프준비 {
    //벡준 골드5 백트래킹

    static int N;  //N : 문제의 개수
    static int L, R;  //L, R : 문제 난이도의 합은 L보다 크거나 같고, R보다 작거나 같아야함
    static int X;  //X : 가장 어려운 문제와 가장 쉬운 문제의 난이도 차이는 X보다 크거나 같아야함
    static int[] level;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        level = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            level[i] = Integer.parseInt(st.nextToken());
        }
        //---------- 입력 완료--------------------
        Arrays.sort(level);
//        combination(N, new int[N] , 0);
//        com(0, 0, N);
        subSet(0, new boolean[N]);
        System.out.println(answer);

    }

    private static void subSet(int cnt, boolean[] checked){
        //base part
        if(cnt==N){
            int count = 0;
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            int sum = 0;
            for(int i=0;i<checked.length;i++){
                if(checked[i]){
//                    System.out.print(level[i]+" ");
                    max = Math.max(max, level[i]);
                    min = Math.min(min, level[i]);
                    sum+=level[i];
                    count++;
                }
            }
            if(count>=2){ //2문제 이상
                if(max-min>=X) {//가장 어려운문제와 쉬운문제 난이도차이가 X보다 크거나같음
                    if(sum>=L && sum<=R){ //난이도 합이 L~R사이
//                        System.out.println("cnt : "+count+", max : "+max+", min : "+min);
                        answer++;
                    }
                }
            }
            return;
        }
        //inductive part
        checked[cnt] = true;
        subSet(cnt+1, checked);
        checked[cnt] = false;
        subSet(cnt+1, checked);
    }
}

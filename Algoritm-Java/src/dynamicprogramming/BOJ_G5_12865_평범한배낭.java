package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_12865_����ѹ賶 {
    //���� ���5
    //���͵� - dp
    //���� �κ����� ������ Ǯ���� �� �ð��ʰ�


    static int N;  //N : ��ǰ�� ��
    static int K;  //K : �ؼ��� ��ƿ �� �ִ� ����
    static int[][] bags;
    static boolean[] selected;
    static int maxValue = Integer.MIN_VALUE;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        bags = new int[N][2];
        selected = new boolean[N];
        dp = new int[N][K];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            bags[i][0] = w;
            bags[i][1] = v;
        }

        subSet(0, selected , 0);

        System.out.println(maxValue);
    }

    private static void subSet(int cnt,boolean[] selected, int weightSum){
        //base part
        if(cnt==N){
            int sum = 0;
            int value = 0;
            for (int i = 0; i < N; i++) {
                if(selected[i]){
                    sum += bags[i][0];
                    value += bags[i][1];
                }
            }
            if(sum<=K){
                maxValue = Math.max(maxValue, value);
            }

            return;
        }


        //inductive part
        selected[cnt] = true;
        subSet(cnt+1, selected, weightSum+=bags[cnt][0]);
        selected[cnt] = false;
        subSet(cnt+1, selected, weightSum);
    }
}

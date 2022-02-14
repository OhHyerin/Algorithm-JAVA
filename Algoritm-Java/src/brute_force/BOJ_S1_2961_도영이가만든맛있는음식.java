package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_2961_도영이가만든맛있는음식 {
    //백준 실버1
    //부분집합

    static int N;
    static int[] sour;
    static int[] bitter;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        sour = new int[N];
        bitter = new int[N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            sour[i]= Integer.parseInt(st.nextToken());
            bitter[i] = Integer.parseInt(st.nextToken());
        }
        //----------입력완료-------------
//        System.out.println(Arrays.toString(sour));
//        System.out.println(Arrays.toString(bitter));

        tastySubSet(0, new boolean[N]);
        System.out.println(min);
    }

    private static void tastySubSet(int cnt, boolean[] checked) {
        //base part
        int totalSour = 1;
        int totalBitter = 0;
        if(cnt==N){
            for(int i=0;i<N;i++){
                if(checked[i]){
                    totalSour *= sour[i];
                    totalBitter += bitter[i];
                }
            }
            if(totalSour!=1)
                min = Math.min(min, Math.abs(totalSour-totalBitter));
            return;
        }

        //inductive part
        checked[cnt] = true;
        tastySubSet(cnt+1, checked);
        checked[cnt] = false;
        tastySubSet(cnt+1, checked);
    }
}

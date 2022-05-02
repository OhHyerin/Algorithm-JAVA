package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G5_2110_공유기설치 {

    static int N;  //집의 개수
    static int C;  //공유기의 개수
    static int[] home;  //집의 좌표
    static int maxDist = Integer.MIN_VALUE;

   public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        home = new int[N];

        for(int i=0;i<N;i++){
            home[i] = Integer.parseInt(br.readLine());
        }

       Arrays.sort(home);

        combination(0, 0, new int[C]);

       System.out.println(maxDist);

    }

    private static void combination(int cnt, int start, int[] selected){
       if(cnt==C){
           System.out.println(Arrays.toString(selected));

           int maxTemp = Integer.MIN_VALUE;
           for(int i=0;i<selected.length;i++){
//               maxTemp = Math.max();
           }

           maxDist = Math.max(maxDist, maxTemp);
           return;
       }

       for(int i=start;i<N;i++){
           selected[cnt] = i;
           combination(cnt+1, i+1, selected);
       }

    }

}

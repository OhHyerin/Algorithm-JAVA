package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_3980_선발명단 {

    static int[][] power;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){

            power = new int[12][12];

            for(int i=1;i<=11;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=1;j<=11;j++){
                    power[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            max = Integer.MIN_VALUE;

            permutation(0, new int[11], new boolean[11]);
            
            sb.append(max).append("\n");


        }  //t

    }

    private static void permutation(int cnt, int[] selected, boolean[] isSelected){
        if(cnt==11){
            int sum = 0;
            for(int i=0;i<selected.length;i++){
                sum += selected[i];
            }

            max = Math.max(max, sum);

            return;
        }

        for(int i=1;i<=11;i++){
            if(isSelected[i]) continue;
            if(power[cnt][i]==0) continue;

            selected[cnt] = i;
            isSelected[i] = true;

            permutation(cnt+1, selected, isSelected);
            isSelected[i] = false;
        }
    }
}

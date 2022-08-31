package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_3980_���߸�� {

    static int[][] power;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){

            power = new int[12][12];

            for(int i=1;i<=11;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=1;j<=11;j++){
                    power[i][j] = Integer.parseInt(st.nextToken());
                }
            }








        }  //t

    }

    private static void permutation(int cnt, int[] selected, boolean[] isSelected){
        if(cnt==11){



            return;
        }

        for(int i=1;i<=11;i++){
            if(isSelected[i]) continue;

            selected[cnt] = i;
            isSelected[i] = true;

            permutation(cnt+1, selected, isSelected);
            isSelected[i] = false;
        }
    }
}

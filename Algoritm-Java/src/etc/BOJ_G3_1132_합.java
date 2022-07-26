package etc;

import jdk.internal.util.xml.impl.Input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G3_1132_합 {
    // 그리디 알고리즘

    static int N;
    static String[] strs;
    static int[] alpha;
    static int[] sortAlpha;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++){
            String str = br.readLine();
            strs[i] = str;
            int extra = 1;
            for(int j=str.length()-1;j>=0;j--){
                char c = str.charAt(j);
                alpha[c-'A'] = extra*10;  //pow 사용
                extra *= 10;
            }
        }


    }


}

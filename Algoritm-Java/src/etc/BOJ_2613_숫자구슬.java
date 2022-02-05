package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2613_숫자구슬 {

    static int N, M;
    static int[] marble;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        marble = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            marble[i] = Integer.parseInt(st.nextToken());
        }



    }



}

package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D3_3307_최장증가부분수열 {
    //LIS

    static int N;
    static int[] input;
    static int[] LIS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");

            N = Integer.parseInt(br.readLine());
            input = new int[N];
            LIS = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                input[i] = Integer.parseInt(st.nextToken());
            }
            //--------------입력완료------------------

            int max = 0;
            for (int i = 0; i < N; i++) {
                LIS[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (input[j] < input[i] && LIS[i] < LIS[j] + 1) {
                        LIS[i] = LIS[j] + 1;
                    }
                }
                max = Math.max(max, LIS[i]);
            }
            sb.append(max).append("\n");

        }//t
        System.out.println(sb);
    }


}

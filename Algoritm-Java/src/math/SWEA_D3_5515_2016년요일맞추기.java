package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D3_5515_2016년요일맞추기 {


    static int[] day = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static int[] a = {4, 5, 6, 0, 1, 2, 3};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            sb.append("#").append(t).append(" ");

            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            int sum = 0;

            for(int i=1;i<m;i++){
                sum += day[i];
            }

            sum += d;
            sum -= 1;
            sb.append(a[sum%7]).append("\n");

        }
        System.out.println(sb);

    }

}

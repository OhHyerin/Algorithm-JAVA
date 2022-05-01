package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_5430_AC {

    /*
    R : 뒤집기
    D : 버리기
     */

    static char[] orders;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){

            String str = br.readLine();
            orders = str.toCharArray();

            int n = Integer.parseInt(br.readLine());
            numbers = new int[n];

            st = new StringTokenizer(br.readLine(), "[],");
            for(int i=0;i<n;i++){
                numbers[i] = Integer.parseInt(st.nextToken());
            }


        }//t

    }
}

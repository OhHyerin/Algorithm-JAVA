package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D4_8458_원점으로집합 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        outer : for(int t=1;t<=T;t++){
            sb.append("#").append(t).append(" ");
            int N = Integer.parseInt(br.readLine());
            int[] x = new int[N];
            int max = 0;
            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                int xi = Integer.parseInt(st.nextToken());
                int yi = Integer.parseInt(st.nextToken());
                x[i] = Math.abs(xi) + Math.abs(yi);
                max = Math.max(max, x[i]);
            }

            for(int i=1;i<N;i++){
                if((x[0]%2)!=(x[i]%2)){
                    sb.append(-1).append("\n");
                    continue outer;
                }
            }

            int sum = 0;
            for(int i=0;;i++){
                sum += i;
                if(sum>=max && (sum%2) == (max%2)){
                    sb.append(i).append("\n");
                    continue  outer;
                }
            }


        } //t
        System.out.println(sb);

    }
}

package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_D4_5604_구간합 {
    //[Professional]

    static long A, B;
    static long[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            sb.append("#").append(t).append(" ");

            st = new StringTokenizer(br.readLine());
            A = Long.parseLong(st.nextToken());
            B = Long.parseLong(st.nextToken());

            ans = new long[10];
            long point = 1; //자리수

            while(A<=B){
                while(B%10 != 9 && A<=B){
                    make(B, ans, point);
                    B--;
                }

                if(B<A){
                    break;
                }
                while(A%10 != 0 && A<=B){
                    make(A, ans, point);
                    A++;
                }

                A/=10;
                B/=10;
                for(int i=0;i<10;i++){
                    ans[i] += (B-A+1)*point;
                }
                point *= 10;
            }

            long sum = 0;
            for(int i=0;i<10;i++){
                sum += ans[i]*i;
            }
            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }

    private static void make(long x, long[] ans, long p){
        while(x>0){
            String str = String.valueOf(x);
            int tmp = str.charAt(str.length()-1)-'0';
            ans[tmp] += p;
            x /= 10;
        }
    }
}

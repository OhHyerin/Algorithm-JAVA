package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D3_5607_조합 {
    //[Professional] 조합

    static final int MOD = 1234567891;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            sb.append("#").append(t).append(" ");
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            long[] fact = new long[N+1];
            fact[1] = 1;
            for(int i=2;i<=N;i++){
                fact[i] = (fact[i-1] * i) % MOD;  //팩토리얼 미리 구해놓기
            }

            long b = (fact[R] * fact[N-R]) % MOD;
            b = makePow(b, MOD-2);

            sb.append((fact[N]*b)%MOD).append("\n");


        }//t
        System.out.println(sb);
    }

    private static long makePow(long a, int b){
        //a의 b승 구하기
        long temp = 0;
        if(b==0){
            return 1;
        }else if(b==1){
            return a;
        }
        if(b%2==0){
            temp = makePow(a, b/2);
            return (temp * temp)%MOD;
        }else{
            temp = makePow(a, b-1) % MOD;
            return (temp*a) % MOD;
        }
    }
}

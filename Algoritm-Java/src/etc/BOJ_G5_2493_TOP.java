package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_2493_TOP {
    //완탐
    //기존 배열로 풀면 61퍼쯤에서 시간초과 (큰 값이 들어오나봄)
    //stack이용

    static int N;
    static int[] top;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        top = new int[N+1];
        result = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            top[i] = Integer.parseInt(st.nextToken());
        }

        outer : for(int i=N;i>0;i--){
            for(int j=i-1;j>0;j--){
                if(top[i]<=top[j]){
                    result[i] = j;
                    continue outer;
                }
            }
        }

        for(int i=1;i<=N;i++){
            sb.append(result[i]).append(" ");
        }

        System.out.println(sb);

    }
}

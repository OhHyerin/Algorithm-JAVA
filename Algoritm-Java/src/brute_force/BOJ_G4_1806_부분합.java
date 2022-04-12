package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_1806_부분합 {
    //투포인터

    static int N;
    static int S;
    static int[] numbers;
    static int count = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        numbers = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }

//        System.out.println(Arrays.toString(numbers));

        int start = 0;
        int end = 0;
        int sum = 0;

        while (true) {
            if(sum>=S) {
                sum -= numbers[start++];
                count = Math.min(count, end - start + 1);
            }
            else if(end==N) break;
            else {
                sum += numbers[end++];
            }

        }


        System.out.println(count==Integer.MAX_VALUE? 0 : count);


    }
}

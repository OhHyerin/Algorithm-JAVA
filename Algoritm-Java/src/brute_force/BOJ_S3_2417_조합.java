package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BOJ_S3_2417_조합 {
    //백준 실버2
    // 단순 재귀로 조합을 풀면 시간초과가 난다.
    //nCr = n!/(n-r)!*r!을 적용해서 BigIntger을 사용한다.

    static int n, m;
    static int[] arr;
    static long cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        for(int i=0;i<arr.length;i++){
            arr[i] = n+i;
        }

        BigInteger n1 = BigInteger.ONE;
        BigInteger n2 = BigInteger.ONE;

        for(int i=0;i<m;i++){
            n1 = n1.multiply(new BigInteger(String.valueOf(n-i)));
            n2 = n2.multiply(new BigInteger(String.valueOf(i+1)));
        }
        BigInteger answer = n1.divide(n2);
        System.out.println(answer);

//        combination(m, new int[m], 0);
//        System.out.println(combination(n, m));
    }

//    private static int combination(int n, int m){
//        if(n==m || m==0) return 1;
//        return combination(n-1, m-1) + combination(n-1, m);
//    }

//    private static void combination(int toChoose, int[] choosed, int startIdx){
//        //base part
//        if(toChoose==0 || toChoose==n){
//            cnt++;
//            return;
//        }
//        //inductive part
//        for(int i=startIdx;i<arr.length;i++){
//            choosed[choosed.length-toChoose] = arr[i];
//            combination(toChoose-1, choosed, i+1);
//        }
//    }
}

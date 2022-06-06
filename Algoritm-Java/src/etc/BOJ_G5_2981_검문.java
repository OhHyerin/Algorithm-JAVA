package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G5_2981_검문 {
    //math, 유클리드호제법, 최대공약수

    static int N;
    static long[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        nums = new long[N];

        for(int i=0;i<N;i++){
            nums[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(nums);

        for(int i=2;i<nums[N-1];i++){
            long mod = nums[0]%i;
            boolean isSame = false;

            inner : for(int j=1;j<N;j++){
                if(nums[j]%i==mod){
                    isSame = true;
                }else{
                    isSame = false;
                    break inner;
                }
            }

            if(isSame){
                sb.append(i).append(" ");
            }
        }

        System.out.println(sb);

    }
}

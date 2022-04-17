package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_20437_문자열게임2 {

    /*
    1. 알파벳 소문자로 이루어진 문자열 W가 주어진다.
    2. 양의 정수 K가 주어진다.
    3. 어떤 문자를 정확히 K개 포함하는 가장 짧은 연속 문자열의 길이를 구한다.
    4. 어떤 문자를 정확히 K를 포함하고,
        문자열의 첫 번째와 마지막 글자가 해당 문자로 가장 긴 연속 문자열의 길이를 구한다.
     */

    static String W;
    static int K;
    static char[] chars;
    static int[] alpha;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){

            W = br.readLine();
            K = Integer.parseInt(br.readLine());

            alpha = new int[26];
            chars = new char[W.length()];
            for(int i=0;i<W.length();i++){
                chars[i] = W.charAt(i);
                alpha[chars[i]-'a']++;
            }

//            System.out.println(Arrays.toString(chars));


            check();



        }//t
    }

    private static void check(){
        int start = 0;
        int end = 0;
        int count = Integer.MAX_VALUE;

        alpha[chars[0]-'a']++;
        int maxAlphaCount = 1;

        for(int i=0;i<chars.length;i++){
            if(alpha[chars[i]-'a']<K) break;

            start = i;
            end = i;
            int sumCount = 0;
            boolean isSame = false;

            while(true){
                if(sumCount>=K){

                }

            }

        }

    }
}

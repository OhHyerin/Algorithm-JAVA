package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_20437_���ڿ�����2 {

    /*
    1. ���ĺ� �ҹ��ڷ� �̷���� ���ڿ� W�� �־�����.
    2. ���� ���� K�� �־�����.
    3. � ���ڸ� ��Ȯ�� K�� �����ϴ� ���� ª�� ���� ���ڿ��� ���̸� ���Ѵ�.
    4. � ���ڸ� ��Ȯ�� K�� �����ϰ�,
        ���ڿ��� ù ��°�� ������ ���ڰ� �ش� ���ڷ� ���� �� ���� ���ڿ��� ���̸� ���Ѵ�.
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

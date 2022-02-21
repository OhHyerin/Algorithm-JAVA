package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G5_1759_암호만들기 {
    //백준 골드5

    static int n, m;
    static char[] input;
    static StringBuilder sb = new StringBuilder();
    static boolean isVowel;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        input = new char[m];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<m;i++){
            input[i] = st.nextToken().charAt(0);
        }


        Arrays.sort(input);
//        System.out.println(Arrays.toString(input));

        combination(n, new char[n], 0);
        System.out.println(sb.toString());
    }

    static void combination(int toChoose, char[] choosed, int startIdx){
        //base part
        cnt=0;
        isVowel = false;
        if(toChoose==0){
            for(int i=0;i<choosed.length;i++) {
//                System.out.println("i : "+i+"   choosed[i] : "+choosed[i]);
                if(check(choosed[i])){
                    isVowel = true;
                }
                else{
                    cnt++;
                }
            }
            if(isVowel && cnt>=2) {
                for (int i = 0; i < choosed.length; i++) {
                    sb.append(choosed[i]);
                }
                sb.append("\n");
            }

            return;
        }

        //inductive part
        for(int i=startIdx;i<input.length;i++){
            choosed[choosed.length-toChoose] = input[i];
            combination(toChoose-1, choosed, i+1);
        }
    }

    static boolean check(char c){
        if(c=='a' || c=='e' || c=='i' || c=='o' || c=='u')
            return true;
        return false;
    }

}

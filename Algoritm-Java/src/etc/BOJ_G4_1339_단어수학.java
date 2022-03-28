package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G4_1339_단어수학 {
    //백준 골드4
    //스터디 - 순조부

    static int N;  //입력받을 문자열의 개수
    static String[] strs;  //입력받는 문자열
    static int diffCharCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        strs = new String[N];

        for(int i=0;i<N;i++){
            strs[i] = br.readLine();
        }

        int[] chars = new int[26];
        for(int i=0;i<N;i++){
            for(int j=0;j<strs[i].length();j++){
                chars[strs[i].charAt(j)-'A']++;
            }
        }

        for(int i=0;i<26;i++){
//            System.out.println(chars[i]);
            if(chars[i]!=0){
                diffCharCount++;
            }
        }



    }
}

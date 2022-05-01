package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

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
    static HashMap<Character, ArrayList<Integer>> hashMap;
    static int minStrLength = Integer.MAX_VALUE; //가장 짧은 연속 문자열 길이
    static int maxStrLength = Integer.MIN_VALUE; //가장 긴 연속 문자열의 길이
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

            hashMap = new HashMap<>();
            for(int i=0;i<26;i++){
                hashMap.put((char)(i+'a'), new ArrayList<>());  //각 알파벳 hashMap에 Key로 추가
            }

            for(int i=0;i<W.length();i++){
                hashMap.get(W.charAt(i)).add(i);  //hashMap에서 각 Character를 찾아 리스트에 위치를 value를 담아 추가
            }

//            System.out.println(Arrays.toString(chars));


            check();

            if(minStrLength==Integer.MAX_VALUE){
                sb.append("-1\n");
                continue;
            }
            sb.append(minStrLength).append(" ").append(maxStrLength).append("\n");

        }//t
        System.out.println(sb);
    }

    private static void check(){
        for(int i=0;i<26;i++){
            char key = (char)(i+'a');
            ArrayList<Integer> list = hashMap.get(key);  //각 char가 포함되어있는 list(위치값) 받아오기

            if(list.size()<K) continue; //연결되어있는 위치값의 개수(list의 크기)가 K보다 작으면 continue;
            for(int j=0;j<list.size();j++){  //list의 크기가 K보다 크거나 같으면

            }
        }
    }

}

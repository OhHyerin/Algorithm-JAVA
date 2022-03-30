package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class BOJ_G4_1339_단어수학 {
    static int N;  //입력받을 문자열의 개수
    static String[] strs;  //입력받는 문자열
    static int diffCharCount = 0;
    static int[] alpa;
    static char[] chars;
    static int[] ints;
    static int maxNum = Integer.MIN_VALUE;
    static HashSet<Character> hashSet;  //중복되는 char 제거하기위해 HashSet사용


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        strs = new String[N];
        alpa = new int[26];
        hashSet = new HashSet<>();

        for(int i=0;i<N;i++){
            strs[i] = br.readLine();
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<strs[i].length();j++){
                alpa[strs[i].charAt(j)-'A']++;
                hashSet.add(strs[i].charAt(j));
            }
        }

        System.out.println(Arrays.toString(alpa));

        diffCharCount = hashSet.size();
        chars = new char[diffCharCount];
        ints = new int[diffCharCount];

        int index = 0;
        Iterator iter = hashSet.iterator();
        while(iter.hasNext()){
            chars[index] = (char)iter.next();
            index++;
        }


        Arrays.sort(alpa);
        System.out.println(Arrays.toString(alpa));



        System.out.println(Arrays.toString(chars));
        System.out.println(Arrays.toString(ints));

//        System.out.println(maxNum);

    }
}

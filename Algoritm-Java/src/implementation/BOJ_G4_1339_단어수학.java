package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class BOJ_G4_1339_단어수학 {
    //백준 골드4
    //스터디 - 순조부

    static int N;  //입력받을 문자열의 개수
    static String[] strs;  //입력받는 문자열
    static int diffCharCount = 0;
    static HashSet<Character> hashSet;  //중복되는 char 제거하기위해 HashSet사용
    static char[] chars;
    static int maxNum = Integer.MIN_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        strs = new String[N];
        hashSet = new HashSet<>();

        for(int i=0;i<N;i++){
            strs[i] = br.readLine();
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<strs[i].length();j++){
                hashSet.add(strs[i].charAt(j));
            }
        }
        diffCharCount = hashSet.size();
//        System.out.println(diffCharCount);
        chars = new char[diffCharCount];

        int index = 0;
        Iterator iter = hashSet.iterator();
        while(iter.hasNext()){
            chars[index] = (char)iter.next();
            index++;
        }

//        System.out.println(Arrays.toString(chars));

//        combination(0, 0, new int[diffCharCount]);]
        int[] in = new int[diffCharCount];
        int full = 9;
        for(int i=0;i<diffCharCount;i++){
            in[i] = full;
            full--;
        }

        permutation(0,new int[diffCharCount], in, new boolean[diffCharCount]);

        System.out.println(maxNum);

    }


    private static void permutation(int cnt,int[] matched, int[] input, boolean[] isSelected){
        if(cnt==diffCharCount){
//            System.out.println(Arrays.toString(matched));
            HashMap<Character, Integer> hashMap = new HashMap<>();
            for(int i=0;i<diffCharCount;i++){
                hashMap.put(chars[i], matched[i]);
            }

            int result[] = new int[strs.length];

            for(int i=0;i<strs.length;i++){
                for(int j=0;j<strs[i].length();j++){
                   result[i] = result[i]*10 + hashMap.get(strs[i].charAt(j));
                }
            }

            int numResult = 0;
            for(int i=0;i<result.length;i++){
//                System.out.println(result[i]);
                numResult += result[i];
            }
            maxNum = Math.max(maxNum, numResult);
            return;
        }

        //inductive part
        for(int i=0;i<input.length;i++){
            if(isSelected[i]) continue;

            matched[cnt] = input[i];
            isSelected[i] = true;
            permutation(cnt+1, matched, input, isSelected);
            isSelected[i] = false;
        }
    }
}

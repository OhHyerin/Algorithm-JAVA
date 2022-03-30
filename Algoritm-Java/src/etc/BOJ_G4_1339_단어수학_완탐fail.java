package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;

public class BOJ_G4_1339_단어수학_완탐fail {
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

//        combination(0, 0, new int[diffCharCount]);
        int[] in = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        permutation(0,new int[diffCharCount], new boolean[10]);

        System.out.println(maxNum);

    }

//    private static void combination(int cnt, int start, int[]selected){
//        if(cnt==diffCharCount){
//            System.out.println(Arrays.toString(selected));
//            permutation(0, selected, new int[diffCharCount], new boolean[diffCharCount]);
//            return;
//        }
//
//        for(int i=start;i<10;i++){
//            selected[cnt] = i;
//            combination(cnt+1, i+1, selected);
//        }
//
//    }

    private static void permutation(int cnt,int[] matched, boolean[] isSelected){
        if(cnt==diffCharCount){
//            System.out.println(Arrays.toString(matched));
            String result[] = new String[N];
            for(int i=0;i<result.length;i++){
                result[i] = "";
            }

            for(int i=0;i<strs.length;i++){
                for(int j=0;j<strs[i].length();j++){
                    for(int c=0;c<diffCharCount;c++){
                        if(strs[i].charAt(j)==chars[c]){
                            result[i] += String.valueOf(matched[c]);
                        }
                    }
                }
            }

            int numResult = 0;
            for(int i=0;i<result.length;i++){
//                System.out.println(result[i]);
                numResult += Integer.parseInt(result[i]);
            }
            maxNum = Math.max(maxNum, numResult);
            return;
        }

        //inductive part
        for(int i=0;i<10;i++){
            if(isSelected[i]) continue;

            matched[cnt] = i;
            isSelected[i] = true;
            permutation(cnt+1, matched, isSelected);
            isSelected[i] = false;
        }
    }
}

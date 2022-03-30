package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G4_1339_단어수학 {
    static int N;  //입력받을 문자열의 개수
    static String[] strs;  //입력받는 문자열
    static int diffCharCount = 0;
    static char[] chars;
    static int[] ints;
    static int maxNum = Integer.MIN_VALUE;
    static HashSet<Character> hashSet;  //중복되는 char 제거하기위해 HashSet사용


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
        chars = new char[diffCharCount];
        ints = new int[diffCharCount];

        int index = 0;
        Iterator iter = hashSet.iterator();
        while(iter.hasNext()){
            chars[index] = (char)iter.next();
            index++;
        }
        for(int i=0;i<strs.length;i++){
            for(int j=0;j<strs[i].length();j++){
                char temp = strs[i].charAt(j);
                for(int c=0;c<diffCharCount;c++){
                    if(chars[c]==temp){
                        ints[c]++;
                    }
                }
            }
        }

//        System.out.println(Arrays.toString(chars));
//        System.out.println(Arrays.toString(ints));

        Queue<Chars> queue = new PriorityQueue<>();
        for(int i=0;i<diffCharCount;i++){
            queue.add(new Chars(chars[i], ints[i]));
        }
//        System.out.println(queue);

        ArrayList<Chars> matching = new ArrayList<>();
        for(int i=9;i>=(10-diffCharCount);i--){
            Chars cur = queue.poll();
            matching.add(new Chars(cur.ch, cur.count, i));
        }

//        System.out.println(matching);

        String[] result = new String[strs.length];
        for(int i=0;i<strs.length;i++){
            result[i] = "";
            for(int j=0;j<strs[i].length();j++){
                char temp = strs[i].charAt(j);
                for(int c=0;c<matching.size();c++){
                    if(matching.get(c).ch==temp){
                        result[i] += String.valueOf(matching.get(c).num);
                    }
                }
            }
        }

        int answer = 0;
        for(int i=0;i<strs.length;i++){
                answer += Integer.parseInt(result[i]);
        }

        System.out.println(answer);
//        System.out.println(maxNum);

    }

    static class Chars implements Comparable<Chars>{
        char ch;
        int count;
        int num;

        public Chars(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }

        public Chars(char ch, int count, int num) {
            this.ch = ch;
            this.count = count;
            this.num = num;
        }

        @Override
        public int compareTo(Chars o) {
            return -(count-o.count);
        }

        @Override
        public String toString() {
            return "Chars{" +
                    "ch=" + ch +
                    ", count=" + count +
                    ", num=" + num +
                    '}';
        }
    }
}

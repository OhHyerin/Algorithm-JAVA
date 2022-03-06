package data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_S5_1181_단어정렬 {
    //백준 실버5
    //오늘의문제

    //중복값 허용 안하므로 HashSet으로 입력받은 후
    //ArrayList로 변환 후 Collection.sort사용

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        HashSet<String> set = new HashSet<>(); //String과 String길이

        for(int i=0;i<n;i++){
            String str = br.readLine();
            set.add(str);
        }

        List<String> list = new ArrayList<>(set);

        Collections.sort(list, new Comparator<String>(){

            @Override
            public int compare(String o1, String o2) {
                if(o1.length()!=o2.length()){  //길이가 다르면 length로 비교
                    return o1.length()-o2.length();
                }else{  //길이가 같으면 사전순으로 비교
                    return o1.compareTo(o2);
                }
            }
        });

        for(String word:list){
            sb.append(word).append("\n");
        }

        System.out.println(sb);

    }
}

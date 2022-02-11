package data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_S5_1158_요세푸스문제_재귀 {
    //LinkedList 재귀로
    //시간, 메모리 최적화

    static StringBuilder sb ;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        list = new LinkedList<>();
        for(int i=1;i<=n;i++){
            list.add(i);
        }
        sb.append("<");
        pop(k-1, k-1);
        sb.setLength(sb.length()-2);
        sb.append(">");
        System.out.println(sb);

    }
    private static void pop(int idx, int range){
        sb.append(list.get(idx)).append(", ");
        list.remove(idx);
        if(list.isEmpty())
            return;
        pop((idx+range)%list.size(), range);
    }
}

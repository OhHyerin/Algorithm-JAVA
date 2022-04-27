package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G5_3079_입국심사 {
    //이분탐색

    static int N; //입국심사대 개수
    static int M; //사람 수
    static List<Integer> list;  //입국심사대

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();

        for(int i=0;i<N;i++){
            list.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(list); //시간 짧은 순서대로 정렬



    }
}

package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G5_2212_센서 {
    //그리디

    static int N;
    static int K;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        list = new ArrayList<>();
        for(int i=0;i<N;i++){
            list.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(list);  //센서 좌표 오름차순순



   }
}

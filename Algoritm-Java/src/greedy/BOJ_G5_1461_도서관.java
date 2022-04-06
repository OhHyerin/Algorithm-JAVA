package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G5_1461_도서관 {
    //정렬, 그리디 알고리즘

    static int N; // 책의 개수
    static int M; // 한 번에 들 수 있는 책의 개수
    static List<Integer> plist;
    static List<Integer> mlist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        plist = new ArrayList<>();
        mlist = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            int a = Integer.parseInt(st.nextToken());
            if(a>=0) plist.add(a);
            else mlist.add(a);
        }

    }



}

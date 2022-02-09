package divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_실2_18222_투에모스문자열 {
    //이분탐색
    //numberformat부터

    static int K;
    static ArrayList<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        list.add(0);
        makeString(1);

        System.out.println(list.get(K-1));

    }

    static void makeString(int cnt){
        if(cnt>=K) return;

        for(int i=0;i<cnt;i++){
            list.add(Math.abs(list.get(i)-1));
        }

        makeString(cnt*2);
    }
}

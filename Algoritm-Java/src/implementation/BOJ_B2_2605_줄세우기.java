package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_B2_2605_줄세우기 {
    //백준 브론즈2
    //IM 대비


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<n;i++){
            list.add(i+1);
        }

        for(int i=1;i<=n;i++){
            int v = Integer.parseInt(st.nextToken());
            int temp = list.remove(i-1);
            list.add(i-v-1, temp);
        }

        for(int i=0;i<n;i++){
            System.out.print(list.get(i)+" ");
        }


    }
}

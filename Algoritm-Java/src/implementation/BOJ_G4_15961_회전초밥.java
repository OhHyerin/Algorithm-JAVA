package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_G4_15961_회전초밥 {

    static int N; // 회전 초밥 벨트에 놓인 접시의 수
    static int D; // 초밥의 가짓수
    static int K; // 연속해서 먹는 접시의 수
    static int C; // 쿠폰 번호
    static ArrayList<Integer> belt;
    static int[] select;
    static int maxCount = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        //        belt = new int[N];
        belt = new ArrayList<>();
        select = new int[D+1];

        for (int i = 0; i < N; i++) {
            //            belt[i] = Integer.parseInt(br.readLine());
            belt.add(Integer.parseInt(br.readLine()));
        }
        for(int i=0;i<K-1;i++){
            int temp = belt.get(i);
            belt.add(temp);
        }

        //        System.out.println(belt);

        pick();

        System.out.println(maxCount);
    }


    public static void pick() {
        int count = 0;
        for(int i=0;i<K;i++){
            if(select[belt.get(i)]==0) count++;
            select[belt.get(i)]++;
        }

        maxCount = count;

        for(int i=1;i<N;i++){
            if(maxCount<=count){
                if(select[C]==0){
                    maxCount = count+1;
                }else{
                    maxCount = count;
                }
            }

            select[belt.get(i-1)]--;
            if(select[belt.get(i-1)]==0) count--;
            if(select[belt.get(i+K-1)]==0) count++;
            select[belt.get(i+K-1)]++;
            //            System.out.println("i : "+i+" count : "+count);
        }


    }

}

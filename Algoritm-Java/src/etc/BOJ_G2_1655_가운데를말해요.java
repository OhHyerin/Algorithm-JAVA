package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ_G2_1655_가운데를말해요 {

    static int N;
    static Queue<Integer> maxQueue;
    static Queue<Integer> minQueue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        maxQueue = new PriorityQueue<>(((o1, o2) -> o2-o1));  //내림차순 (큰수부터)
        minQueue = new PriorityQueue<>(((o1, o2) -> o1-o2));  //오름차순 (작은수부터)

        for(int i=0;i<N;i++){
            int n = Integer.parseInt(br.readLine());


        }

        System.out.println(sb);

    }
}

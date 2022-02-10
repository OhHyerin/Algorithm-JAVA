package data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S5_1158_요세푸스문제 {
    //백준 실버 5

    static int N, K;
    static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        sb.append("<");
        queue = new LinkedList<>();
        for(int i=1;i<=N;i++){
            queue.offer(i);
        }

        while(!queue.isEmpty()){
            // K-1번째까지 큐 뒤로 보내기
            for(int i=0;i<K-1;i++){
                queue.offer(queue.poll());
            }
            //K번째 값은 poll하기
            sb.append(queue.poll()+", ");
        }
        sb.setCharAt(sb.length()-2, '>');
//        sb.append(">");
        System.out.println(sb);


    }

}

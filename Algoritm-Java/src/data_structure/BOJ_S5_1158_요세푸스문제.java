package data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S5_1158_�似Ǫ������ {
    //���� �ǹ� 5

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
            // K-1��°���� ť �ڷ� ������
            for(int i=0;i<K-1;i++){
                queue.offer(queue.poll());
            }
            //K��° ���� poll�ϱ�
            sb.append(queue.poll()+", ");
        }
        sb.setCharAt(sb.length()-2, '>');
//        sb.append(">");
        System.out.println(sb);


    }

}

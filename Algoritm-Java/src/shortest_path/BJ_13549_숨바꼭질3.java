package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BJ_13549_���ٲ���3 {
    //���� (���5)

    //ť�� ���ο� ��带 ���� �� ����
    //������ ������ ������������ �����ϰ�,
    //���� ����� ���� ��带 ������ �ִܰŸ��� �����Ѵ�.
    static int n, k;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");
        //n : �����̰� �ִ� ��ġ
        n = Integer.parseInt(str[0]);
        //k : ������ �ִ� ��ġ
        k = Integer.parseInt(str[1]);
        distance = new int[100001];
        System.out.println(dijkstra(n, k));

    }

    static int dijkstra(int start, int end){
        PriorityQueue<Node> queue= new PriorityQueue<>();
        Arrays.fill(distance, 10000);

        Node sNode = new Node();
        //������ ù����� start�� 0��
        sNode.index = start;
        sNode.sec = 0;
        //ù ��� ť�� �߰�
        queue.offer(sNode);
        distance[start] = 0; //�Ÿ�0

        while(!queue.isEmpty()){
            //ť�� �� �� ����
            Node curNode = queue.poll();
            if(distance[curNode.index]<curNode.sec)
                continue; //���� ��忡 ����Ǿ��ִ� �ð�(�Ÿ�)�� �� ������ �н�

            //deltas : 3���� ����
            int[] deltas = new int[]{curNode.index-1, curNode.index+1, curNode.index*2};

            for(int i=0;i<deltas.length;i++){
                if(deltas[i]<0 || deltas[i]>10000) continue; //���� ����� �н�
                Node nextNode = new Node();
                nextNode.index = deltas[i];
                nextNode.sec = (i==0 || i==1)? 1:0;
                if(distance[nextNode.index]>distance[curNode.index]+ nextNode.sec){
                    queue.offer(nextNode);
                    distance[nextNode.index]=distance[curNode.index]+ nextNode.sec;
                }
            }

        }
        return distance[end];
    }


    static class Node implements Comparable<Node> {
        private int index;
        private int sec;


        @Override
        public int compareTo(Node o) {
            return sec-o.sec;
        }

    }

}

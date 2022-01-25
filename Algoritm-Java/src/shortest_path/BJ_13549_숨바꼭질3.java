package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BJ_13549_숨바꼭질3 {
    //백준 (골드5)

    //큐에 새로운 노드를 넣을 때 마다
    //노드들을 비용기준 오름차순으로 정렬하고,
    //가장 비용이 적은 노드를 꺼내며 최단거리를 갱신한다.
    static int n, k;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");
        //n : 수빈이가 있는 위치
        n = Integer.parseInt(str[0]);
        //k : 동생이 있는 위치
        k = Integer.parseInt(str[1]);
        distance = new int[100001];
        System.out.println(dijkstra(n, k));

    }

    static int dijkstra(int start, int end){
        PriorityQueue<Node> queue= new PriorityQueue<>();
        Arrays.fill(distance, 10000);

        Node sNode = new Node();
        //시작은 첫노드의 start와 0초
        sNode.index = start;
        sNode.sec = 0;
        //첫 노드 큐에 추가
        queue.offer(sNode);
        distance[start] = 0; //거리0

        while(!queue.isEmpty()){
            //큐가 빌 때 까지
            Node curNode = queue.poll();
            if(distance[curNode.index]<curNode.sec)
                continue; //현재 노드에 저장되어있는 시간(거리)가 더 작으면 패스

            //deltas : 3가지 방향
            int[] deltas = new int[]{curNode.index-1, curNode.index+1, curNode.index*2};

            for(int i=0;i<deltas.length;i++){
                if(deltas[i]<0 || deltas[i]>10000) continue; //범위 벗어나면 패스
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

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
    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");
        //n : 수빈이가 있는 위치
        n = Integer.parseInt(str[0]);
        //k : 동생이 있는 위치
        k = Integer.parseInt(str[1]);

        distance = new int[100001];
        Arrays.fill(distance, Integer.MAX_VALUE);
        visited = new int[100001];


    }

//    public static void dijkstra(int start){
//        PriorityQueue<Node> pq = new PriorityQueue<>();
//        //시작 노드로 가기 위한 최단 경로를 0으로 설정하여, 큐에 삽입
//        pq.offer(new Node(start, 0));
//        d[n] = 0;
//        while(!pq.isEmpty()){
//
//        }
//    }
}

class Node {
    private int index;
    private int distance;

    public Node(int index, int distance){
        this.index = index;
        this.distance = distance;
    }
}

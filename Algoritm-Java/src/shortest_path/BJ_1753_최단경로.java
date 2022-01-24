package shortest_path;

import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_1753_최단경로 {

    static int v, e; //v:정점의 개수, e:간선의 개수
    static int k; //k:시작 정점의 번호
    static final int INF = Integer.MAX_VALUE;
    static ArrayList<Node>[] list;
    static int[] distance;
    static int start;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(br.readLine());

        list = new ArrayList[v+1]; //정점 갯수만큼 초기화
        distance = new int[v+1]; //정점 갯수만큼 초기화
        for(int i=1;i<=v;i++){
            list[i] = new ArrayList<>();
        }
        Arrays.fill(distance, INF); //최댓값으로 초기화

        distance[k] = 0;
        //리스트에 그래프 정보를 초기화
        for(int i=0;i<e;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            //start에서 end로 가는 거리
            list[start].add(new Node(end, distance));
        }
       dijkstra(k);
        for(int i=1;i<=v;i++){
            //dist가 초기값 그대로 INF라면 "INF"출력
            if(distance[i]==INF) {
                System.out.println("INF");
            }
            else System.out.println(distance[i]);
        }



    }

    private static void dijkstra(int index){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        boolean [] check = new boolean[v+1];
        queue.add(new Node(index, 0));
        distance[index]=0;

        while(!queue.isEmpty()){
            //큐가 빌 때까지
            //노드 poll
            Node curNode = queue.poll();
            int cur = curNode.index;

            if(check[cur]==true){
                //방문한 적 있으면
                continue;
            }
            check[cur] = true;

            for(Node node:list[cur]){
                if(distance[node.index]>distance[cur]+node.distance){
                    //현재 노드가 저장되어있는 값보다 작으면
                    distance[node.index] = distance[cur]+node.distance;
                    queue.add(new Node(node.index, distance[node.index]));
                }
            }
        }
    }

static class Node implements Comparable<Node>{
     int index;
     int distance;

    public Node(int index, int distance){
        this.index = index;
        this.distance = distance;
    }
    @Override
    public int compareTo(Node o) {
        return distance-o.distance;
    }
}

}



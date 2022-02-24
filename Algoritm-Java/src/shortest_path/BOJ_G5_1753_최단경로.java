package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G5_1753_최단경로 {
    //백준 골드5
    //다시풀기

    static int V, E; //v:정점개수, e:간선개수
    static ArrayList<Node>[] adjList;
    static final int INF = Integer.MAX_VALUE;
    static int[] distance;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[V+1];  //인접리스트 배열 선언
        for(int i=1;i<=V;i++){
            adjList[i] = new ArrayList<>();
        }
        distance = new int[V+1];
        visited = new boolean[V+1];

        int start = Integer.parseInt(br.readLine());

        for(int e = 0;e<E;e++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[from].add(new Node(to, weight));
        }

        Arrays.fill(distance, INF);
        distance[start] = 0;

        dijkstra();

        for(int i=1;i<=V;i++){
            if(distance[i]==INF)
                sb.append("INF").append("\n");
            else{
                sb.append(distance[i]).append("\n");
            }
        }
        System.out.println(sb);
    }

    static void dijkstra(){
        int min = 0;
        int current = 1;
        for(int i=1;i<=V;i++){
            min = INF; //min 최솟값 초기설정
            for(int j=1;j<=V;j++){  //모든 정점 검사
                if(!visited[j] && distance[j]<min){
                    min = distance[j];  //최소거리 갱신
                    current = j;  //최소거리 갖고있는 인덱스가 current
                }
            }
            visited[current] =true;  //방문처리
            for(int j=0;j<adjList[current].size();j++){
                //인접리스트 탐색
                if(!visited[adjList[current].get(j).to] && distance[adjList[current].get(j).to] > min+adjList[current].get(j).weight){
                    //현재 다음 간선이 방문한 적 없고, 갖고있는 가중치가 현재 최솟값+현재가중치 비교해서 작은 값 갱신
                    distance[adjList[current].get(j).to] = min+adjList[current].get(j).weight;
                }
            }
        }
    }


    static class Node{
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}

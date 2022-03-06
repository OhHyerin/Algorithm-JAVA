package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G3_1238_파티 {
    //백준 골드3
    //스터디 - 최단거리(다익스트라)
    //다익스트라 : 한 정점에서 다른 모든 정점의 최단거리를 구하는 알고리즘
    //플로이드와샬 : 모든 정점에서 다른 모든 정점의 최단거리를 구하는 알고리즘

    //갈때 최단거리, 올때 최단거리 구해서 더하기기

    static int V, E, X;
    static int[] distance;
    static boolean[] visited;
    static ArrayList<Town>[] golist; //갈때
    static ArrayList<Town>[] cblist; //올때
    static final int INF = Integer.MAX_VALUE;
    static int[] result; //갈때+올때 최단거리 배열
    static int max = Integer.MIN_VALUE; //결과값


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken()); //정점개수
        E = Integer.parseInt(st.nextToken()); //간선개수
        X = Integer.parseInt(st.nextToken()); //파티하는 타운 번호

        golist = new ArrayList[V + 1];  //from->to로 가는 리스트배열
        cblist = new ArrayList[V + 1];  //to->from으로 가는 리스트배열
        distance = new int[V + 1];  //최단거리 저장하는 배열
        result = new int[V + 1];  //갈때 최단거리 + 올때 최단거리

        //각 리스트배열 선언
        for (int i = 1; i <= V; i++) {
            golist[i] = new ArrayList<>();
            cblist[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) { //간선 갯수만큼 입력받음
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            golist[from].add(new Town(to, distance));  //from->to로
            cblist[to].add(new Town(from, distance));  //to->from으로
        }

        int[] goDistance = dijkstra(X, golist); //갈 때 최단거리 배열
        int[] cbDistance = dijkstra(X, cblist); //올 때 최단거리 배열

        for (int i = 1; i <= V; i++) {
            result[i] = goDistance[i] + cbDistance[i];
            max = Math.max(max, result[i]);
        }

        System.out.println(max);
    }

    //원래 dijkstra는 void dijkstra(int index)로 사용했지만
    //ArrayList배열에 따른 새로운 int배열이 반환되어야하기 때문에
    //반환형은 int[]형(최단거리 distance), 매개변수는 ArrayList<>[]
    private static int[] dijkstra(int index, ArrayList<Town>[] list) {
        PriorityQueue<Town> pq = new PriorityQueue<>(); //거리 최단으로 정렬
        distance = new int[V + 1];  //최단거리 갱신할 배열
        visited = new boolean[V + 1]; //방문 여부 배열

        //최단거리 저장할 distance배열은 최댓값으로 초기화
        Arrays.fill(distance, INF);

        //시작정점 index 초기화
        pq.add(new Town(index, 0));
        distance[index] = 0;

        while (!pq.isEmpty()) {
            Town curTown = pq.poll();
            int cur = curTown.index;

            if (visited[cur] == true) continue;  //방문한 적 있으면 continue;

            //방문한 적 없으면
            visited[cur] = true;

            for (Town town : list[cur]) {
                if (distance[town.index] > distance[cur] + town.distance) {
                    //다음으로 갈 town
                    //distance배열에 저장되어있는 값보다 현재 town에서 다음 town으로 가는 거리가 더 짧다면
                    //distance배열 갱신
                    distance[town.index] = distance[cur] + town.distance;
                    pq.add(new Town(town.index, distance[town.index]));
                }
            }
        }
        return distance;
    }

    static class Town implements Comparable<Town> {
        int index;
        int distance;

        public Town(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Town o) {
            return distance - o.distance;
        }
    }
}

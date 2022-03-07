package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_S2_18352_특정거리의도시찾기 {
    //백준 실버2
    //다익스트라

    //특정 도시 X로부터 모든 도시로 가는 거리

    static int V, E;
    static int K, X;
    static List<Integer>[] nodes;
    static int[] distance;
    static boolean[] visited;
    static int INF = 300001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        V = Integer.parseInt(st.nextToken()); //도시개수(정점개수)
        E = Integer.parseInt(st.nextToken()); //도로개수(간선개수)
        K = Integer.parseInt(st.nextToken()); //거리정보(최단거리가 K인 도시를 찾음)
        X = Integer.parseInt(st.nextToken()); //출발 도시의 번호

        nodes = new ArrayList[V+1];

        distance = new int[V+1];
        visited = new boolean[V+1];

        for(int i=1;i<=V;i++){
            nodes[i] = new ArrayList<>();
        }

        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            nodes[from].add(to);
        }

        dijkstra();

        boolean flag = false;
        for(int i=1;i<=V;i++){
            if(distance[i]==K){
                flag = true;
                sb.append(i).append("\n");
            }
        }
        if(!flag) sb.append(-1).append("\n");

        System.out.println(sb);
    }

    private static void dijkstra(){
        Queue<Pos> queue = new LinkedList<>();
        queue.offer(new Pos(X, 0));

        Arrays.fill(distance, INF);

        while(!queue.isEmpty()){
            Pos cur = queue.poll();

            if(distance[cur.pos]>cur.time){
                distance[cur.pos] = cur.time;

                for(int e:nodes[cur.pos]){
                    queue.offer(new Pos(e, cur.time+1));
                }
            }
        }


    }

    private static class Pos{
        int pos;
        int time;

        public Pos(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }
    }

}

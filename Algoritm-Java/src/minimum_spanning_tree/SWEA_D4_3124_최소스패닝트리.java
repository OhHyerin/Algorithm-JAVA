package minimum_spanning_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_D4_3124_최소스패닝트리 {
    //MST

    static int V, E;
    static List<Node> edges;
    static int[] root;
    static long result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){
            sb.append("#").append(t).append(" ");

            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            edges = new ArrayList<>();
            root = new int[V+1];
            result = 0;

            for(int i=1;i<=V;i++){
                root[i] = i;
            }

            for(int i=0;i<E;i++){
                st = new StringTokenizer(br.readLine());
                int nodeA = Integer.parseInt(st.nextToken());
                int nodeB = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                edges.add(new Node(nodeA, nodeB, cost));
            }

            //간선을 비용순으로 정렬
            Collections.sort(edges);

            for(int i=0;i<edges.size();i++){
                long cost = edges.get(i).cost;
                int nodeA = edges.get(i).nodeA;
                int nodeB = edges.get(i).nodeB;

                if(find(nodeA) != find(nodeB)){
                    union(nodeA, nodeB);
                    result += cost;
                }
            }
            sb.append(result).append("\n");
        }//t
        System.out.println(sb);
    }

    private static void union(int a, int b){
        a = find(a);
        b = find(b);

        if(a<b) root[b] = a;
        else root[a] = b;
    }

    private static int find(int x){
        if(root[x]==x) return x;
        return root[x] = find(root[x]);
    }

    private static class Node implements Comparable<Node>{
        int nodeA;
        int nodeB;
        long cost;

        public Node(int nodeA, int nodeB, long cost) {
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return cost-o.cost>0 ? 1 : -1;
        }
    }

}

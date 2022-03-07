package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G5_14284_�����̾��2 {
    //���� ���5
    //���ͽ�Ʈ�� �����ϴ� ���� �⺻���� ����

    //���� n��, 0���� �������� �̷���� ������ �׷���
    //m���� ����ġ ���� ����Ʈ�� �߰��س���
    //s�� t�� ����Ǵ� �������� ���� �߰��� ����
    //s��t�� ������ �Ǵ� ������ ������ ����ġ�� ���� �ּҰ� �ǵ���

    static int n, m;
    static int s, t;  //s�� t�� ������ �Ǵ� ����
    static ArrayList<Edge>[] edges;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        edges = new ArrayList[n+1];
        for (int i = 0; i < n+1; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[from].add(new Edge(to, weight));
            edges[to].add(new Edge(from, weight));
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        distance = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        dijkstra();

        System.out.println(distance[t]);


    }

    private static void dijkstra() {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        distance[1] = 0;

        for (Edge edge : edges[s]) {
            queue.add(new Edge(edge.vertex, edge.cost));
            distance[edge.vertex] = edge.cost;
        }

        while (!queue.isEmpty()) {
            Edge cur = queue.poll();
            if (cur.vertex == t) {
                return;
            }

            if (cur.cost > distance[cur.vertex]) {
                continue;
            }

            for (Edge edge : edges[cur.vertex]) {
                if (distance[edge.vertex] > edge.cost + cur.cost) {
                    queue.add(new Edge(edge.vertex, edge.cost + cur.cost));
                    distance[edge.vertex] = edge.cost + cur.cost;
                }
            }
        }

    }


    private static class Edge implements Comparable<Edge> {

        int vertex;
        int cost;

        public Edge(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}

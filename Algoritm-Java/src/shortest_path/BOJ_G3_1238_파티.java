package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G3_1238_��Ƽ {
    //���� ���3
    //���͵� - �ִܰŸ�(���ͽ�Ʈ��)
    //���ͽ�Ʈ�� : �� �������� �ٸ� ��� ������ �ִܰŸ��� ���ϴ� �˰���
    //�÷��̵�ͼ� : ��� �������� �ٸ� ��� ������ �ִܰŸ��� ���ϴ� �˰���

    //���� �ִܰŸ�, �ö� �ִܰŸ� ���ؼ� ���ϱ��

    static int V, E, X;
    static int[] distance;
    static boolean[] visited;
    static ArrayList<Town>[] golist; //����
    static ArrayList<Town>[] cblist; //�ö�
    static final int INF = Integer.MAX_VALUE;
    static int[] result; //����+�ö� �ִܰŸ� �迭
    static int max = Integer.MIN_VALUE; //�����


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken()); //��������
        E = Integer.parseInt(st.nextToken()); //��������
        X = Integer.parseInt(st.nextToken()); //��Ƽ�ϴ� Ÿ�� ��ȣ

        golist = new ArrayList[V + 1];  //from->to�� ���� ����Ʈ�迭
        cblist = new ArrayList[V + 1];  //to->from���� ���� ����Ʈ�迭
        distance = new int[V + 1];  //�ִܰŸ� �����ϴ� �迭
        result = new int[V + 1];  //���� �ִܰŸ� + �ö� �ִܰŸ�

        //�� ����Ʈ�迭 ����
        for (int i = 1; i <= V; i++) {
            golist[i] = new ArrayList<>();
            cblist[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) { //���� ������ŭ �Է¹���
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            golist[from].add(new Town(to, distance));  //from->to��
            cblist[to].add(new Town(from, distance));  //to->from����
        }

        int[] goDistance = dijkstra(X, golist); //�� �� �ִܰŸ� �迭
        int[] cbDistance = dijkstra(X, cblist); //�� �� �ִܰŸ� �迭

        for (int i = 1; i <= V; i++) {
            result[i] = goDistance[i] + cbDistance[i];
            max = Math.max(max, result[i]);
        }

        System.out.println(max);
    }

    //���� dijkstra�� void dijkstra(int index)�� ���������
    //ArrayList�迭�� ���� ���ο� int�迭�� ��ȯ�Ǿ���ϱ� ������
    //��ȯ���� int[]��(�ִܰŸ� distance), �Ű������� ArrayList<>[]
    private static int[] dijkstra(int index, ArrayList<Town>[] list) {
        PriorityQueue<Town> pq = new PriorityQueue<>(); //�Ÿ� �ִ����� ����
        distance = new int[V + 1];  //�ִܰŸ� ������ �迭
        visited = new boolean[V + 1]; //�湮 ���� �迭

        //�ִܰŸ� ������ distance�迭�� �ִ����� �ʱ�ȭ
        Arrays.fill(distance, INF);

        //�������� index �ʱ�ȭ
        pq.add(new Town(index, 0));
        distance[index] = 0;

        while (!pq.isEmpty()) {
            Town curTown = pq.poll();
            int cur = curTown.index;

            if (visited[cur] == true) continue;  //�湮�� �� ������ continue;

            //�湮�� �� ������
            visited[cur] = true;

            for (Town town : list[cur]) {
                if (distance[town.index] > distance[cur] + town.distance) {
                    //�������� �� town
                    //distance�迭�� ����Ǿ��ִ� ������ ���� town���� ���� town���� ���� �Ÿ��� �� ª�ٸ�
                    //distance�迭 ����
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

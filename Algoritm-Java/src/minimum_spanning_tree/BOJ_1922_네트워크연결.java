package minimum_spanning_tree;

import java.io.IOException;
import java.util.*;

public class BOJ_1922_��Ʈ��ũ���� {
    //���� (���4)
    //MST
    //1. �׷����� �������� ����ġ�� ������������ �����Ѵ�.
    //2. ������ �ϳ��� Ȯ���ϸ� ������ ������ ����Ŭ�� �߻���Ű���� Ȯ���Ѵ�.
    //   ��, ���� ���� ����ġ�� ���� �����Ѵ�.
    //   ����Ŭ�� �����ϴ� ������ �����Ѵ�.
    //3. �ش� ������ ������ MST���տ� �߰��Ѵ�.

    static int n, m; //n:������, m:������
    static ArrayList<Edge> edges; //��� ������ ���� ����Ʈ
    static int result = 0; //���� ����� ���� ����
    static int[] parent; //�θ� ���̺� �ʱ�ȭ

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//        n = Integer.parseInt(st.nextToken());
//        m = Integer.parseInt(st.nextToken());
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        parent = new int[100001];
        //�θ� ���̺�󿡼�, �θ� �ڱ� �ڽ����� �ʱ�ȭ
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        //���� ���� ����Ʈ
        edges  = new ArrayList<>();

        //��� ������ ���� ������ �Է� �ޱ�
        for (int i = 0; i < m; i++) {
//            st = new StringTokenizer(br.readLine(), " ");
//            int a = Integer.parseInt(st.nextToken());
//            int b = Integer.parseInt(st.nextToken());
//            int cost = Integer.parseInt(st.nextToken());
            int a = sc.nextInt();
            int b = sc.nextInt();
            int cost = sc.nextInt();
            edges.add(new Edge(cost, a, b));
        }

        //������ �������� ����
        Collections.sort(edges);

        //������ �ϳ��� Ȯ���ϸ�
        for (int i = 0; i < edges.size(); i++) {
            int cost = edges.get(i).getDistance();
            int a = edges.get(i).getNodeA();
            int b = edges.get(i).getNodeB();
            //����Ŭ�� �߻����� �ʴ� ��쿡�� ���տ� ����
            if (findParent(a) != findParent(b)) {
                unionParent(a, b);
                result += cost;
            }
        }
        System.out.println(result);
    }


    //Ư�� ���Ұ� ���� ������ ã��
    public static int findParent(int x){
        //��Ʈ��尡 �ƴ϶��, ��Ʈ ��带 ã�� ������ ��������� ȣ��
        if(x==parent[x]) return x;
        return parent[x] = findParent(parent[x]);

    }

    //�� ���Ұ� ���� ������ ��ġ��
    public static void unionParent(int a, int b){
        a = findParent(a);
        b = findParent(b);
        if(a<b) parent[b] = a;
        else parent[a] = b;
    }


    static class Edge implements Comparable<Edge>{
        private int distance;
        private int nodeA;
        private int nodeB;

        public Edge(int distance, int nodeA, int nodeB){
            this.distance = distance;
            this.nodeA = nodeA;
            this.nodeB = nodeB;
        }

        public int getDistance() {
            return this.distance;
        }

        public int getNodeA() {
            return this.nodeA;
        }

        public int getNodeB() {
            return this.nodeB;
        }

        @Override
        public int compareTo(Edge o) {
            //�Ÿ�(���)�� ª�� ���� ���� �켱������ �������� ����
            if(this.distance<o.distance){
                return -1;
            }
            return 1;
        }
    }

}

package minimum_spanning_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_G3_1774_���ֽŰ��Ǳ��� {
    //���� ���3
    //���͵� MST

    //����Ŭ�� ����� �ϴϱ� MST
    //ũ�罺Į
    /*
    1. ����, ��� ������ ����ġ�� ���� ������������ ����
    2. ����ġ�� ���� ���� �������� �����ϸ鼭 Ʈ���� ����Ŵ
        2.1) ����Ŭ�� �����Ѵٸ� �������� ����ġ�� ���� ������ ����
    3. n-1���� ������ ���õɶ����� 2�� �ݺ�
     */

    //�̹� ����Ǿ��ִ� ������ �����ϰ� -> ���ֽŵ��� ��ǥ��� ũ�罺Į �˰������� MST����

    //��ǥ�� ó���� Ŭ������ ���� �������ؼ� ó�� ������ �������

    static int N, M;  //N:��������, M:��������
    static int[] root;  //����� root��� �迭
    static ArrayList<Node> list;  //���� ����
    static Pos[] spaces;  //���ֽŵ��� ��ġ ��ǥ�� �����ϴ� Pos�迭

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // ���ֽ��� ��ġ ����
        M = Integer.parseInt(st.nextToken());  // ����Ǿ��ִ� ���ֽ��� ����
        root = new int[N+1];
        list = new ArrayList<>();
        spaces = new Pos[N+1];

        for(int i=1;i<=N;i++){
            root[i] = i;  //root��带 �����ϴ� �迭�� �ڱ��ڽ����� �ʱ�ȭ
        }

        //���� �Ǿ�� �ϴ� ���ֽŵ��� ��ǥ
        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            spaces[i] = new Pos(i, x, y);   //���ֽŵ��� ��ǥ�� spaces�迭�� ����
        }

        //�̹� ����� ���
        for(int i=1;i<=M;i++){
            st = new StringTokenizer(br.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());

            union(nodeA, nodeB);  //�̹� ����Ǿ��ִ� ���� ���� �ϳ��� ������(����Ŭ�ƴҶ�)
        }

        //��� ������ ��� �������� ���� (������ ������̱⶧���� j�� i+1���� ����)
        for(int i=1;i<=N;i++){
            for(int j=i+1;j<=N;j++){
                double distance = calDistance(spaces[i], spaces[j]); //��� ��尣 �Ÿ� ����
                list.add(new Node(spaces[i].index, spaces[j].index, distance));  //��� ��� list�� ����
            }
        }
        Collections.sort(list);  //distance���� ������� ��������

        double answer = 0.0;  //���ؾ� �� �� : �ּ��� ��� ���� (double��)

        //ũ�罺Į
        for(int i=0;i<list.size();i++){
            Node cur = list.get(i);

            // ���� ��ü�� Ž���� �� �ִ� ������
            // distance�� ���������� ���������߱⶧����
            // distance�� �����ͺ��� �̹� ����ǰ�
            // ���߿� distance�� ū ���� ��Ʈ��� �񱳷� �˾Ƽ� ������� ����
            if(find(cur.nodeA) != find(cur.nodeB)){  //���� ��尡 ����Ǿ��ִ� nodeA�� nodeB�� ��Ʈ��尡 �ٸ�����
                // ����Ŭ�� �����Ѵٸ� �������� ����ġ�� ���� ������ ����
                answer += cur.distance;
                union(cur.nodeA, cur.nodeB);
            }

        }

        // �Ҽ��� 2��°���� ���
        System.out.printf("%.2f", answer);


    }

    private static double calDistance(Pos A, Pos B){
        // ��ǥ���� �� �� ������ �Ÿ��� ���ϴ� �Լ�
        // ��� ����� double���̱� ������ distance���� double������ ��ȯ
        return Math.sqrt(Math.pow(A.x-B.x, 2)+Math.pow(A.y-B.y, 2));
    }

    private static void union(int a, int b){
        a = find(a);
        b = find(b);

        if(a<b) root[b] = a;
        else root[a] = b;
    }

    private static int find(int x){
        if(root[x]==x) return x;
        else return root[x] = find(root[x]);
    }

    private static class Pos{
        //���ֽŵ��� ��ȣ�� ��ǥ�� �����ϴ� Ŭ����
        int index;
        int x;
        int y;

        public Pos(int index, int x, int y) {
            this.index = index;
            this.x = x;
            this.y = y;
        }
    }

    private static class Node implements Comparable<Node>{
        // ���� MSTó�� Node�� ����
        int nodeA;
        int nodeB;
        double distance;

        public Node(int nodeA, int nodeB, double distance) {
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            // double���̶� ���� distance-o.distance�� ����ȵ�
            if(distance>o.distance) return 1;
            else return -1;
        }
    }
}

package minimum_spanning_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ_1197_�ּҽ��д�Ʈ�� {
    //���� ���4
    //MST - Kruskal
    //1. �׷����� �������� ����ġ�� ������������ �����Ѵ�.
    //2. ������ �ϳ��� Ȯ���ϸ� ������ ������ ����Ŭ�� �߻���Ű���� Ȯ���Ѵ�.
    //   ��, ���� ���� ����ġ�� ���� �����Ѵ�.
    //   ����Ŭ�� �����ϴ� ������ �����Ѵ�.
    //3. �ش� ������ ������ MST���տ� �߰��Ѵ�.


    static int v, e;  // v : ����, e : ����
    static ArrayList<Node> list; // �������� list
    static int [] root;  // ����Ŭ Ȯ�� ����

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        root = new int[10001]; // ���� ������ŭ
        for (int i=1;i<=v;i++){
            root[i] = i;
        }
        list = new ArrayList<>();

        for(int i=0;i<e;i++){
            st = new StringTokenizer(br.readLine());

            int dotA = Integer.parseInt(st.nextToken());
            int dotB = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            list.add(new Node(dotA, dotB, dist));

        }
        //----------------�Է� �Ϸ�--------------------
        int answer=0;
        //�������� ����ġ�� ������������ ����
        Collections.sort(list);

        for(int i=0;i<list.size();i++){
            //����Ʈ�� ��ȸ�ϸ�
            int dist = list.get(i).distance;
            int dotA = list.get(i).nodeA;
            int dotB = list.get(i).nodeB;
            //����Ŭ�� �߻��ϴ� ��� ����
            if(findCycle(dotA) != findCycle(dotB)){
                //��A�� ��B�� �θ� �ٸ��ٸ�
                union(dotA, dotB);
                answer += dist;
            }
        }
        System.out.println(answer);
    }

    public static int findCycle(int x){
        //��Ʈ��尡 �ƴ϶��, ��Ʈ��带 ã�� ������ ��������� ȣ��
        if(x==root[x]) return x;

        return root[x] = findCycle(root[x]);
    }

    public static void union(int a, int b){
        //�Ű����� �� a,b�� ��Ʈ��带 ã�Ƽ�
        a = findCycle(a);
        b = findCycle(b);
        //�� ���� ������ root���� �� ���� ��Ʈ��� �����ϰ� ����(��ħ)
        if(a<b) root[b] = a;
        else root[a] = b;
    }


    static class Node implements Comparable<Node>{

        private int distance;
        private int nodeA;
        private int nodeB;

        public Node(int nodeA, int nodeB, int distance) {
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.distance = distance;
        }
        public int getNodeA() {
            return nodeA;
        }
        public int getNodeB() {
            return nodeB;
        }
        public int getDistance() {
            return distance;
        }
        @Override
        public int compareTo(Node o) {
            return distance-o.distance;
        }
    }

}

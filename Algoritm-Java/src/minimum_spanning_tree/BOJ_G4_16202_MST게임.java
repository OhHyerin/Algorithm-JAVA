package minimum_spanning_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_G4_16202_MST���� {
    //���� ���4
    //���͵� MST
    
    //���� �ϳ� ���� ������ ��尡 ���������� Ȯ���ϰ�
    //��尡 �������� root��带 �ʱ�ȭ�ؾ���
    
    //������ ���� �� ��尡 �������ٸ� 
    //�������� ��带 root���� �����ִ� ��常 root��带 �ʱ�ȭ�Ϸ��ߴ��� �������
    //ũ�罺Į �����ϱ����� ��ü root��带 �ʱ�ȭ���ָ� ��������
    
    //���� MST�������� ������ MST�� �������ؼ� ���ϰ� ũ�罺Į�� ����ϸ� ������
    //�� ������ MST�� ��������� ���� ���� �־ MST���� �Ǵ��ؾ��ϴ� ������ �������

    static int N;  //N : ������ ����
    static int M;  //M : ������ ����
    static int K;  //K : ���� ��
    static ArrayList<Node> nodes;  //���� �����ϴ� ����Ʈ
    static int[] root; //�� ����� ��Ʈ��� ����
    static int nCnt;  //���� ���� ������ ������ ������ üũ�ϴ� ����

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        nodes = new ArrayList<>(); //��带 ���� ����Ʈ ����
        root = new int[N+1];  //����� ��Ʈ��带 ���� �迭

        for(int i=1;i<=N;i++){
            root[i] = i;  //�� ����� ��Ʈ��带 �ڱ� �ڽ����� �ʱ�ȭ
        }

        for(int i=1;i<=M;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            nodes.add(new Node(x, y, i));
        }
        //---------------�Է¿Ϸ�----------------------

        //---------------ù ���� ���� �������ʰ� ���------
        Collections.sort(nodes);  //distance�������� �������� ����
        nCnt = N;
        sb.append(kruskal(nodes)).append(" ");
        //---------------ù ��-------------------------

        //---------------�� ��° �Ϻ���------------------
        for(int k=1;k<K;k++){
//            System.out.println("turn : "+k);
//            Collections.sort(nodes);
            boolean isNodeRemove = true; //������ ���� �� ������ �����Ǵ��� Ȯ���ϴ� ����
            int nA = nodes.get(0).nodeA;  // ������ ������ ����Ǿ��ִ� ���A
            int nB = nodes.get(0).nodeB;  // ������ ������ ����Ǿ��ִ� ���B

            //������ ���̸�ŭ üũ
            for(int i=1;i<nodes.size();i++) {
                if (nA == nodes.get(i).nodeA || nA == nodes.get(i).nodeB||nB== nodes.get(i).nodeA || nB== nodes.get(i).nodeB) {
                    //���� ������ ������ ����Ǿ��ִ� ��尡 �ٸ� ����Ʈ���� �����ϸ�
                        isNodeRemove = false;  //�����ȵ�
                        break;
                }
            }

            if(isNodeRemove){
                //������ ������ ����Ǿ��ִ� ��尡 �ٸ� ����Ʈ�� ������
                //���� �ϳ��� �����Ǵ� ��
                nCnt--;
            }
            nodes.remove(0);  //����� ���� ���� ���� ����
//            System.out.println(nodes);

            sb.append(kruskal(nodes)).append(" ");
        }

        System.out.println(sb);

    }

    private static int kruskal(ArrayList<Node> list){
        for(int i=1;i<=N;i++){
            root[i] = i;  //�� ����� ��Ʈ��带 �ڱ� �ڽ����� �ʱ�ȭ
        }
        int answer = 0;  //MST ���
        int nodeCnt = 0;  //MST�� �����ϸ鼭 �������� ������ ����
        for(int i=0;i<list.size();i++){
            Node cur = list.get(i);

            if(find(cur.nodeA) != find(cur.nodeB)){
                answer += cur.distance;
                nodeCnt++;  //��峢�� �̾��� �� ���� nodeCnt�� �ϳ��� ����
                union(cur.nodeA, cur.nodeB);
            }
        }

//        System.out.println("nodeCnt : "+nodeCnt);
//        System.out.println("nCnt : "+nCnt);

        if(nodeCnt==nCnt-1) return answer;  //���� MST�� ���� ������ ���� ������ �������� �ϳ� �۴ٸ� MST��� return
        else return 0;  // MST�� ������� �ʾҴٸ� 0 return

//        System.out.println("answer : "+answer);
//        return answer;
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


    private static class Node implements Comparable<Node>{
        int nodeA;
        int nodeB;

        int distance;

        public Node(int nodeA, int nodeB, int distance) {
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return distance-o.distance;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "nodeA=" + nodeA +
                    ", nodeB=" + nodeB +
                    ", distance=" + distance +
                    '}';
        }

    }
}

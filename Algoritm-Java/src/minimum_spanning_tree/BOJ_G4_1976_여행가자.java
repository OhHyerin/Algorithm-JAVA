package minimum_spanning_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_1976_���డ�� {
    //���� ���4
    //���͵� MST

    //����� �ִܰ�γ� �ּҰ����� ����� ���� �ƴϱ⶧����(ũ�罺Į���� �� �ʿ� X)
    //�ٸ� ���� ���� union-find�� ���� ���� ��ο� �ִ����� Ȯ�� �� true, false ��ȯ
    
    //���� ũ�罺Į�� �ִܰŸ��� ���ϴ°��̹Ƿ� ��θ� �ٽ� ���ƿ´ٸ� ũ�罺Į �������� �ٽ� �����غ��ƾ� ��

    //��Ʈ��尡 ���� ��Ʈ����� ���� ��ο� �ִٰ� �Ǵ��� �� ����

    static int N, M;
    static int[] root; //��Ʈ��带 �����ϴ� �迭
    static int[] travel; //������

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());  //������ ����
        M = Integer.parseInt(br.readLine());  //���� ��ȹ���� ������ ����
        root = new int[N+1];
        travel = new int[M];

        for(int i=1;i<=N;i++) {
            root[i] = i; //�� ����� root�� �ڱ��ڽ����� �ʱ�ȭ
        }

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                int isConnection = Integer.parseInt(st.nextToken());

                if(isConnection==1){
                    //i��°�� j��°�� ����Ǿ������� ����
                    union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++){
            travel[i] = Integer.parseInt(st.nextToken());
        }

        int startRoot = find(travel[0]); //���� ����� ù ��° ������ ��Ʈ��带 ã��
        boolean check = true;
        for(int i=1;i<M;i++){
            if(startRoot != find(travel[i])){
                check = false;
                break;
            }
        }

        if(check){
            sb.append("YES");
        }else{
            sb.append("NO");
        }

        System.out.println(sb);

    }

    private static void union(int a, int b){

        //��� a�� b�� ��Ʈ��带 ���� ã��
        a = find(a);
        b = find(b);

        //���� a�� b�� �� ����� ��Ʈ�����
        //�� ��带 ��ġ������ �� ��Ʈ��� �� ���� ���� ���� (����ǵ���)
        if(a<b) root[b] = a;
        else root[a] = b;

    }

    private static int find(int x){
        if(x==root[x]) return x;  //�ڱ� �ڽ��� ��Ʈ����� �ڽ� ��ȯ
        return root[x] = find(root[x]);  //root��� ã�� �� ����
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

        @Override
        public int compareTo(Node o) {
            return distance-o.distance;
        }
    }
}

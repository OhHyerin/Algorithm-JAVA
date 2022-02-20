package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_G2_11780_�÷��̵�2 {
    //���� ���2
    //�ִܰ�� + �ִܰ�κ��
    //�÷��̵� ����

    static int n, m;
    static int [][] dist;
    //dist[i][j] = i���� j���� ���� �ּҺ��
    static int [][] next;
    //next[i][j] = �ִܰ�θ� �����ϸ鼭 j���� ���� ���ø� �����س��� �迭

    static final int INF = 1000000;
    static final int NIL = -1;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        dist  = new int [n+1][n+1];
        next = new int [n+1][n+1];

        //�迭 �ʱ�ȭ
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                next[i][j] = NIL;
                if(i==j){
                    continue;
                }
                dist[i][j] = INF;
            }
        }

        //�׷��� ����
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            dist[start][end] = Math.min(cost, dist[start][end]); //���� ���� cost�� ���� ����Ǿ��ִ� start-end ����ġ �� �ּڰ�
            next[start][end] = start;
        }

        floydWarshall();
        print();

    }

    private static void floydWarshall(){
        for(int k=1;k<=n;k++){ //k : ������
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    //�ּҺ���� ���ŵǸ� next�迭�� ����
                    if(dist[i][j]>dist[i][k]+dist[k][j]){
                        dist[i][j] = dist[i][k]+dist[k][j];
                        next[i][j] = next[k][j];
                    }
                }
            }
        }
    }

    private static void print(){
        StringBuilder sb = new StringBuilder();
        //�ִܰŸ� ���
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(dist[i][j]>=INF){
                    sb.append("0").append("\n");
                }
                else{
                    sb.append(dist[i][j]+" ");
                }
                sb.append("\n");
            }
        }

        //�ִ� �Ÿ� ���
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(next[i][j]==NIL){
                    sb.append("0").append("\n");
                }
                else{
                    Stack<Integer> st = new Stack<>();
                    int pre = j;
                    st.add(j);
                    while(i!=next[i][pre]){
                        pre = next[i][pre];
                        st.push(pre);
                    }
                    //�ִ� �Ÿ� ��� ũ�� (��� �������� ����)
                    sb.append(st.size()+1+" ");
                    sb.append(i+" ");
                    while(!st.empty()){
                        sb.append(st.pop()+" ");
                    }
                    sb.append("\n");
                }
            }
        }
        System.out.println(sb);
    }

}

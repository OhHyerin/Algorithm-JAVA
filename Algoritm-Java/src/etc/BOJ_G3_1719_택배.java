package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G3_1719_�ù� {
    //�ִܰŸ�, �÷��̵����

    static int N;
    static int M;
    static int[][] adjMatrix;
    static int[][] first;
    static final int INF = 10000*1000;  //�÷��̵� ������ �׻� INF�� ����
    /*
    Integer.MAXVALUE�� �ϸ� ���ϴ� �� �ȳ���
    �������� ���� �� �ִ� �ִ� ���� ��������� ��.
    �ش� ���������� ����� ����(10000) * ����� �ҿ�ð� (1000)
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjMatrix = new int[N+1][N+1];
        first = new int[N+1][N+1];

        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                adjMatrix[i][j] = INF;  //������� �ִ����� �ʱ�ȭ
//                if(i==j){
//                    //�ڱ� �ڽ����� ���±��̶�� 0
//                    adjMatrix[i][j] = 0;
//                }
            }
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            adjMatrix[from][to] = time;
            adjMatrix[to][from] = time;  //�����
            first[from][to] = to;
            first[to][from] = from;

        }

        floydWarshall();

        StringBuilder sb = new StringBuilder();

        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(i==j){
                    if(i==N && j==N){
                        sb.append("-");
                    }else{
                        sb.append("- ");
                    }
                }else{
                    sb.append(first[i][j]+" ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());

    }

    private static void floydWarshall(){
        for(int k=1;k<=N;k++){  //������
            for(int i=1;i<=N;i++){
                if(k==i) continue;  //�������� ������� ������
                for(int j=1;j<=N;j++){
                    //�ּҺ�� ���ŵǸ� adjMatrix, first �迭 ����
                    if(adjMatrix[i][j] > adjMatrix[i][k] + adjMatrix[k][j]){
                        adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
                        first[i][j] = first[i][k];  //���� �÷��̵���� �ڵ忡 �ش� �ٸ� �߰�
                    }
                }
            }

        }
    }
}

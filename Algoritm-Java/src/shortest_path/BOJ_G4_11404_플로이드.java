package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_11404_�÷��̵� {
    //���� ���4
    //�ִܰŸ� - �÷��̵����

    //��� ������ �� (A, B)���� ���� A->B�� ���µ� ��� �Ÿ� ����ϹǷ� ���ͽ�Ʈ��X, �÷��̵�� Ǯ��� ��

    static int V, E;
    static int[][] adjMatrix;
    static final int INF = 100000000;  //Integer.MAX_VALUE���ϸ� ���� �Ѿ�� �ٸ� �� ��µ� //2147483647
    //���� INF�� ���� �������� ��°���????????

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        adjMatrix = new int[V+1][V+1];

        for(int i=1;i<=V;i++){
            for(int j=1;j<=V;j++){
                adjMatrix[i][j] = INF;  //������� �ִ����� �ʱ�ȭ

                if(i==j){
                    adjMatrix[i][j] = 0;  //�ڱ��ڽ����� ���±��� 0
                }
            }
        }

//        for(int i=1;i<=V;i++){
//            for(int j=1;j<=V;j++){
//                System.out.print(adjMatrix[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println("--------------------------");

        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            adjMatrix[from][to] = Math.min(adjMatrix[from][to], distance);  //�������� ���۵��ÿ� �������ø� �����ϴ� �뼱�� �ϳ��� �ƴҼ��� �ִٰ� ��õ�
        }


//        for(int i=1;i<=V;i++){
//            for(int j=1;j<=V;j++){
//                System.out.print(adjMatrix[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println("--------------------------");

        floydWarshall();

        for(int i=1;i<=V;i++){
            for(int j=1;j<=V;j++){
                if(adjMatrix[i][j]==INF){
                    adjMatrix[i][j]=0;  //�ʱⰪ���� �״�� �ִ� ������ ���� �����̹Ƿ� 0���� ����
                }

                sb.append(adjMatrix[i][j]+" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    private static void floydWarshall(){
        for(int k=1;k<=V;k++){ //k : ������
            for(int i=1;i<=V;i++){
                for(int j=1;j<=V;j++){
                    //�ּҺ���� ���ŵǸ� adjMatrix�迭�� ����
                    if(adjMatrix[i][j]>adjMatrix[i][k]+adjMatrix[k][j]){
                        adjMatrix[i][j] = adjMatrix[i][k]+adjMatrix[k][j];
                    }
                }
            }
        }
    }
}

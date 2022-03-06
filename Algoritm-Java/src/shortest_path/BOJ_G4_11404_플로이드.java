package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_11404_플로이드 {
    //백준 골드4
    //최단거리 - 플로이드워셜

    //모든 도시의 쌍 (A, B)에서 도시 A->B로 가는데 모든 거리 계산하므로 다익스트라X, 플로이드로 풀어야 함

    static int V, E;
    static int[][] adjMatrix;
    static final int INF = 100000000;  //Integer.MAX_VALUE로하면 범위 넘어가서 다른 값 출력됨 //2147483647
    //보통 INF는 무슨 기준으로 잡는건지????????

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        adjMatrix = new int[V+1][V+1];

        for(int i=1;i<=V;i++){
            for(int j=1;j<=V;j++){
                adjMatrix[i][j] = INF;  //인접행렬 최댓값으로 초기화

                if(i==j){
                    adjMatrix[i][j] = 0;  //자기자신으로 오는길은 0
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

            adjMatrix[from][to] = Math.min(adjMatrix[from][to], distance);  //문제에서 시작도시와 도착도시를 연결하는 노선은 하나가 아닐수도 있다고 명시됨
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
                    adjMatrix[i][j]=0;  //초기값으로 그대로 있는 간선은 없는 간선이므로 0으로 설정
                }

                sb.append(adjMatrix[i][j]+" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    private static void floydWarshall(){
        for(int k=1;k<=V;k++){ //k : 경유지
            for(int i=1;i<=V;i++){
                for(int j=1;j<=V;j++){
                    //최소비용이 갱신되면 adjMatrix배열도 갱신
                    if(adjMatrix[i][j]>adjMatrix[i][k]+adjMatrix[k][j]){
                        adjMatrix[i][j] = adjMatrix[i][k]+adjMatrix[k][j];
                    }
                }
            }
        }
    }
}

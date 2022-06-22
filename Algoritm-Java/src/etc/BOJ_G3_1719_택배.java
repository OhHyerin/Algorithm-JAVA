package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G3_1719_택배 {
    //최단거리, 플로이드워셜

    static int N;
    static int M;
    static int[][] adjMatrix;
    static int[][] first;
    static final int INF = 10000*1000;  //플로이드 워셜은 항상 INF값 주의
    /*
    Integer.MAXVALUE로 하면 원하는 답 안나옴
    문제에서 나올 수 있는 최대 수로 지정해줘야 함.
    해당 문제에서는 경로의 개수(10000) * 경로의 소요시간 (1000)
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
                adjMatrix[i][j] = INF;  //인접행렬 최댓값으로 초기화
//                if(i==j){
//                    //자기 자신으로 오는길이라면 0
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
            adjMatrix[to][from] = time;  //양방향
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
        for(int k=1;k<=N;k++){  //경유지
            for(int i=1;i<=N;i++){
                if(k==i) continue;  //경유지와 출발지가 같으면
                for(int j=1;j<=N;j++){
                    //최소비용 갱신되면 adjMatrix, first 배열 갱신
                    if(adjMatrix[i][j] > adjMatrix[i][k] + adjMatrix[k][j]){
                        adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
                        first[i][j] = first[i][k];  //기존 플로이드워셜 코드에 해당 줄만 추가
                    }
                }
            }

        }
    }
}

package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_G2_11780_플로이드2 {
    //백준 골드2
    //최단경로 + 최단경로비용
    //플로이드 워셜

    static int n, m;
    static int [][] dist;
    //dist[i][j] = i에서 j까지 가는 최소비용
    static int [][] next;
    //next[i][j] = 최단경로를 갱신하면서 j도착 직전 도시를 저장해놓은 배열

    static final int INF = 1000000;
    static final int NIL = -1;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        dist  = new int [n+1][n+1];
        next = new int [n+1][n+1];

        //배열 초기화
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                next[i][j] = NIL;
                if(i==j){
                    continue;
                }
                dist[i][j] = INF;
            }
        }

        //그래프 구성
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            dist[start][end] = Math.min(cost, dist[start][end]); //새로 들어온 cost와 기존 저장되어있던 start-end 가중치 중 최솟값
            next[start][end] = start;
        }

        floydWarshall();
        print();

    }

    private static void floydWarshall(){
        for(int k=1;k<=n;k++){ //k : 경유지
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    //최소비용이 갱신되면 next배열도 갱신
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
        //최단거리 비용
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

        //최단 거리 경로
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
                    //최단 거리 경로 크기 (출발 정점까지 포함)
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

package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_D4_5643_professionalÅ°¼ø¼­ {

    static int N;
    static int M;
    static int [][] adj;
    static int countB, countS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            sb.append("#").append(t).append(" ");

            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            adj = new int[N+1][N+1];

            for(int i=1;i<=M;i++){
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                adj[a][b] = 1;
            }

            int result = 0;
            for(int i=1;i<=N;i++){
                countB = 0;
                countS = 0;
                bfs(i, 1);
                bfs(i, 2);
                if(countB + countS==N-1) result++;
            }
            sb.append(result).append("\n");
        } //t
        System.out.println(sb);

    }

    private static void bfs(int idx, int type){
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N+1];

        queue.add(idx);
        visited[idx] = true;

        while(!queue.isEmpty()){
            int cur = queue.poll();
            for(int i=1;i<=N;i++){
                if(adj[cur][i] == 1 && !visited[i] && type==1){
                    queue.add(i);
                    visited[i] = true;
                    countB++;
                }
                if(adj[i][cur] ==1 && !visited[i] && type==2){
                    queue.add(i);
                    visited[i] = true;
                    countS++;
                }
            }
        }
    }

}

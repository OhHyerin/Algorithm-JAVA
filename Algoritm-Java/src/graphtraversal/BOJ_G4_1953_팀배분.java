package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_1953_팀배분 {
    //그래프탐색

    static int N;  //학생들의 수
    static int[][] map;
    static int[] visited;
    static Queue<Integer> queue;
    static List<Integer>[] list;
    static List<Integer> blue;
    static List<Integer> white;
    static StringBuilder sb = new StringBuilder();
    static int start;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N+1][N+1];
        visited = new int[N+1];
        queue = new LinkedList<>();

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            for(int j=0;j<n;j++){
                int m = Integer.parseInt(st.nextToken());
                map[i][m] = 1;
                map[m][i] = 1;
            }
        }

        for(int i=1;i<=N;i++){
            if(visited[i] != 0) continue;  //0이 아니면 이미 방문한 것
            visited[i] = 1;  //방문하면 1
            queue.add(i);  //i 추가하고 팀 빌딩 시작
            bfs();
        }

        int cnt1 = 0;
        int cnt2 = 0;

        StringBuilder team1 = new StringBuilder();
        StringBuilder team2 = new StringBuilder();

        for(int i=1;i<=N;i++){
            if(visited[i]==1){
                //1번팀
                cnt1++;
                team1.append(i+" ");
            }else{
                cnt2++;
                team2.append(i+" ");
            }
        }

        System.out.println(cnt1);
        System.out.println(team1);
        System.out.println(cnt2);
        System.out.println(team2);


    }

    private static void bfs(){

        while(!queue.isEmpty()){
            int cur = queue.poll();

            for(int i=1;i<=N;i++){
                if(visited[i] != 0) continue; //방문한 적 있으면 continue

                if(map[cur][i]==1){
                    //서로 싫어하는 애들이면
                    visited[i] = visited[cur]*-1;  //다른팀에 배치
                    queue.add(i);  //다음 탐색
                }
            }
        }
    }


}

package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_2573_빙산 {
    //백준 골드4

    //빙하를 녹이는건 bfs
    //빙하 녹인 후 분단된 개수가 몇갠지 dfs로 탐색

    static int R, C;
    static int[][] map;
    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;

        while(true){
            //빙하가 다 녹았을 때 return
            if(countIceberg()==0){
                day = 0;
                break;
            }
            //빙하가 두 덩어리 이상으로 나눠졌을 때 return
            if(countIceberg()>=2){
                break;
            }
            bfs();
            day++;
        }

        System.out.println(day);
    }


    static void bfs() {
        Queue<Iceberg> queue = new LinkedList<>();

        //모든 빙산 queue에 추가 (map에 반영 된 것도 여기서 초기화됨)
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j]==-1){
                    map[i][j]=0;
                }
                if (map[i][j] != 0) {
                    queue.add(new Iceberg(i, j, map[i][j]));
                }
            }
        }

        while (!queue.isEmpty()) {

            Iceberg cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if(isIn(nr,nc)){
                    //현재위치 기준 사방이 범위 안에 있고
                    if(map[nr][nc]==0){
                        //물이면
                        map[cur.r][cur.c]--;  //물 있는 만큼 현재위치 높이 감소
                        if(map[cur.r][cur.c]<=0){
                            //0보다 작으면 빙하 없애기 (0이면 바로 다음탐색에서 물로 인식하기때문에 -1로 설정)
                            map[cur.r][cur.c]=-1;
                        }
                   }
                }
            }

        }
    }

    static void dfs(int r, int c){
        //dfs로 방문했던 곳 true처리가 목적 (갔던 곳 다시 안가려고)

        visited[r][c] = true; //해당지점 방문처리

        for(int d=0;d<4;d++){
            int nr = r+dr[d];
            int nc = c+dc[d];

            if(isIn(nr,nc)){
                if(!visited[nr][nc] && map[nr][nc]!=0 && map[nr][nc]!=-1){
                    //방문한 적 없고 빙하일 때 (물인 곳이랑 빙하가 물이 된 곳은 빼고)
                    dfs(nr, nc);
                }
            }
        }

    }

    static int countIceberg(){
        visited = new boolean[R][C];
        int cnt = 0;
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j]!=0 && map[i][j]!=-1 && !visited[i][j]){
                    dfs(i,j);
                    cnt++;
                }
            }
        }

        return cnt;
    }

    static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }


    static class Iceberg {
        int r;
        int c;
        int height;

        public Iceberg(int r, int c, int height) {
            this.r = r;
            this.c = c;
            this.height = height;
        }
    }
}

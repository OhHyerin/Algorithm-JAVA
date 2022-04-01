package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_1600_말이되고픈원숭이 {
    //백준 골드4
    //최단거리니까 bfs

    /*
    visited배열을 3중으로 써야하는 이유 (2중이면 틀렸습니다 나옴)
    1
    5 5
    0 0 0 0 0
    0 0 0 0 0
    0 0 0 1 1
    0 0 0 1 0
    최소의 동작으로 움직여도 목적지에 도달하지 못하는 경우가 있기 때문에
    */

    static int R, C;
    static int K;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dr = {-1, 1, 0, 0, 1, 2, -1, -2, 2, 1, -2, -1};
    static int[] dc = {0, 0, -1, 1, 2, 1, 2, 1, -1, -2, -1, -2};
    static int minDist = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        visited = new boolean[R][C][K+1];

        for(int i=0;i<R;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<C;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        System.out.println(minDist);
    }

    private static void bfs(){
        Queue<Pos> queue = new LinkedList<>();

        queue.add(new Pos(0, 0, 0, 0));
        visited[0][0][0] = true;

        while(!queue.isEmpty()){
            Pos cur = queue.poll();

            if(cur.r==(R-1) && cur.c==(C-1)){
                minDist = cur.dist;
                break;
            }

            for(int d=0;d<(cur.jumpCount<K ? 12:4);d++){
                //현재까지 점프한 개수가 K보다 크면 d==3일때 까지만 돌기
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];
                int nj = (d>=4? cur.jumpCount+1 : cur.jumpCount); //점프 횟수

                if(!isIn(nr,nc)) continue;  //범위내에 있고
                if(visited[nr][nc][nj]) continue; //방문한 적 없고
                if(map[nr][nc]==1) continue;  //벽이 아니면


                visited[nr][nc][nj] = true;
                queue.add(new Pos(nr, nc, nj, cur.dist+1));

            }

        }

    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }

    private static class Pos{
        int r;
        int c;
        int jumpCount;
        int dist;

        public Pos(int r, int c, int jumpCount, int dist) {
            this.r = r;
            this.c = c;
            this.jumpCount = jumpCount;
            this.dist = dist;
        }
    }
}

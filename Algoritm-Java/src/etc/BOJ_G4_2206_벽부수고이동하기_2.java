package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_2206_벽부수고이동하기_2 {
    //bfs, 3차원 배열

    static int R, C;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int minDist = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        visited = new boolean[R][C][2];

        for(int i=0;i<R;i++){
            String str = br.readLine();
            for(int j=0;j<C;j++){
                map[i][j] = str.charAt(j)-'0';
            }
        }

        bfs();

        System.out.println(minDist==Integer.MAX_VALUE? -1:minDist);


    }

    private static void bfs(){
        Queue<Pos> queue = new LinkedList<>();

        queue.add(new Pos(0, 0, 1, 0));
        visited[0][0][0] = true;

        while(!queue.isEmpty()){
            Pos cur = queue.poll();

            if(cur.r==(R-1) && cur.c==(C-1)){
                minDist = cur.dist;
                break;
            }

            for(int d=0;d<4;d++){
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];


                if(!isIn(nr, nc)) continue;
                if(map[nr][nc]==0){  //이동할 수 있는 곳이라면 그냥 이동
                    if(visited[nr][nc][cur.distroy]) continue; //방문한 적 있으면 패스
                    queue.add(new Pos(nr, nc, cur.dist+1, cur.distroy));
                    visited[nr][nc][cur.distroy] = true;
                }else{  //벽이라면,
                    if(cur.distroy>0) continue; //이미 한 번 벽을 부순 적 있으면 이제 못부심
                    if(visited[nr][nc][cur.distroy+1]) continue;
                    queue.add(new Pos(nr, nc, cur.dist+1, cur.distroy+1));
                    visited[nr][nc][cur.distroy+1] = true;
                }
            }
        }
    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }

    private static class Pos{
        int r;
        int c;
        int dist;
        int distroy;

        public Pos(int r, int c, int dist, int distroy) {
            this.r = r;
            this.c = c;
            this.dist = dist;
            this.distroy = distroy;
        }
    }
}

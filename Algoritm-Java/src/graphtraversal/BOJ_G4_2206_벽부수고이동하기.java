package graphtraversal;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_2206_벽부수고이동하기 {
    //백준 골드4
    //최단 경로를 구하는 문제이므로 bfs로 예측
    //모든 길이 1로 막혀있다면 벽 부수기

    static int R, C;
    static int[][] map;
//    static boolean[][] visited;
    static int[][] visited;
    static int minDist = Integer.MAX_VALUE;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
//        visited = new boolean[R][C];
        visited = new int[R][C];

        for(int i=0;i<R;i++){
            String str = br.readLine();
            for(int j=0;j<C;j++){
                map[i][j] = str.charAt(j)-'0';
                visited[i][j]=Integer.MAX_VALUE;
            }
        }

        bfs();

        if(minDist==Integer.MAX_VALUE){
            System.out.println(-1);
        } else{
            System.out.println(minDist);
        }

    }

    static void bfs(){
        Queue<Pos> queue = new LinkedList<>();
        queue.add(new Pos(0, 0, 1, 0));  //좌표 r, c, 이동거리(1부터시작), 벽 부순 횟수
//        visited[0][0] = true;
        visited[0][0] = 0; //처음 공사 횟수

//        int count = 1;

        while(!queue.isEmpty()){
            Pos cur = queue.poll();

            if(cur.r==(R-1) && cur.c==(C-1)){
//                minDistX = Math.min(minDistX, count);
                minDist = cur.distance;
                break;
            }

            for(int d=0;d<4;d++){
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];

                if(!isIn(nr, nc)) continue;
                if(visited[nr][nc]<=cur.isDest) continue;  //방문한 적 있으면 continue

                if(map[nr][nc]==0){
                    //다음 갈 곳이 벽이 아니면
                    visited[nr][nc] = cur.isDest;
                    queue.add(new Pos(nr, nc, cur.distance+1, cur.isDest));
                } else{
                    //다음 갈 곳이 벽이면
                    if(cur.isDest==0){
                        //벽 부순 적 없으면
                        visited[nr][nc] = cur.isDest+1; //벽 부숨
                        queue.add(new Pos(nr, nc, cur.distance+1, cur.isDest+1));
                    }
                }
            }
        }

    }

    static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }

    static class Pos{
        int r;
        int c;
        int distance;
        int isDest;

        public Pos(int r, int c, int distance, int isDest) {
            this.r = r;
            this.c = c;
            this.distance = distance;
            this.isDest = isDest;
        }
    }
}

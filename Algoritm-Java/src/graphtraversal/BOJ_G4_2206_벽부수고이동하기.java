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
    //벽을 안부수고 지나갈 수 있다면 완탐으로 벽 하나씩 부숴보기

    static int R, C;
    static int[][] map;
    static boolean[][] visited;
    static int minDist = Integer.MAX_VALUE;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        visited = new boolean[R][C];

        for(int i=0;i<R;i++){
            String str = br.readLine();
            for(int j=0;j<C;j++){
                map[i][j] = str.charAt(j)-'0';
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
        queue.add(new Pos(0, 0));
        visited[0][0] = true;

        int count = 1;

        while(!queue.isEmpty()){
            Pos cur = queue.poll();

            if(cur.r==(R-1) && cur.c==(C-1)){
                minDist = Math.min(minDist, count);
            }

            for(int d=0;d<4;d++){
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];
                if(isIn(nr, nc) && !visited[nr][nc]){
                    if(map[nr][nc]==0){
                        queue.add(new Pos(nr, nc));
                        visited[nr][nc] = true;
                        count++;
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

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}

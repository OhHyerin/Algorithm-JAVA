package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_D4_1249_보급로 {
    //최단거리

    static int N;
    static int[][] map;
    static int[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int countDest;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){
            sb.append("#").append(t).append(" ");

            N = Integer.parseInt(br.readLine());
            map = new int[N][N];


            for(int i=0;i<N;i++){
                String str = br.readLine();
                for(int j=0;j<N;j++){
                    map[i][j] = str.charAt(j)-'0';
                }
            }

            countDest = Integer.MAX_VALUE;

            bfs();
            sb.append(countDest).append("\n");

        }//t
        System.out.println(sb);
    }

    private static void bfs(){
        Queue<Pos> queue = new LinkedList<>();
        visited = new int[N][N];

        for(int i=0;i<N;i++){
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }

        visited[0][0]= 0;
        queue.add(new Pos(0, 0, 0));


        while(!queue.isEmpty()){
            Pos cur = queue.poll();

            if(cur.r==(N-1) && cur.c==(N-1)) {
                countDest = Math.min(countDest, visited[N-1][N-1]);
//                return;
            }

            if(countDest <= visited[cur.r][cur.c]) continue;

            for(int d=0;d<4;d++){
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];

                if(!isIn(nr, nc)) continue;
                if(visited[nr][nc]<=visited[cur.r][cur.c]+map[nr][nc]) continue;

                visited[nr][nc] = visited[cur.r][cur.c]+map[nr][nc];
                queue.add(new Pos(nr, nc,map[nr][nc]));

            }
        }

    }


    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<N && c<N;
    }



    private static class Pos{
        int r;
        int c;
        int depth;

        public Pos(int r, int c, int depth) {
            this.r = r;
            this.c = c;
            this.depth = depth;
        }
    }

}

package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_7576_토마토 {
    //백준 골드5
    //그래프탐색 - BFS

    //모든 토마토가 익을때까지 최소날짜를 출력한다.
    //익은 토마토가 익은 자리를 모두 queue에 입력하기때문에 depth를 알기 힘들다
    //따라서 visitDay 2차원배열을 사용하여 인접한 칸보다 1씩 더해주었다.

    static int N, M;  //N*M
    static int[][] box;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int dayCount = 0;
    static int rawCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        M = Integer.parseInt(st.nextToken()); //가로
        N = Integer.parseInt(st.nextToken()); //세로
        box = new int[N][M];

        for(int r=0;r<N;r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0;c<M;c++){
                box[r][c] = Integer.parseInt(st.nextToken());
                if(box[r][c]==0) rawCount++;
            }
        }

        bfs();

//        for(int r=0;r<N;r++){
//            for(int c=0;c<M;c++){
//                System.out.print(box[r][c]+" ");
//            }
//            System.out.println();
//        }


        boolean flag = false;
        outer:for(int r=0;r<N;r++){
            for(int c=0;c<M;c++){
                if(box[r][c]==0){
                    flag = true;
//                    System.out.println("r :"+r);
                    break outer;
                }
            }
        }

        if(flag) sb.append(-1);
        else sb.append(dayCount);

        System.out.println(sb);

    }

    private static void bfs(){
        Queue<Pos> queue = new LinkedList<>();
//        int day = 0;

        //익은 토마토 위치 queue에 추가
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(box[i][j]==1){
                    queue.offer(new Pos(i, j, 1));
                }
            }
        }

        while(!queue.isEmpty()){
            if(rawCount==0){
                return;
            }
            Pos cur = queue.poll();
            for(int d=0;d<4;d++){
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];

                if(isIn(nr, nc)){
                    if(box[nr][nc]==0){
                        //익지 않은 토마토 있으면 익은 토마토 위치에 추가
                        box[nr][nc]=1;
                        queue.offer(new Pos(nr, nc, cur.day+1));
                        dayCount = Math.max(dayCount, cur.day);
                        rawCount--;
                    }
                }
            }
        }



    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<N && c<M;
    }

    private static class Pos{
        int r, c;
        int day;

        public Pos(int r, int c, int day) {
            this.r = r;
            this.c = c;
            this.day = day;
        }
    }
}

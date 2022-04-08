package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_14502_연구소 {
    //백준 골드5
    //최대(최소)값이니까 bfs?
    //temp배열에 벽을 조합으로 세우고 bfs로 불 번지도록

    static int R, C;
    static int[][] map;
    static int[][] temp;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        temp = new int[R][C];
//        visited = new boolean[R][C];

        for(int i=0;i<R;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<C;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combination(0);
        System.out.println(max);

    }

    static void combination(int count){
        if(count==3){
            visited = new boolean[R][C];
            for(int i=0;i<R;i++){
                for(int j=0;j<C;j++){
                    temp[i][j] = map[i][j];  //map배열 복사
                }
            }

            for(int i=0;i<R;i++){
                for(int j=0;j<C;j++){
                    if(temp[i][j]==2){     //temp배열, 바이러스위치고
                        if(!visited[i][j]){  //아직 방문하지 X으면
                            bfs(i, j);  //바이러스 퍼트리기
                        }
                    }
                }
            }

            max = Math.max(max,safeCount());
            return;
        }

        //inductive part
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j]==0){
                    //빈 공간에 벽세우기
                    map[i][j] = 1;
                    combination(count+1);
                    map[i][j] = 0;
                }
            }
        }
    }

    static void bfs(int r, int c){
        visited[r][c] = true;
        for(int d=0;d<4;d++){
            int nr = r+dr[d];
            int nc = c+dc[d];

            if(isIn(nr, nc) && temp[nr][nc]==0 && !visited[nr][nc]){
                temp[nr][nc] = 2; //바이러스
                bfs(nr, nc);
            }

        }
    }

    static int safeCount(){
        int count = 0;
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(temp[i][j]==0) count++;
            }
        }
        return count;
    }

    static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }


}

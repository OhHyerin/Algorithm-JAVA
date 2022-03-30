package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_2636_치즈 {
    //백준 골드4
    //BFS
    
    //치즈 안에있는 공기는 치즈가 녹는데에 영향이 없으므로
    //치즈가 아닌, 공기의 위치를 BFS로 탐색할 것

    static int R, C;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int countCheese;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for(int i=0;i<R;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<C;j++){
                int temp = Integer.parseInt(st.nextToken());
                if(temp==1){
                    map[i][j] = 0;  //치즈 있는 곳
                    countCheese++;
                }else{
                    map[i][j] = 1;  //공기 있는 곳
                }
            }
        }

        //---------------------입력완료------------------------------

        int answer2 = 0;
        int answer1 = 1;
        count = 1;

        while(countCheese>0){
            answer2 = bfs();
            answer1 = count;
            count++;
        }


        sb.append(answer1).append("\n").append(answer2);
        System.out.println(sb);
    }

    static int bfs(){
        Queue<Pos> queue = new LinkedList<>();
        visited = new boolean[R][C];

        queue.add(new Pos(0, 0));
        visited[0][0] = true;

        int meltingCount = 0;  //해당 턴에 지워지는 치즈의 개수

        while(!queue.isEmpty()){
            Pos cur = queue.poll();

            for(int d=0;d<4;d++){
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];

                if(isIn(nr,nc) && !visited[nr][nc]){
                    if(map[nr][nc]==0){
                        map[nr][nc] = count;  //해당 위치에 몇 시간이 지났나 저장
                        meltingCount++;  //해당 턴에 지워지는 치즈의 개수 +1
                        visited[nr][nc] = true;  //방문처리
                    }else {
                        visited[nr][nc] = true;  //방문처리
                        queue.add(new Pos(nr, nc));  //위치로 이동
                    }
                }
            }
        }

        countCheese -= meltingCount;  //해당 턴에 지워지는 치즈의 개수만큼 남은 치즈 감소
        return meltingCount;
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

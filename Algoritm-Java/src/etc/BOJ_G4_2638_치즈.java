package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_2638_치즈 {
    //그래프 탐색
    //공기를 기준으로 bfs
    //G5치즈와 다른 점은 4변 중 적어도 2변 이상 실내온도 공기와 접촉한것만 없어짐

    static int R, C;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int countCheese;
    static int day;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for(int i=0;i<R;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<C;j++){
                int temp = Integer.parseInt(st.nextToken());
                map[i][j] = temp;
                if(temp==1) countCheese++;  //초기 치즈 개수 세기
            }
        }

        while(countCheese>0){ //치즈 남았으면 반복
            day++;
            bfs();
        }

        System.out.println(day);

    }

    private static void bfs(){
        Queue<Pos> queue = new LinkedList<>();
        visited = new boolean[R][C];

        queue.add(new Pos(0, 0));
        visited[0][0] = true;


        while(!queue.isEmpty()){
            Pos cur = queue.poll();

            for(int d=0;d<4;d++){
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];

                if(!isIn(nr, nc)) continue;
                if(visited[nr][nc]) continue;
                if(map[nr][nc]!=0){  //다음 위치가 치즈라면
                    map[nr][nc]++;  //치즈 위치 값 -1
                    //여기 일케하면 중간에 치즈가 0이돼서 다음 턴에 영향줌
//                    if(map[nr][nc]==3){  //만약 3이라면
//                        countCheese--;  //치즈 삭제
////                        if(countCheese<=0){
////                            return;
////                        }
//                    }
                }else{
                    visited[nr][nc] = true;
                    queue.add(new Pos(nr, nc)); //다음 위치가 공기라면 위치이동
                }
            }
        }

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j]==1 || map[i][j]==2){  //1이거나 2여서 지워지지 않은 것들은
                    map[i][j] = 1;  //다음 턴 영향 없게 다시 치즈값인 1로 초기화
                }
                if(map[i][j]>=3){  //3이상인 곳에선
                    countCheese--;  //치즈 지우고
                    map[i][j] = 0;  //공기로 초기화
                }
            }
        }

//        for(int i=0;i<R;i++){
//            for(int j=0;j<C;j++){
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();
    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }

    private static class Pos{
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}

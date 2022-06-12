package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G3_1726_로봇 {
    //시뮬
    //이동해야하는 방향이랑 문제에서 준 방향이 번호가 달라서 따로 설정해야함\
    //방향 변경 말고는 기존 bfs와 동일 (모든 지점 다들려보기)

    /*
    문제 설명
    1 : 동
    2 : 서
    3 : 남
    4 : 북

    Go k : 현재 향하고 있는 방향으로 최대 3칸 움직인다
    Turn dir : dir은 left또는 right이며, 각각 왼쪽 또는 오른쪽으로 90도 회전한다.
     */

    static int R, C;
    static int[][] map;
    static int[] dr = {0, 0, 1, -1};  //동->서->남->북
    static int[] dc = {1, -1, 0, 0};
    static Pos start;  //시작 방향과 위치
    static Pos end;  //끝나는 지점의 방향과 위치
    static int orderCnt;  //명령 횟수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R+1][C+1];

        for(int i=1;i<=R;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=C;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        start = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())-1, 0);

        st = new StringTokenizer(br.readLine());
        end = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())-1, 0);

        bfs();

//        System.out.println(start);
//        System.out.println(end);

        System.out.println(orderCnt);
    }

    static void bfs(){
        Queue<Pos> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[R+1][C+1][5];  //방향까지 한번에 담기

        queue.add(start);
        visited[start.r][start.c][start.d] = true;

        while(!queue.isEmpty()){
            Pos cur = queue.poll();

            if(cur.r==end.r && cur.c==end.c && cur.d==end.d){
                //현재 위치가 end상태의 위치와 방향 모두 일치하면 return
                orderCnt = cur.cnt;
                return;
            }

            for(int move=0;move<=3;move++){ //k가 0, 1, 2, 3일 때
                //방향잡는게 어려웠음
                int nr = cur.r+(dr[cur.d]*move);
                int nc = cur.c+(dc[cur.d]*move);

                if(!isIn(nr, nc)) break; //방향 벗어나면 break (방향 바꿔야함)
                if(map[nr][nc]==1) break; //갈 수 없는 곳이면 break (방향 바꿔야함)
                if(visited[nr][nc][cur.d]) continue;  //이미 방향까지 방문한 곳이면 continue

                visited[nr][nc][cur.d] = true;
                queue.add(new Pos(nr, nc, cur.d, cur.cnt+1));
            }

            //방향만 바꿔야되면
            for(int i=0;i<2;i++){
                int nd = 0;
                if(cur.d==0){  //왼, 오
                    nd = i==0? 3:2;
                }else if(cur.d==1){
                    nd = i==0? 2:3;
                }else if(cur.d==2){
                    nd = i==0? 0:1;
                }else if(cur.d==3){
                    nd = i==0? 1:0;
                }

                if(visited[cur.r][cur.c][nd]) continue; //nd방향으로 바꾼적 있으면 continue
//                if(cur.r==end.r && cur.c==end.c && cur.d==end.d){
//                    return;
//                }
                queue.add(new Pos(cur.r, cur.c, nd, cur.cnt+1));
                visited[cur.r][cur.c][nd] = true;
            }

        }
    }

    static boolean isIn(int r, int c){
        return r>0 && c>0 && r<=R && c<=C;
    }

    static class Pos{
        int r;
        int c;
        int d;
        int cnt;

        public Pos(int r, int c, int d, int cnt) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.cnt = cnt;

        }

        @Override
        public String toString() {
            return "Pos{" +
                    "r=" + r +
                    ", c=" + c +
                    ", d=" + d +
                    ", cnt=" + cnt +
                    '}';
        }
    }
}

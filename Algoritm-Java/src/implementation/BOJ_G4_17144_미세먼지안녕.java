package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_17144_미세먼지안녕 {
    //그래프탐색

    static int R, C, T;
    static int[][] map;
    static int upA, DownA;  //공기청정기 위치
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Queue<Dust> queue = new LinkedList<Dust>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
//        queue = new LinkedList<Dust>();

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    upA = i - 1;
                    DownA = i;
                }
            }
        }

        for(int i=0;i<T;i++) {
            makeDust();
            bfs();
            rotate();
        }

//        for(int[] m : map){
//            System.out.println(Arrays.toString(m));
//        }

        System.out.println(dustAmount());
    }

    static void makeDust(){
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j]!=-1 && map[i][j]!=0){
                    queue.offer(new Dust(i, j, map[i][j]));
                }
            }
        }
    }

    static void bfs() {
        while (!queue.isEmpty()) {
            Dust cur = queue.poll();
            if (cur.dust < 5) continue; //5보다 작으면 값이0이라 확산되지 않음

            int spreadAmount = cur.dust / 5;
            int spreadCount = 0;

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (isIn(nr, nc) && map[nr][nc]!=-1) {
                    //먼지가 확산될 공간이 범위안이고, 공기청정기 위치가 아니면
                    map[nr][nc] += spreadAmount;
                    spreadCount++;
                }

                //확산된 양만큼 현재 위치의 먼지는 감소
            }
            map[cur.r][cur.c] = map[cur.r][cur.c]-(spreadAmount*spreadCount);
        }
    }

    static void rotate(){

        //반시계방향
        for(int i=upA-1;i>0;i--){
            map[i][0] =  map[i-1][0];
        }
        for(int i=0;i<C-1;i++){
            map[0][i] = map[0][i+1];
        }
        for(int i=0;i<upA;i++){
//            System.out.println("map[i][C-1] : "+map[i][C-1]);
            map[i][C-1] = map[i+1][C-1];
//            System.out.println("map[i+1][C-1] : "+map[i][C-1]);

        }
        for(int i=C-1;i>1;i--){
            map[upA][i] = map[upA][i-1];
        }
        map[upA][1] = 0;

        //시계방향
        for(int i=DownA+1;i<R-1;i++){
            map[i][0] = map[i+1][0];
        }
        for(int i=0;i<C-1;i++){
            map[R-1][i] = map[R-1][i+1];
        }
        for(int i=R-1;i>DownA;i--){
            map[i][C-1] = map[i-1][C-1];
        }
        for(int i=C-1;i>1;i--){
            map[DownA][i] = map[DownA][i-1];
        }
        map[DownA][1] = 0;


//        for(int i=0;i<R;i++){
//            for(int j=0;j<C;j++){
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();
    }

    static int dustAmount(){
        int result = 0;
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j]==-1) continue;
                result += map[i][j];
            }
        }
        return result;
    }

    static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }


    static class Dust {
        int r;
        int c;
        int dust;

        @Override
        public String toString() {
            return "Dust{" +
                    "r=" + r +
                    ", c=" + c +
                    ", dust=" + dust +
                    '}';
        }

        public Dust(int r, int c, int dust) {
            this.r = r;
            this.c = c;
            this.dust = dust;
        }
    }

}

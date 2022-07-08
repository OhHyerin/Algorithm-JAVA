package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G2_2169_로봇조종하기 {
    //dp

    static int R, C;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

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

        //-------------------입력완료---------------------

        bfs();

    }

    private static void bfs(){
        Queue<Pos> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R+1][C+1];

        queue.add(new Pos(1, 1, map[1][1]));
        visited[1][1] = true;

        while(!queue.isEmpty()){
            Pos cur = queue.poll();

            for(int d=0;d<4;d++){

            }

        }

    }

    private static boolean isIn(int r, int c){
        return r>0 && c>0 && r<=R && c<=C;
    }

    private static class Pos{
        int r;
        int c;
        int value;

        public Pos(int r, int c, int value) {
            this.r = r;
            this.c = c;
            this.value = value;
        }
    }
}

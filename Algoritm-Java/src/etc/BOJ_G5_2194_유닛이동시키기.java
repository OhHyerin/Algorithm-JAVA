package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_2194_유닛이동시키기 {
    //백준 골드5
    //최소라는 말이 있으면 bfs

    static int n, m;
    static int a, b;
    static int k;
    static int[][] map;
    static boolean[][] visited;
    static Pos start, end;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n+1][m+1];
        visited = new boolean[n+1][m+1];

        for(int i=0;i<k;i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = 1;
        }
        st = new StringTokenizer(br.readLine());
        start = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        end = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        //--------------입력완료---------------------

//        for(int i=1;i<=n;i++){
//            for(int j=1;j<=m;j++){
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }


        System.out.println(bfs());


    }

    static int bfs(){
        Queue<Pos> queue = new LinkedList<>();

        queue.add(start);
        visited[start.r][start.c] = true;

        int move = 0;
        int min = Integer.MAX_VALUE;
        while(!queue.isEmpty()){

            int size = queue.size();
            while(size-- >0) {
                Pos cur = queue.poll();

                if (cur.c == end.c && cur.r == end.r) {
                    return move;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if (isIn(nr, nc) && !visited[nr][nc]) {
                        if (unitPossible(nr, nc)) {
                            queue.add(new Pos(nr, nc));
//                        System.out.println("nr : "+nr+" nc : "+nc);
                            visited[nr][nc] = true;
                        }
                    }
                }
            } //depth 하나 끝
            move++;
//            min = Math.min(min, move);
//            System.out.println("move : "+move);
        }
        return -1;
    }

    static boolean unitPossible(int r, int c){
        for(int i=r;i<r+a;i++){
            for(int j=c;j<c+b;j++){
                if(!isIn(i, j) || map[i][j]==1){
                    return false;
                }
            }
        }
        return true;
    }

    static boolean isIn(int r, int c){
        return r>0 && c>0 && r<=n && c<=m;
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

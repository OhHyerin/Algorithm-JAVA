package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_G3_1941_소문난칠공주 {
    //순조부

    static int[][] map;
    static Pos[] pos;
    static int result;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[5][5];
        pos = new Pos[25];

        for (int i = 0; i < 5; i++) {
            String str = br.readLine();
            for (int j = 0; j < 5; j++) {
                char temp = str.charAt(j);
                //이다솜파 : 1, 임도연파 : 0
                map[i][j] = temp == 'Y' ? 0 : 1;
            }
        }
        //----------------------입력완료----------------------

        int index = 0;
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                pos[index] = new Pos(index, r, c);
                index++;
            }
        }

//        System.out.println(Arrays.toString(pos));

        combination(0, 0, new Pos[3]);

    }

    private static void combination(int cnt, int start, Pos[] selected) {
        if (cnt == 3) {
//            System.out.println(Arrays.toString(selected));
            result += bfs(selected);
            return;
        }

        for (int i = start; i < 25; i++) {
            if (map[pos[i].r][pos[i].c] == 0) {
                selected[cnt] = pos[i];
                combination(cnt + 1, i + 1, selected);
            }
        }
    }

    private static int bfs(Pos[] selected){
        Queue<Pos> queue = new LinkedList<>();
        int rr, rc;
        for(int i=0;i<selected.length;i++){
            queue.add(selected[i]);
            rr = selected[i].r;
            rc = selected[i].c;
        }

        Pos cur = queue.poll();
        int cr = cur.r;
        int cc = cur.c;
        int cn = cur.num;

        for(int i=1;i<selected.length;i++){

            for(int d=0;d<4;d++){
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];


                Pos next = queue.poll();

                if(!isIn(nr, nc)) continue;
                if(next.r==nr && next.c==nc){
                    cur = next;
                }
            }
        }

        return 0;
    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<5 && c<5;
    }

    private static class Pos {
        int num;
        int r;
        int c;

        public Pos(int num, int r, int c) {
            this.num = num;
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "num=" + num +
                    ", r=" + r +
                    ", c=" + c +
                    '}';
        }
    }
}

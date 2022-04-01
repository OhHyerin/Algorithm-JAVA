package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
                pos[index] = new Pos(index, r, c, map[r][c]);
                index++;
            }
        }

//        System.out.println(Arrays.toString(pos));

        combination(0, 0, new Pos[7], 0);

        System.out.println(result);

    }

    private static void combination(int cnt, int start, Pos[] selected, int countY) {
        if (cnt == 7) {
            System.out.println(Arrays.toString(selected));
//            result += bfs(selected);
            bfs(selected);
            return;
        }

        if(countY>4) return;

        for (int i = start; i < 25; i++) {
            if (countY<4) {
                selected[cnt] = pos[i];
                if(selected[cnt].who==0) {
                    combination(cnt + 1, i + 1, selected, countY++);
                }else{
                    combination(cnt+1, i+1, selected, countY);
                }
            } else {
                return;
            }
        }

    }

    private static int bfs(Pos[] selected){
        Queue<Pos> queue = new LinkedList<>();
//        HashSet<Pos> hashSet = new HashSet<>();
        boolean[][] visited = new boolean[5][5];

        queue.add(new Pos(selected[0].r, selected[0].c));
        visited[selected[0].r][selected[0].c] = true;
//        hashSet.add(selected[0]);
        int count = 1;

        while(!queue.isEmpty()){
            Pos cur = queue.poll();

            for(int d=0;d<4;d++){
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];

                if(!isIn(nr, nc)) continue;
                if(visited[nr][nc]) continue;
//                if(hashSet.contains()) continue;
                boolean isHave = false;
                for(int i=1;i<selected.length;i++){
                    if(selected[i].r==nr && selected[i].c==nc){
                        isHave = true;
                        break;
                    }
                }
                if(!isHave) continue;

                queue.add(new Pos(nr, nc));
                visited[nr][nc] = true;
                count++;
            }

            if(count==7){
                result++;
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
        int who;

        public Pos(int num, int r, int c, int who) {
            this.num = num;
            this.r = r;
            this.c = c;
            this.who = who;
        }

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "num=" + num +
                    ", r=" + r +
                    ", c=" + c +
                    ", who=" + who +
                    '}';
        }
    }
}

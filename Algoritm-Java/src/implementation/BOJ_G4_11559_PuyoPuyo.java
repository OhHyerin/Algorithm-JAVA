package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_G4_11559_PuyoPuyo {
    //시뮬

    static int R = 12;
    static int C = 6;
    static char[][]map = new char[R][C];
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean isBomb;
    static int answer;
    static int bombCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0;i<R;i++){
            String str = br.readLine();
            for(int j=0;j<C;j++){
                map[i][j] = str.charAt(j);
                if(map[i][j]!='.') bombCount++;
            }
        }

//        for(int i=0;i<R;i++){
//            for(int j=0;j<C;j++){
//                System.out.print(map[i][j]);
//            }
//            System.out.println();
//        }

        bomb();
        System.out.println(answer);

    }

    private static void bomb(){
        //맨 아래 -> 위 순서로 찾기 (아래에 쌓여있고 위에는 .이니까)
        while(bombCount>0) {
            isBomb = false;
            for (int i = R - 1; i >= 0; i--) {  //아래 -> 위
//                boolean isdot = false;  //각 줄이 모드 .인지 체크
                for (int j = 0; j < C; j++) {  //왼 -> 오
                    if (map[i][j] != '.') {
                        bfs(i, j);
//                        isdot = true;
                    }
                }
//                if (!isdot) break;
            }
            if (isBomb) {
                //만약 터진게 있다면 남은 블럭들 아래로 내리기
                dropDown();
                answer++;
            }else{
                return;
            }
        }
    }

    private static void dropDown(){
        Queue<Character> cal;
        for(int c=0;c<C;c++){
            cal = new LinkedList<>();
            for(int r=R-1;r>=0;r--){
                if(map[r][c]!='.'){
                    cal.add(map[r][c]);
                    map[r][c] = '.';
                }
            }
//            System.out.println(cal);
            //한 줄이 검사가 끝나면
            if(cal.size()==0){//그 줄의 모든 값이 .이면 continue
                continue;
            }else{
                int idx = R-1;
                while(!cal.isEmpty()){
                    map[idx][c] = cal.poll();
                    idx--;
                }
            }
        }

//        System.out.println("------------------------");
//        for(int i=0;i<R;i++){
//            for(int j=0;j<C;j++){
//                System.out.print(map[i][j]);
//            }
//            System.out.println();
//        }

    }


    private static void bfs(int r, int c){
        Queue<Pos> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];
        ArrayList<Pos> bombLoc = new ArrayList<>();

        queue.add(new Pos(r, c));
        visited[r][c] = true;
        bombLoc.add(new Pos(r, c));

        while(!queue.isEmpty()){
            Pos cur = queue.poll();

            for(int d=0;d<4;d++){
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];

                if(!isIn(nr, nc)) continue;
                if(map[nr][nc]!=map[cur.r][cur.c]) continue; //색 일치하지 않으면 continue;
                if(visited[nr][nc]) continue;

                queue.add(new Pos(nr, nc));
                visited[nr][nc] = true;
                bombLoc.add(new Pos(nr, nc));
            }
        }

        //4개 이상 붙어있다면
        if(bombLoc.size()>=4){
            isBomb = true;  //터졌다는 신호 보내주고 (블럭 내리기위해)
            bombCount -= bombLoc.size();

            for(int i=0;i<bombLoc.size();i++){
                Pos loc = bombLoc.get(i);
                map[loc.r][loc.c] = '.';  //각 위치 .으로 셋팅
            }

        }




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

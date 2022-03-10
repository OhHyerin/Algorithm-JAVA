package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_1261_알고스팟 {
    //백준 골드4
    //스터디 공통문제

    static int R, C;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] count;  //벽 부순 개수 최소값 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        count = new int[R][C];

        for(int i=0;i<R;i++){
            String str = br.readLine();
            for(int j=0;j<C;j++){
                map[i][j] = str.charAt(j)-'0';
            }
        }

//        for(int i=0;i<R;i++){
//            for(int j=0;j<C;j++){
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }
        //----------------입력완료-----------------


        bfs();
        System.out.println(count[R-1][C-1]);

    }

    private static void bfs(){
        Queue<Pos> queue = new LinkedList<>();
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                count[i][j] = Integer.MAX_VALUE;  //count배열 최댓값으로 초기화 (dp역할)
            }
        }
        queue.add(new Pos(0, 0, 0));
        count[0][0] = 0;

        while(!queue.isEmpty()){
            Pos cur = queue.poll();

            if(count[cur.r][cur.c]<cur.breakCount) continue;  //현재 breakCount가 dp배열에 있는 값보다 크면 안돌아봐도됨

            for(int d=0;d<4;d++){
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];

                if(isIn(nr,nc)){
                    if(map[nr][nc]==0 && count[nr][nc]>cur.breakCount){  //다음 갈 곳이 0이고 dp저장된 배열보다 작으면
                        count[nr][nc] = cur.breakCount;  //dp배열 갱신
                        queue.add(new Pos(nr, nc, cur.breakCount));  //queue에 다음 위치 삽입
                    } else if(map[nr][nc]==1 && count[nr][nc]>cur.breakCount+1){  //다음 갈 곳이 1이고 현재 breakCount+1값이 dp저장된 배열보다 작으면
                        count[nr][nc] = cur.breakCount+1;  //dp배열 갱신
                        queue.add(new Pos(nr, nc, cur.breakCount+1));  //queue에 다음 위치 삽입
                    }
                }
            }
        }
    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }

    private static class Pos {
        int r;
        int c;
        int breakCount;

        public Pos(int r, int c, int breakCount) {
            this.r = r;
            this.c = c;
            this.breakCount = breakCount;
        }

    }
}

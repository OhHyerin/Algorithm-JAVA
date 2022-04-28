package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G4_15683_감시 {
    //시뮬레이션

    static int R, C;
    static int[][] map;
    static List<CCTV> cctvs;
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {1, 0, -1, 0};
    static int minCount = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        cctvs = new ArrayList<>();

        for(int i=0;i<R;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<C;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]>=1 && map[i][j]<=5){
                    cctvs.add(new CCTV(i, j, map[i][j]));
                }
            }
        }

        monitor(0, map);

        System.out.println(minCount);


    }

    private static void monitor(int depth, int[][] tempMap){
        if(depth==cctvs.size()){
            int count = 0;
            for(int i=0;i<R;i++){
                for(int j=0;j<C;j++){
                    if(tempMap[i][j]==0){
                        count++;
                    }
                }
            }

            minCount = Math.min(minCount, count);
            return;
        }

        CCTV cur = cctvs.get(depth);
        int[][] curMap = new int[R][C];

        switch (cur.direc){
            case 1:
                //한 방향 감시
                for(int i=0;i<4;i++){
                    curMap = copyMap(tempMap);
                    watch(curMap, cur.r, cur.c, i);
                    monitor(depth+1, curMap);
                }
                break;
            case 2:
                //두 방향 감시 (반대방향)
                for(int i=0;i<2;i++){
                    curMap = copyMap(tempMap);
                    watch(curMap, cur.r, cur.c, i);
                    watch(curMap, cur.r, cur.c, i+2);
                    monitor(depth+1, curMap);
                }
                break;
            case 3:
                //두 방향 감시 (직각방향)
                for(int i=0;i<4;i++){
                    curMap = copyMap(tempMap);
                    watch(curMap, cur.r, cur.c, i);
                    watch(curMap, cur.r, cur.c, (i+1)%4);
                    monitor(depth+1, curMap);
                }
                break;
            case 4:
                //세 방향 감시
                for(int i=0;i<4;i++){
                    curMap = copyMap(tempMap);
                    watch(curMap, cur.r, cur.c, i);
                    watch(curMap, cur.r, cur.c, (i+1)%4);
                    watch(curMap, cur.r, cur.c, (i+2)%4);
                    monitor(depth+1, curMap);
                }
                break;
            case 5:
                //네 방향 모두 감시
                curMap = copyMap(tempMap);
                watch(curMap, cur.r, cur.c, 0);
                watch(curMap, cur.r, cur.c, 1);
                watch(curMap, cur.r, cur.c, 2);
                watch(curMap, cur.r, cur.c, 3);
                monitor(depth+1, curMap);
                break;
        }


    }

    private static void watch(int[][] map, int r, int c, int dir){
        int nr = r;
        int nc = c;

        while(true){
            nr += dr[dir];
            nc += dc[dir];

            if(!isIn(nr, nc)) break;
            if(map[nr][nc]==6) break;
            if(map[nr][nc]!=0) continue;

            map[nr][nc] = -1;
        }
    }

    private static int[][] copyMap(int[][] map){
        int[][] copy = new int[R][C];

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                copy[i][j] = map[i][j];
            }
        }
        return copy;
    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }


    private static class CCTV {
        int r;
        int c;
        int direc;

        public CCTV(int r, int c, int direc) {
            this.r = r;
            this.c = c;
            this.direc = direc;
        }

    }

}

package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_14503_로봇청소기 {
    //구현, 시뮬레이션

    /*
    1. 현재 위치를 청소한다
    2. 현재 위치에서 바로 왼쪽에 청소하지 않은 빈 공간이 존재한다면
            왼쪽 방향으로 회전 -> 한 칸 전진 -> 현재 위치 청소
       빈 공간이 존재하지 않는다면
            왼쪽 방향으로 회전
    3. 1번으로 돌아가거나 후진하지 않고 2번이 4번 실행됐을 경우,
        바로 뒤쪽이 벽이라면 -> 작동을 멈춘다
        그렇지 않다면 -> 한 칸 후진한다
     */

    static int R, C;
    static int[][] map;
    static int[] dr = {-1,0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int sr, sc, sd;
    static int count;

    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        st = new StringTokenizer(br.readLine());
        sr = Integer.parseInt(st.nextToken());
        sc = Integer.parseInt(st.nextToken());
        sd = Integer.parseInt(st.nextToken());

        for(int i=0;i<R;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<C;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        move(sr, sc, sd, 0);


        System.out.println(count);
    }

    private static void move(int r, int c, int d, int depth){
        if(map[r][c]==0){
            map[r][c] = 2;
            count++;
        }


        if(depth==4){
            //b. 1번으로 돌아가거나 후진하지 않고 2a단계가 연속으로 네 번 실행되었을 경우,
            int backDir =  (d+2)%4;
            if(backDir<0) backDir = 4+backDir;
            int br = r+dr[backDir];
            int bc = c+dc[backDir];
            if(isIn(br, bc)) {
                if (map[br][bc] == 1) {
                    //바로 뒤쪽이 벽이라면 작동을 멈춘다.
                    return;
                } else {
                    //바로 뒤쪽이 벽이 아니라면 한 칸 후진한다.
                    depth = 0;
                    move(br, bc, d, depth);
                    return;
                }
            }
        }

        int leftDir = d-1;
        if(leftDir<0) leftDir = 4+leftDir;
        int nr = r+dr[leftDir];
        int nc = c+dc[leftDir];

        if(isIn(nr, nc)) {
            if (map[nr][nc] == 0) {
                //왼쪽에 청소하지 않은 빈 공간이 존재한다면
                //왼쪽 방향으로 회전한 다음 한 칸 전진하고 청소
                depth = 0;
                move(nr, nc, leftDir, depth);
                return;
            } else {
                //왼쪽에 청소한 공간이거나 빈 공간이 아닐 경우
                //방향만 왼쪽으로 회전
                move(r, c, leftDir, depth + 1);
                return;
            }
        }


    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }
}

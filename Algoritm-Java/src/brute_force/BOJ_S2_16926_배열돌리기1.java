package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S2_16926_배열돌리기1 {
    //인덱스 사용하여 배열을 돌리는 문제

    // N,M : 배열의 크기, R : 회전 수
    static int N, M, R;
    static int [][] grid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        grid = new int[N][M];

        for(int r=0;r<N;r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0;c<M;c++){
                grid[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        //---------------- 입력완료 ---------------------
        int rotation_cnt = Math.min(N, M) / 2;
        for(int i=0;i<R;i++) {
            rotate(rotation_cnt);
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                sb.append(grid[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    public static void rotate(int r){
        for(int depth = 0; depth<r;depth++){
            int temp = grid[depth][depth];

            //위 왼쪽으로
            for(int i=depth+1;i<M-depth;i++){
                grid[depth][i-1] = grid[depth][i];
            }

            //오른 위로
            for(int i=depth+1;i<N-depth;i++){
                grid[i-1][M-1-depth] = grid[i][M-1-depth];
            }

            //아래 오른쪽으로
            for(int i=M-2-depth;i>=depth;i--){
                grid[N-1-depth][i+1] = grid[N-1-depth][i];
            }

            //왼 아래로
            for(int i=N-2-depth; i>=depth; i--){
                grid[i+1][depth] = grid[i][depth];
            }

            grid[depth+1][depth] = temp;
        }
    }


}

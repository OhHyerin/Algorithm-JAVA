package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_16927_배열돌리기2 {
    //인덱스 사용하여 배열을 돌리는 문제
    //배열돌리기1과 다른점은 R의 크기가 매우 크다.
    //시간 초과 조심

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
        int depth = Math.min(N, M) / 2;
        
        //depth에 맞춰서 돌리기
        for(int d=0;d<depth;d++){
            //R이 매우 큼! -> 한바퀴 돌면 원래와 같으니까 없는셈치기
            int round = (N-1-2*d)*2 + (M-1-2*d)*2;
            int cnt = R%round;
            for(int r=0;r<cnt;r++){
                rotate(d);
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                sb.append(grid[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    public static void rotate(int d){
        int keep = grid[d][d];
        // 위쪽라인, 왼쪽으로 한칸씩
        for(int c=d+1 ; c<M-d;c++){
            grid[d][c-1] = grid[d][c];
        }

        //오른쪽 위로 한칸씩
        for(int r=d+1;r<N-d;r++){
            grid[r-1][M-1-d] = grid[r][M-1-d];
        }

        //아래라인, 오른쪽으로 한칸씩
        for(int c=M-d-2;c>=d;c--){
            grid[N-d-1][c+1] = grid[N-d-1][c];
        }
        
        //왼쪽 아래로 한칸씩
        for(int r=N-d-2;r>=d;r--){
            grid[r+1][d] = grid[r][d];
        }
        
        //keep헤뒀던거 넣어주기
        grid[d+1][d] = keep;

    }
}

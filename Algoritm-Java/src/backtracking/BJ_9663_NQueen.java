package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_9663_NQueen {
    //백준
    //N-Queen(골드5)
    //1차원 배열로 풀어보기
    //System.exit(0) -> 재귀가 너무 깊은 경우 스택 초기화

    static int n; //nxn체스판
    static int[][] chess;
    static int count;
    static int result=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        chess = new int[n+1][n+1];

        for(int i=1;i<=n;i++) {
            for (int j = 1; j <= n; j++) {
                set_chess(i, j);
            }
        }

        System.out.println(result);

    }

    static void set_chess(int x, int y){
//        count = 0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                chess[i][j] = 0;
            }
        }
        for(int i=1;i<=n;i++){
            chess[x][i] = 1;
            chess[i][y] = 1;
        }
        for(int i=1;i<=n;i++){
            if(x-i>=0 && y-i>=0){
                chess[x-i][y-i] = 1;
            }
            if(x+i<=n && y+i<=n){
                chess[x+i][y+i] = 1;
            }
            if(x-i>=0 && y+i<=n){
                chess[x-i][y+i] = 1;
            }
            if(x+i<=n && y-i>=0){
                chess[x+i][y-i] = 1;
            }
            //Math.abs(col[i]-col[row] == row-i //기울기로 대각선 풀기
        }

//        for(int i=1;i<=n;i++){
//            for(int j=1;j<=n;j++){
//                if(chess[i][j]==0){
//                    count++;
//                }
//            }
//        }
//
//        if(count>=n){
//            result++;
//        }
    }

    static void dfs(){

    }
}


package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_9663_NQueen {
    //백준
    //N-Queen(골드5)
    //1차원 배열로 풀어보기
    //System.exit(0) -> 재귀가 너무 깊은 경우 스택 초기화

    static int n; //nxn체스판
    static int[] chess;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        chess = new int[n];

        dfs(0);

        System.out.println(count);

    }

    static void dfs(int depth){
        if(depth==n){
            count++;
            return;
        }
        for(int i=0;i<n;i++){
            if(check(depth, i)){
                chess[depth]=i;
                dfs(depth+1);
            }
        }
    }

    static boolean check(int depth, int col){
        for(int i=0;i<depth;i++){
            if(chess[i]==col) return false;
            if(Math.abs(i-depth)==Math.abs(chess[i]-col)) return false;
        }
        return true;
    }
}


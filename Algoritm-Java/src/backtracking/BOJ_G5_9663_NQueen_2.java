package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_9663_NQueen_2 {
    //백준 골드5
    //백트래킹
    //2번째 풀이
    //dfs

    static int n;
    static int[] chess;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        chess = new int[n];

        dfs(0);

    }

    private static void dfs(int depth){
        if(depth==n){ //depth가 전제 row값과 같으면
            cnt++; //count추가 뒤 return
            return;
        }

        for(int i=0;i<n;i++){

        }
    }

}

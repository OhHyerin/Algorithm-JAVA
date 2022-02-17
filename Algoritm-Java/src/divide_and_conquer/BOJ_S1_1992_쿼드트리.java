package divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S1_1992_쿼드트리 {
    //백준 실버1
    //쿼드 트리 알고리즘

    static int N;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i=0;i<N;i++){
            String str = br.readLine();
            for(int j=0;j<N;j++){
                map[i][j] = str.charAt(j)-'0';
            }
        }
        //----------입력완료------------
        quardTree(0, 0, N);
        System.out.println(sb);
    }

    static void quardTree(int r, int c, int size){
        if(isPossible(r, c, size)){
            sb.append(map[r][c]);
            return;
        }

        sb.append("(");
        quardTree(r, c, size/2);
        quardTree(r, c+size/2, size/2);
        quardTree(r+size/2, c, size/2);
        quardTree(r+size/2, c+size/2, size/2);
        sb.append(")");
    }

    static boolean isPossible(int r, int c, int size){
        int tmp = map[r][c];

        for(int i=r;i<r+size;i++){
            for(int j=c;j<c+size;j++){
                if(tmp != map[i][j]){
                    return false;
                }
            }
        }
        return true;
    }


}

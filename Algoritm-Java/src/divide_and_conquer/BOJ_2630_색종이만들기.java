package divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2630_색종이만들기 {
    //백준 실버3
    //분할정복

    static int n;
    static int[][] paper;
    static int white=0, blue=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        paper = new int[n][n];
        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divide(n, 0, 0);
        System.out.println(white);
        System.out.println(blue);
    }

    static void divide(int n, int r, int c){
        if(colorCheck(n, r, c)){
            //만약에 r+n, r+c까지 모든 컬러가 같다면
            if(paper[r][c]==0){
                white++;
            }else{
                blue++;
            }
            return;
        }
        n /= 2;
        divide(n, r, c);
        divide(n, r+n, c);
        divide(n, r, c+n);
        divide(n, r+n, c+n);
    }

    static boolean colorCheck(int n, int r, int c){
        int color = paper[r][c];

        for(int i=r;i<r+n;i++){
            for(int j=c;j<c+n;j++){
                if(paper[i][j]!=color){
                    return false;
                }
            }
        }
        return true;
    }
}

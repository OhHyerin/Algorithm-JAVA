package divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2630_색종이만들기 {
    //백준 실버3

    static int N;
    static int[][] paper;
    static int cnt_white=0, cnt_blue=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        paper = new int[N][N];
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divide(N, 0,0);

        System.out.println(cnt_white);
        System.out.println(cnt_blue);
    }

    static void divide(int n, int r, int c){
        if(color(n, r, c)){
            //만약에 컬러 다 같음
            if(paper[r][c]==0){
                cnt_white++;
            }else{
                cnt_blue++;
            }
            return;
        }

        n /= 2;
        divide(n, r, c);
        divide(n, r, c+n);
        divide(n, r+n, c);
        divide(n, r+n, c+n);
    }

    static boolean color(int n, int r, int c){
        int color = paper[r][c];
        for(int i=r;i<r+n;i++){
            for(int j=c;j<c+n;j++){
                if(paper[i][j] != color){
                    return false;
                }
            }
        }
        return true;
    }
}

package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_B1_2563_색종이 {
    //2차원 배열
    //색종이 들어가는 부분을 1로 설정

    static int[][] paper = new int[100][100];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int cnt = 0;
        for(int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int curx = Integer.parseInt(st.nextToken());
            int cury = Integer.parseInt(st.nextToken());
            for(int x=curx;x<curx+10;x++){
                for(int y=cury;y<cury+10;y++){
                    paper[x][y] = 1;
                }
            }
        }
        for(int i=0;i<100;i++){
            for(int j=0;j<100;j++){
                if(paper[i][j]==1){
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}

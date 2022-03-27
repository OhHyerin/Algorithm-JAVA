package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_16918_봄버맨 {
    //백준 실버1
    //스터디 - 코테광탈

    static int R, C;  //R x C map
    static int N;   //N : N초가 흐른 후 격자판 상태
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for(int i=0;i<R;i++){
            String str = br.readLine();
            for(int j=0;j<C;j++){
                char tmp = str.charAt(j);
                if(tmp=='.'){
                    map[i][j] = 0;  //비어있는 공간
                }else{
                    map[i][j] = 1;  //초기 폭탄 설치 위치
                }
            }
        }

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }

    }

}

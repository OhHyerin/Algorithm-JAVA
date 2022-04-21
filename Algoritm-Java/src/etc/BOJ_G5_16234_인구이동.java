package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_16234_인구이동 {
    //구현, 시뮬레이션

    /*
    1. 인접한 두 나라 인구차이가 L이상, R이하라면 국경선을 하루동안 엶
    2. 국경선 모두 열렸으면, 인구 이동 시작
    3. 국경선이 열려있는 두 나라를 하루동안 연합이라고 한다.
    4. 연합을 이루고 있는 각 칸의 인구수는 (연합의 인구수)/(연합을 이루고 있는 칸의 개수)가 된다.(소수점 버림)
    5. 연합 해체하고, 모든 국경선 닫음
     */

    static int N; //N*N
    static int L, R;  //L이상 R이하
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }



    }

    private static void open(){
        int index = 0;



    }

}

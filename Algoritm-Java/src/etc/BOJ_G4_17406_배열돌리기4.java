package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G4_17406_배열돌리기4 {
    //구현, 완탐, 백트래킹

    static int R, C;
    static int K;  //회전 연산의 개수
    static int[][] map;
    static int[][] rotate;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[R+1][C+1];
        rotate = new int[K][3];

        for(int i=1;i<=R;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=C;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());
            rotate[i][0] = Integer.parseInt(st.nextToken());
            rotate[i][1] = Integer.parseInt(st.nextToken());
            rotate[i][2] = Integer.parseInt(st.nextToken());
        }

        //-----------------입력완료---------------------

        //순열
        permutation(0, new int[K], new boolean[K]);



    }

    private static void permutation(int cnt, int[] selected, boolean[] isSelected){
        if(cnt==K){
            System.out.println(Arrays.toString(selected));
            return;
        }

        for(int i=0;i<K;i++){
            if(isSelected[i]) continue;

            isSelected[i] = true;
            selected[cnt] = i;
            permutation(cnt+1, selected, isSelected);
            isSelected[i] = false;

        }
    }
}

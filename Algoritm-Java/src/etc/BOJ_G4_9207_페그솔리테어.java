package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_9207_페그솔리테어 {
    //구현, 백트래킹

    static char[][] map;
    static int R = 5;
    static int C = 9;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int minPin = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){

            map = new char[R][C];
            minPin = Integer.MAX_VALUE;

            int cntPin = 0;

            for(int i=0;i<R;i++){
                String str = br.readLine();
                for(int j=0;j<C;j++){
                    map[i][j] = str.charAt(j);
                    if(map[i][j]=='o'){
                        cntPin++;
                    }
                }
            }

            br.readLine();  //공백














        }



    }
}

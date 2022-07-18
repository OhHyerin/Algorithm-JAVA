package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G3_16985_Maaaaaaaaaze {
    //완탐, 구현, 그래프이론, BFS


    static int[][][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[5][5][5];

        for(int r=0;r<5;r++){
            for(int c=0;c<5;c++){
                st = new StringTokenizer(br.readLine());
                for(int z=0;z<5;z++){
                    map[r][c][z] = Integer.parseInt(st.nextToken());
                }
            }
        }

        //----------------입력완료------------------

    }

    private static class Pos{
        int r;
        int c;
        int z;
        int cnt;

        public Pos(int r, int c, int z, int cnt) {
            this.r = r;
            this.c = c;
            this.z = z;
            this.cnt = cnt;
        }
    }
}

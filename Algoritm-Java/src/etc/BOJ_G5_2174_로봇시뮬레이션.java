package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_G5_2174_·Îº¿½Ã¹Ä·¹ÀÌ¼Ç {
    //ÄÚÅ×±¤Å» ¹®Á¦Áý

    static int A, B;
    static int N, M;
    static ArrayList<Robot> robots;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        robots = new ArrayList<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);

            robots.add(new Robot(y, x, dir));
        }

    }




    private static class Robot{
        int r;
        int c;
        char direct;

        public Robot(int r, int c, char direct) {
            this.r = r;
            this.c = c;
            this.direct = direct;
        }
    }
}

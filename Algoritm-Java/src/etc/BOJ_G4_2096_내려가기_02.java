package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_2096_내려가기_02 {
    //dp

    static int N;
    static int[][] map;
    static int minAnswer = Integer.MAX_VALUE;
    static int maxAnswer = Integer.MIN_VALUE;
    static int[][] maxDp;
    static int[][] minDp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new int[N][3];
        maxDp = new int[N][3];
        minDp = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        maxDp[0][0] = minDp[0][0] = map[0][0];
        maxDp[0][1] = minDp[0][1] = map[0][1];
        maxDp[0][2] = minDp[0][2] = map[0][2];

        for (int i = 1; i < N; i++) {
            maxDp[i][0] = Math.max(maxDp[i-1][0], maxDp[i-1][1])+map[i][0];
            minDp[i][0] = Math.min(minDp[i-1][0], minDp[i-1][1])+map[i][0];

            maxDp[i][1] = Math.max(maxDp[i-1][0], Math.max(maxDp[i-1][1], maxDp[i-1][2]))+map[i][1];
            minDp[i][1] = Math.min(minDp[i-1][0], Math.min(minDp[i-1][1], minDp[i-1][2]))+map[i][1];

            maxDp[i][2] = Math.max(maxDp[i-1][1], maxDp[i-1][2])+map[i][2];
            minDp[i][2] = Math.min(minDp[i-1][1], minDp[i-1][2])+map[i][2];
        }

        maxAnswer = Math.max(maxDp[N-1][0], Math.max(maxDp[N-1][1], maxDp[N-1][2]));
        minAnswer = Math.min(minDp[N-1][0], Math.min(minDp[N-1][1], minDp[N-1][2]));

        System.out.println(maxAnswer + " " + minAnswer);
//        System.out.println(minAnswer);

    }

}

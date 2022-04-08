package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6987_월드컵 {
    //완전탐색, 백트래킹

    static int[][] inputs = new int[4][18];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i=0;i<4;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<18;j++){
                inputs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

    }
}

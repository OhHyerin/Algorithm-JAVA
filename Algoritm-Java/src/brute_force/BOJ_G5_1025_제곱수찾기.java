package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_1025_제곱수찾기 {
    //백준 골드5
    //완탐?

    static int n, m;
    static int[][] grid;
    static double make_num;
    static double max = Double.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        grid = new int[n][m];

        for(int r=0;r<n;r++){
            String str = br.readLine();
            for(int c=0;c<m;c++){
                grid[r][c] = str.charAt(c)-'0';
            }
        }
        //----------------입력완료------------------

    }
    private static void search(int dr, int dc){
        //(i,j) : 등차수열 시작점
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){

            }
        }
    }

    private static void isSquared(double square){
        //pow(숫자, 거듭제곱 횟수) : 거듭제곱을 계산해주는 함수
        //sqrt(숫자) : 제곱근을 구해주는 함수
        double sqrt = (double)Math.sqrt(square);
        if(square==Math.pow(sqrt, 2)){
            max = Math.max(max, square);
        }
    }
}

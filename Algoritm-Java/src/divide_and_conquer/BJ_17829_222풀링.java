package divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_17829_222풀링 {
    //백준 실버2

    static int n;
    static int[][] grid;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        n = Integer.parseInt(br.readLine());
        grid = new int[n+1][n+1];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //-----------입력완료---------------------

        while(n!=1){
            n = n/2;
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    grid[i][j] = find_second(i, j);
                }
            }
        }

        System.out.println(grid[0][0]);



    }

    static int find_second(int x, int y){
        int[] arr = new int[4];
        arr[0] = grid[x][y];
        arr[1] = grid[x][y+1];
        arr[2] = grid[x+1][y];
        arr[3] = grid[x+1][y+1];
        Arrays.sort(arr);
        return arr[2];
    }

}

package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_16935_배열돌리기3 {
    //백준 실버1

    static int n, m, r;
    static int[][] grid;
    static int which;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        grid = new int[n][m];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
//        for(int[] i:grid){
//            System.out.println(Arrays.toString(i));
//        }
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<r;i++) {
            which = Integer.parseInt(st.nextToken());
            //------------------------입력완료---------------------
            switch (which) {
                case 1:
                    transform_1();
                    break;
                case 2:
                transform_2();
                    break;
                case 3:
                transform_3();
                    break;
                case 4:
                transform_4();
                    break;
                case 5:
                transform_5();
                    break;
                case 6:
                transform_6();
                    break;
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                sb.append(grid[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }
    static void transform_1(){
       int[][] result = new int[n][m];

       for(int i=0;i<n;i++){
           for(int j=0;j<m;j++){
               result[n-i-1][j] = grid[i][j];
           }
       }
        rotateGrid(result);
    }

    static void transform_2() {
        int[][] result = new int[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                result[i][m-j-1] = grid[i][j];
            }
        }
        rotateGrid(result);
    }

    static void transform_3(){
        int[][] result = new int[m][n];

        int c = n-1;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                result[j][c] = grid[i][j];
            }
            c--;
        }
        rotateGrid(result);
    }

    static void transform_4(){
        int [][] result = new int[m][n];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++) {
                result[m - j - 1][i] = grid[i][j];
            }
        }
        rotateGrid(result);
    }

    static void transform_5(){
        int[][] result = new int[n][m];

        int mid_r = n/2;
        int mid_c = m/2;

        //1->2
        for(int i=0;i<mid_r;i++){
            for(int j=0;j<mid_c;j++){
                result[i][mid_c+j] = grid[i][j];
            }
        }
        //2->3
        for(int i=0;i<mid_r;i++){
            for(int j=mid_c;j<m;j++){
                result[mid_r+i][j] = grid[i][j];
            }
        }
        //3->4
        for(int i=mid_r;i<n;i++){
            int c = 0;
            for(int j=mid_c;j<m;j++){
                result[i][c] = grid[i][j];
                c++;
            }
        }
        //4->1
        int r = 0;
        for(int i=mid_r;i<n;i++){
            for(int j=0;j<mid_c;j++){
                result[r][j] = grid[i][j];
            }
            r++;
        }
        rotateGrid(result);
    }

    static void transform_6(){
        int[][] result = new int[n][m];

        int mid_r = n/2;
        int mid_c = m/2;

        //1->4
        for(int i=0;i<mid_r;i++){
            for(int j=0;j<mid_c;j++){
                result[mid_r+i][j] = grid[i][j];
            }
        }
        //4->3
        for(int i=mid_r;i<n;i++){
            for(int j=0;j<mid_c;j++){
                result[i][mid_c+j] = grid[i][j];
            }
        }
        //3->2
        int r=0;
        for(int i=mid_r;i<n;i++){
            for(int j=mid_c;j<m;j++){
                result[r][j] = grid[i][j];
            }
            r++;
        }
        //2->1
        for(int i=0;i<mid_r;i++){
            int c=0;
            for(int j=mid_c;j<m;j++){
                result[i][c] = grid[i][j];
                c++;
            }
        }
        rotateGrid(result);
    }



    static void rotateGrid(int[][] result){
        if(result.length!=n){
            int temp = n;
            n=m;
            m=temp;
        }
        grid = result;


    }
}

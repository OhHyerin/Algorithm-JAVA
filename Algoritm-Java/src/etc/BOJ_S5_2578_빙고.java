package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S5_2578_빙고 {
    //IM 대비

    static int[][] map;
    static int[][] remove;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[5][5];
        remove = new int[5][5];

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                remove[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        outer:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int tmp = remove[i][j];
                result++;

                for (int r = 0; r < 5; r++) {
                    for (int c = 0; c < 5; c++) {
                        if (map[r][c] == tmp) {
                            map[r][c] = 0;
                            if(isBingo()) break outer;
                        }
                    }
                }
            }
        }

//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 5; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }


        System.out.println(result);
    }

    static boolean isBingo() {
        int zeroCount = 0;
        int count = 0;
        //가로
        for (int i = 0; i < 5; i++) {
            for(int j=0;j<5;j++) {
                if (map[i][j] == 0) zeroCount++;
            }
            if(zeroCount==5) count++;
            zeroCount=0;
        }

        //세로
        for (int i = 0; i < 5; i++) {
            for(int j=0;j<5;j++) {
                if (map[j][i] == 0) zeroCount++;
            }
            if(zeroCount==5) count++;
            zeroCount=0;
        }

        //대각선(왼->오)
        for (int i = 0; i < 5; i++) {
            if (map[i][i] == 0) zeroCount++;
        }
        if (zeroCount == 5) count++;

        //대각선(오->왼)
        zeroCount = 0;
        for (int i = 0; i < 5; i++) {
            if (map[i][4 - i] == 0) zeroCount++;
        }
        if (zeroCount == 5) count++;

        if(count>=3) return true;
        return false;

    }
}

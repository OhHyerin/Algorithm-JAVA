package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S2_14430_�ڿ�ĳ�� {
    //���� �ǹ�2
    //�����ǹ���
    //dfs�� Ǯ���� �� �ð��ʰ�
    //�׷���ó�� �������� �ܼ� 2���� dp����

    static int R, C;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
//        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        dp = new int[R][C];

        for(int i=0;i<R;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<C;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = map[0][0];
        //0��° �� dp����
        for(int i=1;i<R;i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][0]+map[i][0]);
        }
        //0��° �� dp����
        for(int i=1;i<C;i++){
            dp[0][i] = Math.max(dp[0][i-1], dp[0][i-1]+map[0][i]);
        }


        findMax();

//        for(int i=0;i<R;i++){
//            for(int j=0;j<C;j++){
//                System.out.print(dp[i][j]);
//            }
//            System.out.println();
//        }


        System.out.println(dp[R-1][C-1]);

    }

    static void findMax(){
        for(int i=1;i<R;i++){
            for(int j=1;j<C;j++){
                dp[i][j] = Math.max(dp[i-1][j]+map[i][j], dp[i][j-1]+map[i][j]);
            }
        }
    }


}

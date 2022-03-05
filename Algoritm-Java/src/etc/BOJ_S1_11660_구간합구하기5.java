package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_11660_구간합구하기5 {
    //백준 실버1
    //오늘의문제
    //기존 for문으로 풀면 시간초과
    //dp

    /*누적값으로 dp 구하는 방법
    (i,j)위치값은 dp[i-1][j](row전값) + dp[i][j-1](col값) - dp[i-1][j-1](대각선값) + map[i][j](현재 map값)
     */

    /*dp로 (r1,c1) ~ (r2,c2)까지 구하는 방법
    dp[r2][c2]-dp[r1-1][c2]-dp[r2][c1-1]+dp[r1-1][c1-1]
     */

    static int N;
    static int T;
    static int[][] map;
    static int[][] dp;
    static int r1,r2,c1,c2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        dp = new int[N+1][N+1];

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[1][1] = map[1][1];
        //dp 열 셋팅
        for(int i=2;i<=N;i++){
            dp[i][1] = dp[i-1][1]+map[i][1];
        }
        //dp 행 셋팅
        for(int i=2;i<=N;i++){
            dp[1][i] = dp[1][i-1]+map[1][i];
        }

        for(int i=2;i<=N;i++){
            for(int j=2;j<=N;j++){
                dp[i][j] = dp[i-1][j]+dp[i][j-1]-dp[i-1][j-1]+map[i][j];
            }
        }


//        for(int i=1;i<=N;i++){
//            for(int j=1;j<=N;j++){
//                System.out.print(dp[i][j]+" ");
//            }
//            System.out.println();
//        }


        for(int i=0;i<T;i++){
            st = new StringTokenizer(br.readLine());
            r1 = Integer.parseInt(st.nextToken());
            c1 = Integer.parseInt(st.nextToken());
            r2 = Integer.parseInt(st.nextToken());
            c2 = Integer.parseInt(st.nextToken());

            int count = dp[r2][c2]-dp[r1-1][c2]-dp[r2][c1-1]+dp[r1-1][c1-1];
            sb.append(count).append("\n");
        }

        System.out.println(sb);

    }
}

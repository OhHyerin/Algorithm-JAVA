package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_1520_내리막길 {
    //백준 골드4
    //최단경로가 아니라 갈 수 있는 경우의 수를 구하므로 dfs라고 예측했다.

    //기존 dfs에 현재보다 다음갈 곳이 값이 더 작은 조건만 넣어줬는데 시간초과
    
    //dfs+dp  //그래프탐색에서 dp와 함께 쓰이는 문제 기출 많음


    static int R, C;
    static int[][] map;
    static int[][] dp;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        dp = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = Integer.MIN_VALUE;
            }
        }

//        dfs(0, 0);
        System.out.println(dfs(0,0));

    }

    static int dfs(int r, int c) {
        if (r == (R - 1) && c == (C - 1)) {
//            count++;
            return 1; //끝까지 도달했으면 count 1 더해줌
        }

        if(dp[r][c]!=Integer.MIN_VALUE){
            //현재 위치의 dp(count)가 초기값이 아니면 return해줌(도달했다는 뜻이므로)
            return dp[r][c];
        }

//        int tmp = map[r][c];
        dp[r][c] = 0; //처음 시작위치 minvalue -> 0으로 방문처리
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

//            if(isIn(nr, nc) && map[nr][nc]<tmp){
//                dfs(nr, nc);
//            }

            //다음 갈 곳이 범위 안에 있고, 현재위치보다 값이 작으면 dp배열에 count증가
            if(isIn(nr, nc) && map[r][c]>map[nr][nc]){
                dp[r][c] += dfs(nr, nc);
            }
        }

        return dp[r][c];
    }

    static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }
}

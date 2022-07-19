package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G3_17090_미로탈출하기 {
    //그래프탐색

    /*
    U인 경우에는 (r-1, c)로 이동해야 한다.
    R인 경우에는 (r, c+1)로 이동해야 한다.
    D인 경우에는 (r+1, c)로 이동해야 한다.
    L인 경우에는 (r, c-1)로 이동해야 한다.
     */

    static int R, C;
    static char[][] map;
    static int[][] dp;  //default : 0, 불가 : -1, 가능 : 1
    static int count;
//    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        dp = new int[R][C];
//        visited = new boolean[R][C];

        for(int i=0;i<R;i++){
            String str = br.readLine();
            for(int j=0;j<C;j++){
                map[i][j] = str.charAt(j);
            }
        }



        //--------------입력 완료-----------------------
        for(int i=R-1;i>=0;i--){
            for(int j=C-1;j>=0;j--){
                if(dp[i][j]==-1) continue;
                else if(dp[i][j]==1) {
                    count++;
                    continue;
                }
                dfs(i, j, i, j, new boolean[R][C]);
            }
        }

        System.out.println(count);

    }

    private static void dfs(int sr, int sc, int r, int c, boolean[][] visited){
        if(!isIn(r, c) || dp[r][c]==1){
            //범위 벗어나면 탈출, (sr, sc)위치의 dp값 1로 설정
            count ++;
            dp[sr][sc] = 1;
            return;
        }

        if(visited[r][c] || dp[r][c]==-1){
            dp[sr][sc] = -1;
            return;
        }

        visited[r][c] = true;

        switch (map[r][c]){
            case 'U' :
                dfs(sr, sc, r-1, c, visited);
                break;
            case 'R' :
                dfs(sr, sc, r, c+1, visited);
                break;
            case 'D' :
                dfs(sr, sc, r+1, c, visited);
                break;
            case 'L' :
                dfs(sr, sc, r, c-1, visited);
                break;
        }
    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }
}

package graphtraversal;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
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
    static boolean[][] dp;  //default : 0, 불가 : -1, 가능 : 1
    static boolean[][] visited;
    static Stack<Point> stack = new Stack<>();
//    static HashSet<Point> possible = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        dp = new boolean[R][C];
        visited = new boolean[R][C];

        for(int i=0;i<R;i++){
            String str = br.readLine();
            for(int j=0;j<C;j++){
                map[i][j] = str.charAt(j);
            }
        }



        //--------------입력 완료-----------------------
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(visited[i][j]) continue;

//                dfs(i, j, i, j, 0);
                dfs(i, j);

//                System.out.println("i : "+i+"  j : "+j);
//                for(int r=0;r<R;r++){
//                    for(int c=0;c<C;c++){
//                        System.out.print(visited[r][c]+" ");
//                    }
//                    System.out.println();
//                }
//                System.out.println("count : "+count);
//                System.out.println();

            }
        }

        System.out.println(getCount());

    }

//    private static void dfs(int sr, int sc, int r, int c, int cnt){
    private static void dfs(int r, int c){
        if(!isIn(r, c)){  //범위를 벗어났으면
            while(!stack.isEmpty()){  //왔던길 모두 탐색하면서
                Point pos = stack.pop();
                dp[pos.x][pos.y] = true;  //가능으로 표시
            }
            return;
        }

        if(visited[r][c]){  //싸이클이 생기면
//            dp[sr][sc] = -1;
            while(!stack.isEmpty()){  //왔던길 모두 탐색하면서
                Point pos = stack.pop();
                dp[pos.x][pos.y] = dp[r][c];  //불가능으로 표시
            }
            return;
        }

        visited[r][c] = true;
        stack.push(new Point(r, c));  //경로 모두 stack에 넣어주기

        switch (map[r][c]){
            case 'U' :
//                dfs(sr, sc, r-1, c,cnt+1);
                dfs( r-1, c);
                break;
            case 'R' :
//                dfs(sr, sc, r, c+1,cnt+1);
                dfs( r, c+1);
                break;
            case 'D' :
//                dfs(sr, sc, r+1, c,  cnt+1);
                dfs( r+1, c);
                break;
            case 'L' :
//                dfs(sr, sc, r, c-1,cnt+1);
                dfs( r, c-1);
                break;
        }
    }

    private static int getCount(){
        int count = 0;
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(dp[i][j]==true) count++;
            }
        }
        return count;
    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }
}

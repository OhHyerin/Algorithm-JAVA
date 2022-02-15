package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_1987_알파벳 {
    //백준 골드4
    //백트래킹
    //bfs로 풀었다가 dfs로 변경
    //bfs : 동시에 같은 레벨의 다른 접점을 접근하기 때문에 만약 2레벨의 같은 알파벳이 있으면 방문 여부를 파악하기 힘듦

    static int R, C;
    static char[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[] visited;
    static int cnt;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[26];

        for(int i=0;i<R;i++){
            String str = br.readLine();
            for(int j=0;j<C;j++){
                map[i][j] = str.charAt(j);
            }
        }
        //--------------입력완료-------------------------
        dfs(0, 0, 0);
        System.out.println(max);

    }

    private static void dfs(int r, int c, int cnt){
        if(visited[map[r][c]-65]){  //해당 알파벳에 모두 방문했으면
            max = Math.max(max, cnt);  //cnt의 최댓값 비교
            return;
        }

        visited[map[r][c]-65] = true;  //해당 알파벳 방문처리(아스키코드 대문자 65~)
        for(int d=0;d<4;d++){ //사방탐색
            int nextR = r+dr[d];
            int nextC = c+dc[d];

            if(!isIn(nextR, nextC)) continue;  //범위 안에 없으면 continue
            dfs(nextR, nextC, cnt+1);
        }
        visited[map[r][c]-65] = false;  //다른 길로 갈 수도 있으니까 false (true면 재귀에서 돌아갈 것!)
    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }

    private static class Pos{
        int r;
        int c;
        Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}

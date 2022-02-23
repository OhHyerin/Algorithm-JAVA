package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_10026_적록색약 {
    //백준 골드 5
    //같은 구역 구하는 문제
    //DFS

    //일반인의 구역 개수를 먼저 구하고
    //visited배열을 초기화, drawing배열에서 기존 G를 R로 변경(똑같이본다고 했으니까)
    //적록색약 사람의 구역 개수 구하기

    static int n;
    static char[][] drawing;
    static boolean[][] visited;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        drawing = new char[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                drawing[i][j] = str.charAt(j);
            }
        }

        int countX = 0, countO = 0;
        //일반인 구역 개수 구하기
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(!visited[i][j]){
                    //새로운 구역일 때
                    dfs(i, j);
                    countX++;
                }
            }
        }
        // G를 R로 변경하기
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(drawing[i][j]=='G')
                    drawing[i][j]='R';
            }
        }
        //적록색약인 사람 구역 개수 구하기
        //구하기 전 visited배열 초기화
        visited = new boolean[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(!visited[i][j]){
                    //새로운 구역일 때
                    dfs(i, j);
                    countO++;
                }
            }
        }

        sb.append(countX).append(" ").append(countO);
        System.out.println(sb);

    }

    private static void dfs(int r, int c) {
        visited[r][c] = true;

        char temp = drawing[r][c];

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(isIn(nr, nc) && !visited[nr][nc]){
                if(drawing[nr][nc]==temp){
                    dfs(nr, nc);
                }
            }
        }
    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<n && c<n;
    }


}

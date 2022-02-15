package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_1987_���ĺ� {
    //���� ���4
    //��Ʈ��ŷ
    //bfs�� Ǯ���ٰ� dfs�� ����
    //bfs : ���ÿ� ���� ������ �ٸ� ������ �����ϱ� ������ ���� 2������ ���� ���ĺ��� ������ �湮 ���θ� �ľ��ϱ� ����

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
        //--------------�Է¿Ϸ�-------------------------
        dfs(0, 0, 0);
        System.out.println(max);

    }

    private static void dfs(int r, int c, int cnt){
        if(visited[map[r][c]-65]){  //�ش� ���ĺ��� ��� �湮������
            max = Math.max(max, cnt);  //cnt�� �ִ� ��
            return;
        }

        visited[map[r][c]-65] = true;  //�ش� ���ĺ� �湮ó��(�ƽ�Ű�ڵ� �빮�� 65~)
        for(int d=0;d<4;d++){ //���Ž��
            int nextR = r+dr[d];
            int nextC = c+dc[d];

            if(!isIn(nextR, nextC)) continue;  //���� �ȿ� ������ continue
            dfs(nextR, nextC, cnt+1);
        }
        visited[map[r][c]-65] = false;  //�ٸ� ��� �� ���� �����ϱ� false (true�� ��Ϳ��� ���ư� ��!)
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

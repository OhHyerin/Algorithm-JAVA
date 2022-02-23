package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_10026_���ϻ��� {
    //���� ��� 5
    //���� ���� ���ϴ� ����
    //DFS

    //�Ϲ����� ���� ������ ���� ���ϰ�
    //visited�迭�� �ʱ�ȭ, drawing�迭���� ���� G�� R�� ����(�Ȱ��̺��ٰ� �����ϱ�)
    //���ϻ��� ����� ���� ���� ���ϱ�

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
        //�Ϲ��� ���� ���� ���ϱ�
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(!visited[i][j]){
                    //���ο� ������ ��
                    dfs(i, j);
                    countX++;
                }
            }
        }
        // G�� R�� �����ϱ�
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(drawing[i][j]=='G')
                    drawing[i][j]='R';
            }
        }
        //���ϻ����� ��� ���� ���� ���ϱ�
        //���ϱ� �� visited�迭 �ʱ�ȭ
        visited = new boolean[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(!visited[i][j]){
                    //���ο� ������ ��
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

package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_1520_�������� {
    //���� ���4
    //�ִܰ�ΰ� �ƴ϶� �� �� �ִ� ����� ���� ���ϹǷ� dfs��� �����ߴ�.

    //���� dfs�� ���纸�� ������ ���� ���� �� ���� ���Ǹ� �־���µ� �ð��ʰ�
    
    //dfs+dp  //�׷���Ž������ dp�� �Բ� ���̴� ���� ���� ����


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
            return 1; //������ ���������� count 1 ������
        }

        if(dp[r][c]!=Integer.MIN_VALUE){
            //���� ��ġ�� dp(count)�� �ʱⰪ�� �ƴϸ� return����(�����ߴٴ� ���̹Ƿ�)
            return dp[r][c];
        }

//        int tmp = map[r][c];
        dp[r][c] = 0; //ó�� ������ġ minvalue -> 0���� �湮ó��
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

//            if(isIn(nr, nc) && map[nr][nc]<tmp){
//                dfs(nr, nc);
//            }

            //���� �� ���� ���� �ȿ� �ְ�, ������ġ���� ���� ������ dp�迭�� count����
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

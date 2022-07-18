package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G2_2169_�κ������ϱ� {
    //dp

    /*
    ���� ����
    - ���� Ž�� �Ұ��� (��, ��, �� ���⸸ ����)
    - �� �� Ž���� �� ��Ž�� �Ұ���
    - ������ �������� ��� ��ġ ���� �ִ�
    (�ִ� �Ÿ���� �� ����!)
     */
    
    /* 
    ��,�� / ��,�� ������� ��ȭ��
     */

    static int R, C;
    static int[][] map;
    static int[] dr = {1, 0, 0};
    static int[] dc = {0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R+1][C+1];

        for(int i=1;i<=R;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=C;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //-------------------�Է¿Ϸ�---------------------

        bfs();

    }

    private static void bfs(){
        Queue<Pos> queue = new LinkedList<>();
        int[][][] dp = new int[R+1][C+1][];
        /*
        dp[][][0] : ������ �Ʒ���
        dp[][][1] : ���ʿ��� ����������
        dp[][][2] : �����ʿ��� ��������
         */

        queue.add(new Pos(1, 1, map[1][1]));
        for(int i=1;i<=C;i++){
            dp[1][i][0] = map[1][i];
            if(i==1) continue;
            dp[1][i][1] = map[1][i-1]+map[1][i];
        }
        dp[1][1][1] = map[1][1];
        dp[1][1][2] = map[1][1];

        while(!queue.isEmpty()){
            Pos cur = queue.poll();

            for(int d=0;d<3;d++){
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];

                if(!isIn(nr, nc)) continue;
                if(d==0 && dp[nr][nc][0]!=0){
                    dp[nr][nc][0] = dp[cur.r][cur.c][0] + map[nr][nc];
                }

            }

        }

    }

    private static boolean isIn(int r, int c){
        return r>0 && c>0 && r<=R && c<=C;
    }

    private static class Pos{
        int r;
        int c;
        int value;

        public Pos(int r, int c, int value) {
            this.r = r;
            this.c = c;
            this.value = value;
        }
    }
}

package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_1600_���̵ǰ��¿����� {
    //���� ���4
    //�ִܰŸ��ϱ� bfs

    /*
    visited�迭�� 3������ ����ϴ� ���� (2���̸� Ʋ�Ƚ��ϴ� ����)
    1
    5 5
    0 0 0 0 0
    0 0 0 0 0
    0 0 0 1 1
    0 0 0 1 0
    �ּ��� �������� �������� �������� �������� ���ϴ� ��찡 �ֱ� ������
    */

    static int R, C;
    static int K;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dr = {-1, 1, 0, 0, 1, 2, -1, -2, 2, 1, -2, -1};
    static int[] dc = {0, 0, -1, 1, 2, 1, 2, 1, -1, -2, -1, -2};
    static int minDist = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        visited = new boolean[R][C][K+1];

        for(int i=0;i<R;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<C;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        System.out.println(minDist);
    }

    private static void bfs(){
        Queue<Pos> queue = new LinkedList<>();

        queue.add(new Pos(0, 0, 0, 0));
        visited[0][0][0] = true;

        while(!queue.isEmpty()){
            Pos cur = queue.poll();

            if(cur.r==(R-1) && cur.c==(C-1)){
                minDist = cur.dist;
                break;
            }

            for(int d=0;d<(cur.jumpCount<K ? 12:4);d++){
                //������� ������ ������ K���� ũ�� d==3�϶� ������ ����
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];
                int nj = (d>=4? cur.jumpCount+1 : cur.jumpCount); //���� Ƚ��

                if(!isIn(nr,nc)) continue;  //�������� �ְ�
                if(visited[nr][nc][nj]) continue; //�湮�� �� ����
                if(map[nr][nc]==1) continue;  //���� �ƴϸ�


                visited[nr][nc][nj] = true;
                queue.add(new Pos(nr, nc, nj, cur.dist+1));

            }

        }

    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }

    private static class Pos{
        int r;
        int c;
        int jumpCount;
        int dist;

        public Pos(int r, int c, int jumpCount, int dist) {
            this.r = r;
            this.c = c;
            this.jumpCount = jumpCount;
            this.dist = dist;
        }
    }
}

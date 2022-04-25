package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_16234_�α��̵� {
    //����, �ùķ��̼�

    /*
    1. ������ �� ���� �α����̰� L�̻�, R���϶�� ���漱�� �Ϸ絿�� ��
    2. ���漱 ��� ��������, �α� �̵� ����
    3. ���漱�� �����ִ� �� ���� �Ϸ絿�� �����̶�� �Ѵ�.
    4. ������ �̷�� �ִ� �� ĭ�� �α����� (������ �α���)/(������ �̷�� �ִ� ĭ�� ����)�� �ȴ�.(�Ҽ��� ����)
    5. ���� ��ü�ϰ�, ��� ���漱 ����
     */

    static int N; //N*N
    static int L, R;  //L�̻� R����
    static int[][] map;
    static int[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


    }

    private static void bfs(int r, int c, int depth){
        Queue<Pos> queue = new LinkedList<>();

        queue.add(new Pos(r, c));
        visited[r][c] = depth;

        while(!queue.isEmpty()){
            Pos cur = queue.poll();

            for(int d=0;d<4;d++){
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];

                if(!isIn(nr, nc)) continue;
                int diff = Math.abs(map[nr][nc]-map[cur.r][cur.c]);
                if(diff<L && diff>R) continue;

                queue.add(new Pos(nr, nc));

            }
        }

    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<N && c<N;
    }

    private static class Pos{
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

}

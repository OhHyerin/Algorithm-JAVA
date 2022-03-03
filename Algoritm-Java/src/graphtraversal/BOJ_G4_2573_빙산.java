package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_2573_���� {
    //���� ���4

    //���ϸ� ���̴°� bfs
    //���� ���� �� �дܵ� ������ ��� dfs�� Ž��

    static int R, C;
    static int[][] map;
    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;

        while(true){
            //���ϰ� �� ����� �� return
            if(countIceberg()==0){
                day = 0;
                break;
            }
            //���ϰ� �� ��� �̻����� �������� �� return
            if(countIceberg()>=2){
                break;
            }
            bfs();
            day++;
        }

        System.out.println(day);
    }


    static void bfs() {
        Queue<Iceberg> queue = new LinkedList<>();

        //��� ���� queue�� �߰� (map�� �ݿ� �� �͵� ���⼭ �ʱ�ȭ��)
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j]==-1){
                    map[i][j]=0;
                }
                if (map[i][j] != 0) {
                    queue.add(new Iceberg(i, j, map[i][j]));
                }
            }
        }

        while (!queue.isEmpty()) {

            Iceberg cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if(isIn(nr,nc)){
                    //������ġ ���� ����� ���� �ȿ� �ְ�
                    if(map[nr][nc]==0){
                        //���̸�
                        map[cur.r][cur.c]--;  //�� �ִ� ��ŭ ������ġ ���� ����
                        if(map[cur.r][cur.c]<=0){
                            //0���� ������ ���� ���ֱ� (0�̸� �ٷ� ����Ž������ ���� �ν��ϱ⶧���� -1�� ����)
                            map[cur.r][cur.c]=-1;
                        }
                   }
                }
            }

        }
    }

    static void dfs(int r, int c){
        //dfs�� �湮�ߴ� �� trueó���� ���� (���� �� �ٽ� �Ȱ�����)

        visited[r][c] = true; //�ش����� �湮ó��

        for(int d=0;d<4;d++){
            int nr = r+dr[d];
            int nc = c+dc[d];

            if(isIn(nr,nc)){
                if(!visited[nr][nc] && map[nr][nc]!=0 && map[nr][nc]!=-1){
                    //�湮�� �� ���� ������ �� (���� ���̶� ���ϰ� ���� �� ���� ����)
                    dfs(nr, nc);
                }
            }
        }

    }

    static int countIceberg(){
        visited = new boolean[R][C];
        int cnt = 0;
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j]!=0 && map[i][j]!=-1 && !visited[i][j]){
                    dfs(i,j);
                    cnt++;
                }
            }
        }

        return cnt;
    }

    static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }


    static class Iceberg {
        int r;
        int c;
        int height;

        public Iceberg(int r, int c, int height) {
            this.r = r;
            this.c = c;
            this.height = height;
        }
    }
}

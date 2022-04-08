package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1953_Ż�ֹ��˰� {
    //���� SW �����׽�Ʈ

    static int R, C; // R*C
    static int mR, mC;  //��Ȧ �Ѳ� ��ġ (mR, mC);
    static int L; //Ż�� �� �ҿ�� �ð�
    static int[][] map;
    //��(0) - ��(3) , ��(1) - ��(2)
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};
    static int[][] type = {{}, {0, 1, 2, 3}, {0, 3}, {1, 2}, {0, 2}, {2, 3}, {1, 3}, {0, 1}};
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            st = new StringTokenizer(br.readLine());

            R = Integer.parseInt(st.nextToken());  //���� ����
            C = Integer.parseInt(st.nextToken());  //���� ����
            mR = Integer.parseInt(st.nextToken()); //��Ȧ ����
            mC = Integer.parseInt(st.nextToken()); //��Ȧ ����
            L = Integer.parseInt(st.nextToken());  //�ð�

            map = new int[R][C];

            for (int i = 0; i < R; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < C; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            answer = 1;
            bfs();
            sb.append(answer).append("\n");
        }//t
        System.out.println(sb);

    }

    private static void bfs() {
        Queue<Pos> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];

        queue.offer(new Pos(mR, mC));
        visited[mR][mC] = true;

        while (L - 1 > 0) { //�����ϸ鼭 1�ð� ���
            int size = queue.size();
            while (size-- > 0) {
                Pos head = queue.poll();

                //�Ϲ����� ����� �ƴ϶� �������� �����ִ� ���⸸ �Ĵٺ���
                for (int d : head.ptype) {
                    int nr = head.r + dr[d];
                    int nc = head.r + dc[d];
                    int nd = 3-d;

                    if(isIn(nr, nc) && canConnect(nr, nc, nd) && !visited[nr][nc]){
                        visited[nr][nc] = true;
                        queue.offer(new Pos(nr, nc));
                        answer++;
                    }
                }
            }
            L--;
        }
    }

    private static boolean canConnect(int r, int c, int d){
        int [] t = type[map[r][c]];
        for(int i=0;i<t.length;i++){
            if(t[i]==d){
                return true;
            }
        }
        return false;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }

    private static class Pos {
        int r;
        int c;
        int[] ptype;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
//            ��ǥ�� �ش��ϴ� ������ ��ȣ�� �����ͼ� �ش� Ÿ���� �迭 �Ҵ�
            ptype = type[map[r][c]];
        }
    }
}

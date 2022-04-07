package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G3_11967_���ѱ� {
    //������ - ���ױ�Ż

    static int N, M;
    static boolean[][] map; //����ġ ���� (true : ������, false : ������)
    static ArrayList<Pos>[][] switches; //�濡 ����ġ�� 2���̻� ���� �� �����Ƿ� ��������Ʈ
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] visited;  //bfs visited
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N+1][N+1];
        switches = new ArrayList[N+1][N+1];
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                switches[i][j] = new ArrayList<>();
            }
        }


        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            switches[r][c].add(new Pos(a, b));  //(r,c)��ġ�� ����Ǿ� �ִ� ����ġ�� list�� �߰�
        }

        map[1][1] = true;  //(1,1) ����ġ�� ��������
        answer = 1;

        bfs();
        System.out.println(answer);
    }

    static void bfs(){
        Queue<Pos> queue = new LinkedList<>();
        visited = new boolean[N+1][N+1];

        queue.add(new Pos(1, 1));
        visited[1][1] = true;

        boolean isTurnOn = false; //�ش� �Ͽ��� ����ġ �״��� ���״��� Ȯ��(return�ֱ�����)

        while(!queue.isEmpty()){
            Pos cur  = queue.poll();
            int cr = cur.r;
            int cc = cur.c;

            //���ѱ�
            for(int i=0;i<switches[cr][cc].size();i++) { //(cr, cc)���� ų �� �ִ� ����ġ Ž��
                Pos turnOn = switches[cr][cc].get(i);
                if(!map[turnOn.r][turnOn.c]) {  //(cr, cc)�� ����ġ�� �������� ������
                    map[turnOn.r][turnOn.c] = true;  //�ش� ��ġ ����ġ Ű��
                    isTurnOn = true; //�ش� �Ͽ��� ����ġ ����
                    answer++;  //����ġ Ų ��ŭ answer++ (if���� �����ν� �ߺ����� �������� ����)
                }
            }
            for(int d=0;d<4;d++){ //�̵��� �� �ִ��� ���Ž��
                int nr = cr+dr[d];
                int nc = cc+dc[d];

                if(!isIn(nr, nc)) continue; //���� ����� �н�
                if(!map[nr][nc]) continue; //�� ���������� �н�
                if(visited[nr][nc]) continue; //�湮�� �� ������ �н�

                queue.add(new Pos(nr, nc));  //���� ��ġ�� �̵�
                visited[nr][nc] = true;
            }
        }
        if(isTurnOn){  //�ش� �Ͽ��� ����ġ�� ������ (1,1)���� �ٽ� bfsŽ��
            bfs();
        }
        return; //�ش� ���� �����ų� ����ġ �������� return

    }

    static boolean isIn(int r, int c){
        return r>0 && c>0 && r<=N && c<=N;
    }

    static class Pos{
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }
}

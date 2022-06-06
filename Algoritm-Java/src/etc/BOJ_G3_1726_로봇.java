package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G3_1726_�κ� {
    //�ù�
    //�̵��ؾ��ϴ� �����̶� �������� �� ������ ��ȣ�� �޶� ���� �����ؾ���

    /*
    1 : ��
    2 : ��
    3 : ��
    4 : ��
     */

    static int R, C;
    static int[][] map;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static Pos start;
    static Pos end;
    static int orderCnt;

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

        st = new StringTokenizer(br.readLine());
        start = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())-1, 0);

        st = new StringTokenizer(br.readLine());
        end = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())-1, 0);

        bfs();

//        System.out.println(start);
//        System.out.println(end);

        System.out.println(orderCnt);
    }

    static void bfs(){
        Queue<Pos> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[R+1][C+1][5];  //�������

        queue.add(start);
        visited[start.r][start.c][start.d] = true;

        while(!queue.isEmpty()){
            Pos cur = queue.poll();

            if(cur.r==end.r && cur.c==end.c && cur.d==end.d){
                //���� ��ġ�� end������ ��ġ�� ���� ��� ��ġ�ϸ� return
                orderCnt = cur.cnt;
                return;
            }

            for(int move=0;move<=3;move++){
                //������°� �������
                int nr = cur.r+(dr[cur.d]*move);
                int nc = cur.c+(dc[cur.d]*move);

                if(!isIn(nr, nc)) break; //���� ����� break (���� �ٲ����)
                if(map[nr][nc]==1) break; //�� �� ���� ���̸� break (���� �ٲ����)
                if(visited[nr][nc][cur.d]) continue;  //�̹� �湮�� ���·� �湮�� ���̸� continue

                visited[nr][nc][cur.d] = true;
                queue.add(new Pos(nr, nc, cur.d, cur.cnt+1));
            }

            //���⸸ �ٲ�ߵǸ�
            for(int i=0;i<2;i++){
                int nd = 0;
                if(cur.d==0){
                    nd = i==0? 3:2;
                }else if(cur.d==1){
                    nd = i==0? 2:3;
                }else if(cur.d==2){
                    nd = i==0? 0:1;
                }else if(cur.d==3){
                    nd = i==0? 1:0;
                }

                if(visited[cur.r][cur.c][nd]) continue; //nd�������� �ٲ��� ������ continue
//                if(cur.r==end.r && cur.c==end.c && cur.d==end.d){
//                    return;
//                }
                queue.add(new Pos(cur.r, cur.c, nd, cur.cnt+1));
                visited[cur.r][cur.c][nd] = true;
            }

        }
    }

    static boolean isIn(int r, int c){
        return r>0 && c>0 && r<=R && c<=C;
    }

    static class Pos{
        int r;
        int c;
        int d;
        int cnt;

        public Pos(int r, int c, int d, int cnt) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.cnt = cnt;

        }

        @Override
        public String toString() {
            return "Pos{" +
                    "r=" + r +
                    ", c=" + c +
                    ", d=" + d +
                    ", cnt=" + cnt +
                    '}';
        }
    }
}

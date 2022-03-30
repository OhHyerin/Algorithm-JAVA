package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_2636_ġ�� {
    //���� ���4
    //BFS
    
    //ġ�� �ȿ��ִ� ����� ġ� ��µ��� ������ �����Ƿ�
    //ġ� �ƴ�, ������ ��ġ�� BFS�� Ž���� ��

    static int R, C;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int countCheese;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for(int i=0;i<R;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<C;j++){
                int temp = Integer.parseInt(st.nextToken());
                if(temp==1){
                    map[i][j] = 0;  //ġ�� �ִ� ��
                    countCheese++;
                }else{
                    map[i][j] = 1;  //���� �ִ� ��
                }
            }
        }

        //---------------------�Է¿Ϸ�------------------------------

        int answer2 = 0;
        int answer1 = 1;
        count = 1;

        while(countCheese>0){
            answer2 = bfs();
            answer1 = count;
            count++;
        }


        sb.append(answer1).append("\n").append(answer2);
        System.out.println(sb);
    }

    static int bfs(){
        Queue<Pos> queue = new LinkedList<>();
        visited = new boolean[R][C];

        queue.add(new Pos(0, 0));
        visited[0][0] = true;

        int meltingCount = 0;  //�ش� �Ͽ� �������� ġ���� ����

        while(!queue.isEmpty()){
            Pos cur = queue.poll();

            for(int d=0;d<4;d++){
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];

                if(isIn(nr,nc) && !visited[nr][nc]){
                    if(map[nr][nc]==0){
                        map[nr][nc] = count;  //�ش� ��ġ�� �� �ð��� ������ ����
                        meltingCount++;  //�ش� �Ͽ� �������� ġ���� ���� +1
                        visited[nr][nc] = true;  //�湮ó��
                    }else {
                        visited[nr][nc] = true;  //�湮ó��
                        queue.add(new Pos(nr, nc));  //��ġ�� �̵�
                    }
                }
            }
        }

        countCheese -= meltingCount;  //�ش� �Ͽ� �������� ġ���� ������ŭ ���� ġ�� ����
        return meltingCount;
    }

    static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }

    static class Pos{
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}

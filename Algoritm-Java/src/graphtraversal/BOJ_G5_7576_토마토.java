package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_7576_�丶�� {
    //���� ���5
    //�׷���Ž�� - BFS

    //��� �丶�䰡 ���������� �ּҳ�¥�� ����Ѵ�.
    //���� �丶�䰡 ���� �ڸ��� ��� queue�� �Է��ϱ⶧���� depth�� �˱� �����
    //���� visitDay 2�����迭�� ����Ͽ� ������ ĭ���� 1�� �����־���.

    static int N, M;  //N*M
    static int[][] box;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int dayCount = 0;
    static int rawCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        M = Integer.parseInt(st.nextToken()); //����
        N = Integer.parseInt(st.nextToken()); //����
        box = new int[N][M];

        for(int r=0;r<N;r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0;c<M;c++){
                box[r][c] = Integer.parseInt(st.nextToken());
                if(box[r][c]==0) rawCount++;
            }
        }

        bfs();

//        for(int r=0;r<N;r++){
//            for(int c=0;c<M;c++){
//                System.out.print(box[r][c]+" ");
//            }
//            System.out.println();
//        }


        boolean flag = false;
        outer:for(int r=0;r<N;r++){
            for(int c=0;c<M;c++){
                if(box[r][c]==0){
                    flag = true;
//                    System.out.println("r :"+r);
                    break outer;
                }
            }
        }

        if(flag) sb.append(-1);
        else sb.append(dayCount);

        System.out.println(sb);

    }

    private static void bfs(){
        Queue<Pos> queue = new LinkedList<>();
//        int day = 0;

        //���� �丶�� ��ġ queue�� �߰�
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(box[i][j]==1){
                    queue.offer(new Pos(i, j, 1));
                }
            }
        }

        while(!queue.isEmpty()){
            if(rawCount==0){
                return;
            }
            Pos cur = queue.poll();
            for(int d=0;d<4;d++){
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];

                if(isIn(nr, nc)){
                    if(box[nr][nc]==0){
                        //���� ���� �丶�� ������ ���� �丶�� ��ġ�� �߰�
                        box[nr][nc]=1;
                        queue.offer(new Pos(nr, nc, cur.day+1));
                        dayCount = Math.max(dayCount, cur.day);
                        rawCount--;
                    }
                }
            }
        }



    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<N && c<M;
    }

    private static class Pos{
        int r, c;
        int day;

        public Pos(int r, int c, int day) {
            this.r = r;
            this.c = c;
            this.day = day;
        }
    }
}

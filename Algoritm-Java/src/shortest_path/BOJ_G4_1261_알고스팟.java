package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_1261_�˰��� {
    //���� ���4
    //���͵� ���빮��

    static int R, C;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] count;  //�� �μ� ���� �ּҰ� ����

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        count = new int[R][C];

        for(int i=0;i<R;i++){
            String str = br.readLine();
            for(int j=0;j<C;j++){
                map[i][j] = str.charAt(j)-'0';
            }
        }

//        for(int i=0;i<R;i++){
//            for(int j=0;j<C;j++){
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }
        //----------------�Է¿Ϸ�-----------------


        bfs();
        System.out.println(count[R-1][C-1]);

    }

    private static void bfs(){
        Queue<Pos> queue = new LinkedList<>();
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                count[i][j] = Integer.MAX_VALUE;  //count�迭 �ִ����� �ʱ�ȭ (dp����)
            }
        }
        queue.add(new Pos(0, 0, 0));
        count[0][0] = 0;

        while(!queue.isEmpty()){
            Pos cur = queue.poll();

            if(count[cur.r][cur.c]<cur.breakCount) continue;  //���� breakCount�� dp�迭�� �ִ� ������ ũ�� �ȵ��ƺ�����

            for(int d=0;d<4;d++){
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];

                if(isIn(nr,nc)){
                    if(map[nr][nc]==0 && count[nr][nc]>cur.breakCount){  //���� �� ���� 0�̰� dp����� �迭���� ������
                        count[nr][nc] = cur.breakCount;  //dp�迭 ����
                        queue.add(new Pos(nr, nc, cur.breakCount));  //queue�� ���� ��ġ ����
                    } else if(map[nr][nc]==1 && count[nr][nc]>cur.breakCount+1){  //���� �� ���� 1�̰� ���� breakCount+1���� dp����� �迭���� ������
                        count[nr][nc] = cur.breakCount+1;  //dp�迭 ����
                        queue.add(new Pos(nr, nc, cur.breakCount+1));  //queue�� ���� ��ġ ����
                    }
                }
            }
        }
    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }

    private static class Pos {
        int r;
        int c;
        int breakCount;

        public Pos(int r, int c, int breakCount) {
            this.r = r;
            this.c = c;
            this.breakCount = breakCount;
        }

    }
}

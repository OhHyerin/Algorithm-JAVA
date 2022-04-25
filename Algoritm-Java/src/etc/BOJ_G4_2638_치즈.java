package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_2638_ġ�� {
    //�׷��� Ž��
    //���⸦ �������� bfs
    //G5ġ��� �ٸ� ���� 4�� �� ��� 2�� �̻� �ǳ��µ� ����� �����Ѱ͸� ������

    static int R, C;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int countCheese;
    static int day;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for(int i=0;i<R;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<C;j++){
                int temp = Integer.parseInt(st.nextToken());
                map[i][j] = temp;
                if(temp==1) countCheese++;  //�ʱ� ġ�� ���� ����
            }
        }

        while(countCheese>0){ //ġ�� �������� �ݺ�
            day++;
            bfs();
        }

        System.out.println(day);

    }

    private static void bfs(){
        Queue<Pos> queue = new LinkedList<>();
        visited = new boolean[R][C];

        queue.add(new Pos(0, 0));
        visited[0][0] = true;


        while(!queue.isEmpty()){
            Pos cur = queue.poll();

            for(int d=0;d<4;d++){
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];

                if(!isIn(nr, nc)) continue;
                if(visited[nr][nc]) continue;
                if(map[nr][nc]!=0){  //���� ��ġ�� ġ����
                    map[nr][nc]++;  //ġ�� ��ġ �� -1
                    //���� �����ϸ� �߰��� ġ� 0�̵ż� ���� �Ͽ� ������
//                    if(map[nr][nc]==3){  //���� 3�̶��
//                        countCheese--;  //ġ�� ����
////                        if(countCheese<=0){
////                            return;
////                        }
//                    }
                }else{
                    visited[nr][nc] = true;
                    queue.add(new Pos(nr, nc)); //���� ��ġ�� ������ ��ġ�̵�
                }
            }
        }

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j]==1 || map[i][j]==2){  //1�̰ų� 2���� �������� ���� �͵���
                    map[i][j] = 1;  //���� �� ���� ���� �ٽ� ġ��� 1�� �ʱ�ȭ
                }
                if(map[i][j]>=3){  //3�̻��� ������
                    countCheese--;  //ġ�� �����
                    map[i][j] = 0;  //����� �ʱ�ȭ
                }
            }
        }

//        for(int i=0;i<R;i++){
//            for(int j=0;j<C;j++){
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();
    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
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

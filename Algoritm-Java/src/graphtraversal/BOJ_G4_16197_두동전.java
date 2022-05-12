package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_16197_�ε��� {
    //�׷���Ž�� (�� �� ���� bfs)
    
    //comparable�� cnt�� �ּڰ����� �������� ������ count�� �ּڰ����� ������ ����

    /*
    - ������ �̵��Ϸ��� ĭ�� ���̸�, ������ �̵����� �ʴ´�.
    - ������ �̵��Ϸ��� ���⿡ ĭ�� ������ ������ ���� �ٱ����� ��������.
    - �� ���� ��쿡�� �̵��Ϸ��� �������� �� ĭ �̵��Ѵ�.
        �̵��Ϸ��� ĭ�� ������ �ִ� ��쿡�� �� ĭ �̵��Ѵ�.
     */

    static int R, C;
    static char[][] map;  // 0:��ĭ, 1:��, 2:����
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Coin[] coins;
//    static Coin coin1;
//    static Coin coin2;
    static int count;
    static boolean isSuccess;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        coins = new Coin[2];  //���� 2��

        int index = 0;

        for(int i=0;i<R;i++){
            String str = br.readLine();
            for(int j=0;j<C;j++){
                map[i][j] = str.charAt(j);
                if(map[i][j]=='o'){
                    coins[index] = new Coin(i, j);  //�� ���� ����
                    index++;
                }
            }
        }

//        coin1 = coins[0];
//        coin2 = coins[1];

        bfs();

        if(isSuccess){
            System.out.println(count);
        }else{
            System.out.println(-1);
        }

    }

    private static void bfs(){
        Queue<Pos> queue = new LinkedList<>();

//        queue.add(new Pos(coin1.r, coin1.c, coin2.r, coin2.c, 1));
        queue.add(new Pos(coins[0].r, coins[0].c, coins[1].r, coins[1].c, 1));

        while(!queue.isEmpty()){

//            count++;

            Pos cur = queue.poll();
            int r1 = cur.r1;
            int c1 = cur.c1;
            int r2 = cur.r2;
            int c2 = cur.c2;

            if(cur.cnt>10){
                isSuccess = false;
                return;
            }

            for(int d=0;d<4;d++){
                int nr1 = r1+dr[d];
                int nc1 = c1+dc[d];
                int nr2 = r2+dr[d];
                int nc2 = c2+dc[d];

                // (isIn(nr1, nc1) ^ isIn(nr2, nc2)) -> �ε� �� �� ����
                
                if(isIn(nr1, nc1) && !isIn(nr2, nc2)){  //���� 2�� �������� ��
//                    System.out.println("����2�� ������");
                    isSuccess = true;
                    count = cur.cnt;
                    return;
                }
                else if(!isIn(nr1, nc1) && isIn(nr2, nc2)){  //���� 1�� �������� ��
//                    System.out.println("���� 1�� ������");
                    isSuccess = true;
                    count = cur.cnt;
                    return;
                }

                if(!isIn(nr1, nc1) && !isIn(nr2, nc2)) continue;  //�ΰ� �� ���������� continue;
                if(map[nr1][nc1]=='#'){  // ����1�� ���� ��ġ�� ���̸�
//                    System.out.println("����1��ġ ���� ��");
                    nr1 = r1;  //����1�� ��ġ�� �״��
                    nc1 = c1;
                }
                if(map[nr2][nc2]=='#'){
//                    System.out.println("���� 2��ġ ���� ��");
                    nr2 = r2;
                    nc2 = c2;
                }

                queue.add(new Pos(nr1, nc1, nr2, nc2, cur.cnt+1));
//                System.out.println("-----------------------");
//                System.out.println("����1 ������ġ : "+nr1+" "+nc1);
//                System.out.println("����2 ������ġ : "+nr2+" "+nc2);

            }

        }

    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }

    private static class Coin{
        int r;
        int c;

        public Coin(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static class Pos implements Comparable<Pos>{
        int r1;
        int c1;
        int r2;
        int c2;
        int cnt;

        public Pos(int r1, int c1, int r2, int c2, int cnt) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Pos o) {
            return cnt-o.cnt;
        }
    }
}


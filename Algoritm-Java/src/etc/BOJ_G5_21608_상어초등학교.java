package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_21608_����ʵ��б� {
    //�ùķ��̼�

    /*
    �����ϴ�? : |r1-r2| + |c1-c2| = 1
    1. ����ִ� ĭ �߿��� �����ϴ� �л��� ������ ĭ�� ���� ���� ĭ���� �ڸ��� ���Ѵ�.
    2. 1�� �����ϴ� ĭ�� ���� ���̸�, ������ ĭ �߿��� ����ִ� ĭ�� ���� ���� ĭ���� �ڸ��� ���Ѵ�.
    3. 2�� �����ϴ� ĭ�� ���� ���� ��쿡�� ���� ��ȣ�� ���� ���� ĭ����,
        �׷��� ĭ�� ���� ���̸� ���� ��ȣ�� ���� ���� ĭ���� �ڸ��� ���Ѵ�.
     */

    static int N;  //N*N�� �ڸ�
    static int M;  //�л� ��
    static Seat[][] seats;
    static int[][] favorits;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = N*N;

        seats = new Seat[N+1][N+1];
        favorits = new int[N*N+1][5];

        for(int i=1;i<=M;i++){
            st = new StringTokenizer(br.readLine());
            int me = Integer.parseInt(st.nextToken());
            int f1 = Integer.parseInt(st.nextToken());
            int f2 = Integer.parseInt(st.nextToken());
            int f3 = Integer.parseInt(st.nextToken());
            int f4 = Integer.parseInt(st.nextToken());

            favorits[i][0] = me;
            favorits[i][1] = f1;
            favorits[i][2] = f2;
            favorits[i][3] = f3;
            favorits[i][4] = f4;
        }

        def();  //seats �迭 �ʱ�ȭ

        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                System.out.print(seats[i][j].empty+" ");
            }
            System.out.println();
        }

        //ù ��° ģ�� �ڸ� ����
        int first = 0;
        int fr = 0;
        int fc = 0;
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(first<seats[i][j].empty){
                    first = seats[i][j].empty;
                    fr = i;
                    fc = j;
                }
            }
        }

        seats[fr][fc].who = favorits[1][0];
        seats[fr][fc].empty = seats[fr][fc].empty-1;
        for(int d=0;d<4;d++){
            int nr = fr+dr[d];
            int nc = fc+dc[d];
            if(!isIn(nr, nc)) continue;
            seats[nr][nc].empty = seats[nr][nc].empty-1;
        }

       /* for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                System.out.print(seats[i][j].who+" ");
            }
            System.out.println();
        }

        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                System.out.print(seats[i][j].empty+" ");
            }
            System.out.println();
        }*/

        for(int i=2;i<=M;i++){
            selectSeat(favorits[i][0]);
        }




    }

    private static void selectSeat(int who){
        int sr = 0;
        int sc = 0;
        int maxEmpty = 0;
        int maxFavor = 0;

        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){

            }
        }
    }

    private static int favoritCount(int r, int c){
        int count = 0;

        for(int d=0;d<4;d++){
            int nr = r+dr[d];
            int nc = c+dc[d];

            if(!isIn(nr, nc)) continue;
            for(int f=1;f<=4;f++){
                if(seats[nr][nc].who==favorits[seats[r][c].who][f]){
                    count++;
                }
            }
        }
        return count;
    }

    private static int emptyCount(int r, int c){
        int count = 0;

        for(int d=0;d<4;d++){
            int nr = r+dr[d];
            int nc = c+dc[d];

            if(!isIn(nr, nc)) continue;
            if(seats[nr][nc]==null || seats[nr][nc].who==0){
                count++;
            }
        }

        return count;
    }



    private static void def(){

        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                seats[i][j] = new Seat(0, emptyCount(i, j));
            }
        }
    }

    private static boolean isIn(int r, int c){
        return r>0 && c>0 && r<=N && c<=N;
    }

    private static class Seat{
        int who;
        int empty;

        public Seat(int who, int empty) {
            this.who = who;
            this.empty = empty;
        }

    }
}

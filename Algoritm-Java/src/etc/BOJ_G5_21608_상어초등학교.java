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
    static int[][] seats;
    static int[][] favorits;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = N*N;

        seats = new int[N+1][N+1];
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

    private static int favoritCount(){
        int count = 0;

        for(int r=1;r<=N;r++){
            for(int c=1;c<=N;c++){
                int like = 0;
                for(int d=0;d<4;d++){
                    int nr = r+dr[d];
                    int nc = c+dc[d];

                    if(!isIn(nr, nc)) continue;

                }
            }
        }

        return count;
    }

    private static int emptyCount(){
        int count = 0;

        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                count = 0;
                for(int d=0;d<4;d++){
                    int nr = i+dr[d];
                    int nc = j+dc[d];

                    if(!isIn(nr, nc)) continue;
                    if(seats[nr][nc]==0) count++;
                }

            }
        }

        return count;
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

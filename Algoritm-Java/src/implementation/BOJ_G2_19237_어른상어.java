package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_G2_19237_���� {
    //�ù�

    static int N;
    static int M;  //����� ����
    static int K;  //������ �����ִ� �ð�
    static int[][] smell;  //������ �Ѹ� ����� ��ȣ ����
    static int[][] restSmell; //������ ����������� ���� �ð� ����
    static int[][][] priority; //���� ���⿡�� �켱����(��� ��ȣ, ���� ����, �켱��������)
    static int[] dr = {0, -1, 1, 0, 0};
    static int[] dc = {0, 0, 0, -1, 1};
    static int sharkCount;
    static Shark[] sharks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        restSmell = new int[N+1][N+1];
        smell = new int[N+1][N+1];
        priority = new int[M+1][5][4];
        sharks = new Shark[M+1];

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                int info = Integer.parseInt(st.nextToken());

                if(info>0){
                    sharks[info] = new Shark(i, j, 0);
                    restSmell[i][j] = K;
                    smell[i][j] = info;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=M;i++){
            sharks[i].dir = Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<=M;i++){
            for(int j=1;j<=4;j++){
                st = new StringTokenizer(br.readLine());
                for(int k=0;k<4;k++){
                    priority[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        //----------------------�Է¿Ϸ�---------------------------

        int time = 0;

        outer : while(true){
            int count = 0;  //����ִ� ����� ��

            for(int m=1;m<=M;m++){
                if(sharks[m]!=null){
                    count++;
                }
            }

            if(count==1 && sharks[1] != null){ //�� 1���� ���Ұ�, �� �� 1���� ��
                break outer;
            }

            if(time>=1000){
                //�ð� �� �ƴµ� ���� �ȸ��� ��
                time = -1;
                break outer;
            }

            int[][] tmp = new int[N+1][N+1];

            for(int m=1;m<=M;m++){  //1������ �켱������ ���� �������
                if(sharks[m] != null){  //�� ��� �ȿ� ������
                    move(tmp, m);
                }
            }

            //�� ĭ�� ���� ���̱�
            discountSmell();

            //�̵� �� ��� ��ġ�� ������ ��ȿ�Ⱓ �ʱ�ȭ
            for(int i=1;i<=N;i++){
                for(int j=1;j<=N;j++){
                    if(tmp[i][j]>0){
                        restSmell[i][j] = K;  //�ٽ� K��ŭ ���� ��� �ʱ�ȭ
                        smell[i][j] = tmp[i][j];  //��� ��ȣ �ʱ�ȭ
                    }
                }
            }

            time++;


        }


        System.out.println(time);


    }

    private static void discountSmell(){
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(restSmell[i][j]>0){  //������ 0���� ũ�ٸ�
                    restSmell[i][j]--;  //�ϳ� �ٿ���
                }
                if(restSmell[i][j]==0){  //0�̸�
                    smell[i][j] = 0;  //smell���� ��� ��ȣ ������
                }
            }
        }
    }

    private static void move(int[][] tmp, int m){
        int nr = 0;
        int nc = 0;
        int d = 0;

        boolean flag = false;  //������ ���� ���� ������ true, ������ false

        for(int i=0;i<4;i++){
            d = priority[m][sharks[m].dir][i];
            nr = sharks[m].r + dr[d];
            nc = sharks[m].c + dc[d];

            if(!isIn(nr, nc)) continue;  //��� ����� continue
            if(smell[nr][nc]==0){  //������ ���� ���̸�
                flag = true;  //flag�� true�� ó���ϰ�
                break;  //Ż��
            }
        }

        if(!flag){//�� ������ ���� ���
            for(int i=0;i<4;i++){
                d = priority[m][sharks[m].dir][i];
                nr = sharks[m].r + dr[d];
                nc = sharks[m].c + dc[d];

                if(!isIn(nr, nc)) continue;
                if(smell[nr][nc]==m) break;  //�ڽ��� ������ �ִ� ĭ�� ������ �ش� ��ġ�� ������
           }
        }

        if(tmp[nr][nc]==0){  //�̵��ϴ� ��ġ�� �� ������ ���� �ٽ� ����
            tmp[nr][nc] = m;
            sharks[m].r = nr;
            sharks[m].c = nc;
            sharks[m].dir = d;
        }

        else{  //�̵��ϴ� ��ġ�� �� ������ ��� ������ �ѾƳ���
            sharks[m] = null;
        }

    }

    private static boolean isIn(int r, int c){
        return r>0 && c>0 && r<=N && c<=N;
    }

    private static class Shark{
        int r;
        int c;
        int dir;

        public Shark(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }
}

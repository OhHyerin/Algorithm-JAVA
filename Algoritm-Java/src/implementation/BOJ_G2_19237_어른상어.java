package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_G2_19237_어른상어 {
    //시뮬

    static int N;
    static int M;  //상어의 개수
    static int K;  //냄새가 남아있는 시간
    static int[][] smell;  //냄새를 뿌린 상어의 번호 저장
    static int[][] restSmell; //냄새가 없어지기까지 남은 시간 저장
    static int[][][] priority; //현재 방향에서 우선순위(상어 번호, 현재 방향, 우선순위방향)
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

        //----------------------입력완료---------------------------

        int time = 0;

        outer : while(true){
            int count = 0;  //살아있는 상어의 수

            for(int m=1;m<=M;m++){
                if(sharks[m]!=null){
                    count++;
                }
            }

            if(count==1 && sharks[1] != null){ //상어가 1마리 남았고, 그 상어가 1번일 때
                break outer;
            }

            if(time>=1000){
                //시간 다 됐는데 조건 안맞을 때
                time = -1;
                break outer;
            }

            int[][] tmp = new int[N+1][N+1];

            for(int m=1;m<=M;m++){  //1번부터 우선순위가 높은 순서대로
                if(sharks[m] != null){  //상어가 경계 안에 있으면
                    move(tmp, m);
                }
            }

            //각 칸의 냄새 줄이기
            discountSmell();

            //이동 후 상어 위치의 냄새와 유효기간 초기화
            for(int i=1;i<=N;i++){
                for(int j=1;j<=N;j++){
                    if(tmp[i][j]>0){
                        restSmell[i][j] = K;  //다시 K만큼 남은 향기 초기화
                        smell[i][j] = tmp[i][j];  //상어 번호 초기화
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
                if(restSmell[i][j]>0){  //냄새가 0보다 크다면
                    restSmell[i][j]--;  //하나 줄여줌
                }
                if(restSmell[i][j]==0){  //0이면
                    smell[i][j] = 0;  //smell에서 상어 번호 지워줌
                }
            }
        }
    }

    private static void move(int[][] tmp, int m){
        int nr = 0;
        int nc = 0;
        int d = 0;

        boolean flag = false;  //냄새가 없는 곳이 있으면 true, 없으면 false

        for(int i=0;i<4;i++){
            d = priority[m][sharks[m].dir][i];
            nr = sharks[m].r + dr[d];
            nc = sharks[m].c + dc[d];

            if(!isIn(nr, nc)) continue;  //경계 벗어나면 continue
            if(smell[nr][nc]==0){  //냄새가 없는 곳이면
                flag = true;  //flag를 true로 처리하고
                break;  //탈출
            }
        }

        if(!flag){//다 냄새가 있을 경우
            for(int i=0;i<4;i++){
                d = priority[m][sharks[m].dir][i];
                nr = sharks[m].r + dr[d];
                nc = sharks[m].c + dc[d];

                if(!isIn(nr, nc)) continue;
                if(smell[nr][nc]==m) break;  //자신의 냄새가 있는 칸이 있으면 해당 위치로 셋팅팅
           }
        }

        if(tmp[nr][nc]==0){  //이동하는 위치에 상어가 없으면 정보 다시 세팅
            tmp[nr][nc] = m;
            sharks[m].r = nr;
            sharks[m].c = nc;
            sharks[m].dir = d;
        }

        else{  //이동하는 위치에 상어가 있으면 경계 밖으로 쫓아내기
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

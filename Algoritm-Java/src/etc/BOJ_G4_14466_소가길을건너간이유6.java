package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G4_14466_소가길을건너간이유6 {
    //백준 골드4
    //스터디 - 코테광탈

    static int N; // N : 배열 크기
    static int K; // K : 소의 개수
    static int R; // R : 길의 개수
    static ArrayList<Load> loads;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Pos[] cows;
    static int[] isSelected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        loads = new ArrayList<>();
        map = new int[N+1][N+1];

        for(int i=0;i<R;i++){
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            loads.add(new Load(r1, c1, r2, c2));
        }

        cows = new Pos[K];
        for(int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[r][c] = 1;  //소가 있는 자리는 1
            cows[i] = new Pos(i+1, r, c);
        }

        isSelected = new int[2];
        combination(0, 0);

    }

    static void combination(int cnt, int start){
        if(cnt==2){
            System.out.println(Arrays.toString(isSelected));
            return;
        }

        for(int i=start;i< cows.length;i++){
            isSelected[cnt] = cows[i].num;
            combination(cnt+1, i+1);
        }

    }

    static void bfs(Pos a, Pos b){
        //Queue를 전역변수로 바꿔보기


        while(!queue.isEmpty()){
            Pos cur = queue.poll();
            for(int d=0;d<4;d++){
                Pos next = new Pos(cur.r+dr[d], cur.c+dc[d]);
                int nr = next.r;
                int nc = next.c;

                if(isIn(nr, nc)){
                    if(!isLoad(cur, next)){
                        queue.offer(new Pos(nr, nc));
                    }
                }

            }
        }

    }

    static boolean isIn(int r, int c){
        return r>0 && c>0 && r<=N && c<=N;
    }


    static boolean isLoad(Pos a, Pos b){
        for(int i=0;i<loads.size();i++){
            int r1 = loads.get(i).r1;
            int c1 = loads.get(i).c1;
            int r2 = loads.get(i).r2;
            int c2 = loads.get(i).c2;

            if(a.r==r1 && a.c==c1 && b.r==r2 && b.c==c2){
                return true;
            }
        }
        return false;
    }




    static class Load{
        int r1;
        int c1;
        int r2;
        int c2;

        public Load(int r1, int c1, int r2, int c2) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
        }

    }

    static class Pos{
        int num;
        int r;
        int c;

        public Pos(int num, int r, int c) {
            this.num = num;
            this.r = r;
            this.c = c;
        }
    }
}

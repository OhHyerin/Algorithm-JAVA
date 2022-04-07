package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G5_21610_마법사상어와비바라기 {
    //삼성 SW역량 테스트 기출문제

    /*
    1. 구름 이동하기
    1-1. 구름 있는 칸에 비가 1씩 내리고 (+1)
    2. 구름이 있던 곳에 대각선 방향에 물이 있는 개수만큼 ++
    2-2. 구름 없어짐
    3. 구름 있었던 칸 제외하고 나머지 칸 중 물의 양이 2 이상인 칸에 구름 생김
    3-2. 구름 생긴 곳에 -2
     */

    static int N, M;
    static int[][] map;
    static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static Queue<Pos> clouds = new LinkedList<>();
    static boolean[][] visited;
    static int answer;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        visited = new boolean[N+1][N+1];

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        clouds.add(new Pos(N, 1));
        clouds.add(new Pos(N, 2));
        clouds.add(new Pos(N-1, 1));
        clouds.add(new Pos(N-1, 2));

//        System.out.println(clouds);

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            move(d, s);  //이동하고 비내리기
            plusRainy();  //구름이었던 곳 대각선에 물 있으면 개수만큼 더하기
            makeCloud();  //새 구름 만들기
        }
        count();  //물이 0 이상인 곳 개수세기
        System.out.println(answer);

    }

    private static void move(int d, int s){
        int qSize = clouds.size();
        while(qSize-- >0){
            Pos cur = clouds.poll();
            int nr = cur.r + (dr[d] * s);
            int nc = cur.c + (dc[d] * s);

            while(!isValid(nr)) nr = changeLocation(nr);
            while(!isValid(nc)) nc = changeLocation(nc);

            clouds.add(new Pos(nr, nc));  //구름 이동
            map[nr][nc]++;  //비내리기
            visited[nr][nc] = true;  //구름이었던 자리
        }
    }

    private static void plusRainy(){
        int qSize = clouds.size();

        while(qSize-- >0){
            Pos cur = clouds.poll();
            int cr = cur.r;
            int cc = cur.c;

            if(isIn(cr-1, cc-1) && map[cr-1][cc-1] != 0){
                map[cr][cc]++;
            }
            if(isIn(cr-1, cc+1) && map[cr-1][cc+1] != 0){
                map[cr][cc]++;
            }
            if(isIn(cr+1, cc-1) && map[cr+1][cc-1] != 0){
                map[cr][cc]++;
            }
            if(isIn(cr+1, cc+1) && map[cr+1][cc+1] != 0){
                map[cr][cc]++;
            }
        }
    }

    private static void makeCloud(){
        clouds.clear();
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(map[i][j]>=2 && !visited[i][j]){
                    clouds.add(new Pos(i, j));
                    map[i][j] -= 2;
                }
            }
        }
        for(int i=1;i<=N;i++){
            Arrays.fill(visited[i], false);
        }
    }

    private static void count(){
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                answer += map[i][j];
            }
        }
    }



    private static int changeLocation(int x){
        if(x<1) return x+N;
        else return x-N;
    }

    private static boolean isValid(int x){
        return x>0 && x<=N;
    }

    private static boolean isIn(int r, int c){
        return r>0 && c>0 && r<=N && c<=N;
    }


    private static class Pos{
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }
}

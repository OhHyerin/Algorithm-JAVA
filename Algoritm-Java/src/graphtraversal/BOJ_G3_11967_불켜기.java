package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G3_11967_불켜기 {
    //문제집 - 코테광탈

    static int N, M;
    static boolean[][] map; //스위치 상태 (true : 켜있음, false : 꺼있음)
    static ArrayList<Pos>[][] switches; //방에 스위치가 2개이상 있을 수 있으므로 인접리스트
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] visited;  //bfs visited
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N+1][N+1];
        switches = new ArrayList[N+1][N+1];
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                switches[i][j] = new ArrayList<>();
            }
        }


        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            switches[r][c].add(new Pos(a, b));  //(r,c)위치에 연결되어 있는 스위치들 list로 추가
        }

        map[1][1] = true;  //(1,1) 스위치는 켜져있음
        answer = 1;

        bfs();
        System.out.println(answer);
    }

    static void bfs(){
        Queue<Pos> queue = new LinkedList<>();
        visited = new boolean[N+1][N+1];

        queue.add(new Pos(1, 1));
        visited[1][1] = true;

        boolean isTurnOn = false; //해당 턴에서 스위치 켰는지 안켰는지 확인(return주기위해)

        while(!queue.isEmpty()){
            Pos cur  = queue.poll();
            int cr = cur.r;
            int cc = cur.c;

            //불켜기
            for(int i=0;i<switches[cr][cc].size();i++) { //(cr, cc)에서 킬 수 있는 스위치 탐색
                Pos turnOn = switches[cr][cc].get(i);
                if(!map[turnOn.r][turnOn.c]) {  //(cr, cc)가 스위치가 켜져있지 않으면
                    map[turnOn.r][turnOn.c] = true;  //해당 위치 스위치 키고
                    isTurnOn = true; //해당 턴에서 스위치 켰음
                    answer++;  //스위치 킨 만큼 answer++ (if문을 줌으로써 중복으로 더해지지 않음)
                }
            }
            for(int d=0;d<4;d++){ //이동할 수 있는지 사방탐색
                int nr = cr+dr[d];
                int nc = cc+dc[d];

                if(!isIn(nr, nc)) continue; //범위 벗어나면 패스
                if(!map[nr][nc]) continue; //불 꺼져있으면 패스
                if(visited[nr][nc]) continue; //방문한 적 있으면 패스

                queue.add(new Pos(nr, nc));  //다음 위치로 이동
                visited[nr][nc] = true;
            }
        }
        if(isTurnOn){  //해당 턴에서 스위치를 켰으면 (1,1)부터 다시 bfs탐색
            bfs();
        }
        return; //해당 턴이 끝났거나 스위치 안켰으면 return

    }

    static boolean isIn(int r, int c){
        return r>0 && c>0 && r<=N && c<=N;
    }

    static class Pos{
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

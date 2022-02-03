package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_5427_불 {
    //백준 골드4
    //BFS?

    static int T;
    static int W, H;
    static int[][] map;
    static int[] rdir = {-1, 1, 0, 0}; //상하좌우
    static int[] cdir = {0, 0, -1, 1};
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++) {
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new int[H+2][W+2]; //탈출구까지 합해서 초기화
            Pos sang = null; //상근이 위치 초기화
            Queue<Pos> fire = new LinkedList<>();

            for (int h = 1; h <= H; h++) {
                //?? : char배열 받을 땐 StringTokenizer로 해결 못하는지?
                //(St.nextToken()).charAt(0);
                String str = br.readLine();
                for (int w = 1; w <= W; w++) {
                    char c = str.charAt(w-1);
                    if(c=='@'){
                        //상근이 시작 위치
                        sang = new Pos(h,w,0);
                    }else if(c=='*'){
                        //불
                        fire.offer(new Pos(h,w));
                        map[h][w]= -1; //방문처리
                    }else if(c=='#'){
                        map[h][w] = -1; //방문처리
                    }
                }
            }
            //---------------입력완료------------------------
            min = Integer.MAX_VALUE;
            bfs(sang, fire, map);

            //MAX_VALUE 그대로면 끝에 도달하지 못한것이므로 IMPOSSIBLE출력
            if(min != Integer.MAX_VALUE){
                System.out.println(min);
            } else{
                System.out.println("IMPOSSIBLE");
            }



        }
    }

    private static void bfs(Pos person, Queue<Pos> fire, int[][] visited){
        Queue<Pos> queue = new LinkedList<>();

        //초기화
        visited[person.r][person.c] = 1; //상근이 처음위치 방문처리 o
        queue.offer(person);

        //상근이의 위치를 담은 queue가 빌때까지 수행
        while(!queue.isEmpty()){
            //불 먼저 퍼뜨리기
            //시간 별로 퍼뜨리기 위해 초기 담겨있던 불의 개수만큼만 진행하고
            //새로 이동한 불은 다음 반복때 퍼트린다.
            for(int i=0;i<fire.size();i++){
                //불의 갯수만큼
                Pos f = fire.poll();
                int fr = f.r;
                int fc = f.c;

                for(int j=0;j<4;j++){
                    int dfr = fr+rdir[j];
                    int dfc = fc+cdir[j];
                    //위치가 유효하고 불이 번지지 않았으면 이동
                    //x로 방문처리
                    if(dfc>0 && dfr>0 && dfc<W+1 && dfr<H+1 && visited[dfr][dfc] != -1){
                        visited[dfr][dfc] = -1;
                        fire.offer(new Pos(dfr, dfc));
                    }
                }
            }

            //상근이 이동
            for(int i=0;i<queue.size();i++){
                Pos p = queue.poll();
                int r = p.r;
                int c = p.c;
                int time = p.time;

                //끝에 도달한 경우 min값 update
                if(c==0||r==0||c==W+1||r==H+1){
                    min = min>time? time:min;
                    continue;
                }

                for(int j=0;j<4;j++){
                    int dr = r+rdir[j];
                    int dc = c+cdir[j];
                    //범위가 유효하고 불이 퍼지지 않았고 상근이가 방문한 곳이 아니면 방문처리
                    if(dc>=0 && dr>=0 && dc<=W+1 && dr<=H+1){
                        if(visited[dr][dc] != -1 && visited[dr][dc] != 1){
                            queue.offer(new Pos(dr, dc, time+1));
                            visited[dr][dc] = 1;
                        }
                    }
                }
            }

        }
    }



    static class Pos{
        int r;
        int c;
        int time;
        Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
        Pos(int r, int c, int time){
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }
}

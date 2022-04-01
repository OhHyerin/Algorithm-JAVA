package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_14466_소가길을건너간이유6 {
    //백준 골드4
    //스터디 - 코테광탈

    static int N; // N : 배열 크기
    static int K; // K : 소의 개수
    static int R; // R : 길의 개수
    static ArrayList<Pos> loads[][];  //길의 정보를 담고 있는 list
    static int[][] map;  //전체 map (소가 있는 곳은 1)
    static boolean[][] visited;  //bfs검사할 때 사용하는 visited
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[] isSelected;  //combination으로 뽑힌 숫자 2개 저장하는 배열
    static Cow[] cows;  //소 number, r, c 저장하는 배열
    static int answer = 0;  //몇 쌍 연결되는지


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        loads = new ArrayList[N+1][N+1];  //길 정보
        map = new int[N+1][N+1]; //전체 맵 (소가 있는 자리는 1)

        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                loads[i][j] = new ArrayList<>();
            }
        }

        for(int i=0;i<R;i++){
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            loads[r1][c1].add(new Pos(r2,c2));
            loads[r2][c2].add(new Pos(r1,c1));
        }

        cows = new Cow[K];
        for(int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[r][c] = 1;  //소가 있는 자리는 1
            cows[i] = new Cow(i+1, r, c);
        }

        isSelected = new int[2];
        combination(0, 0);

        System.out.println(K-answer);
    }

    static void combination(int cnt, int start){
        if(cnt==2){
            int fr=0, fc=0, tr=0,tc=0;
            for(int i=0;i<cows.length;i++){
                if(cows[i].num==isSelected[0]){
                    fr = cows[i].r;
                    fc = cows[i].c;
                }else if(cows[i].num==isSelected[1]){
                    tr = cows[i].r;
                    tc = cows[i].c;
                }
            }
            bfs(fr, fc, tr, tc);
            return;
        }
        for(int i=start;i<cows.length;i++){
            isSelected[cnt] = cows[i].num;
            combination(cnt+1, i+1);
        }
    }



    private static void bfs(int r, int c, int tr, int tc){
        Queue<Pos> queue = new LinkedList<>();
        visited = new boolean[N+1][N+1];

        queue.add(new Pos(r, c));
        visited[r][c] = true;

        while(!queue.isEmpty()){
            Pos cur = queue.poll();
            int cr = cur.r;
            int cc = cur.c;

            if(cr==tr && cc==tc){
                answer++;
                return;
            }

            for(int d=0;d<4;d++){
                int nr = cr+dr[d];
                int nc = cc+dc[d];

                boolean flag = true;

                if(isIn(nr,nc)){
                    if(!visited[nr][nc]){
                        for(Pos tmp:loads[cr][cc]){
                            if(tmp.r==nr && tmp.c==nc){
                                flag = false; //불가
                                continue;
                            }
                        }

                        if(flag){
                            //다리를 건너는게 아니면 이동
                            queue.add(new Pos(nr, nc));
                            visited[nr][nc] = true;
                        }
                    }
                }
            }
        }
    }

    static boolean isIn(int r, int c){
        return r>0 && c>0 && r<=N && c<=N;
    }

    private static class Pos{
        int r;
        int c;
        Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    private static class Cow{
        int num;
        int r;
        int c;

        public Cow(int num, int r, int c) {
            this.num = num;
            this.r = r;
            this.c = c;
        }
    }
}

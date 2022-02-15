package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S2_3184_양 {
    //백준 실버2
    //BFS

    static int n, m;
    static char[][] map;
    static int resultO = 0;  //양 개수
    static int resultV = 0;  //늑대 개수
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];

        for(int i=0;i<n;i++){
            String str = br.readLine();
            for(int j=0;j<m;j++){
                map[i][j] = str.charAt(j);
            }
        }
        //------------입력완료-------------
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(map[i][j]=='#') continue;
                find(i,j);
            }
        }

        System.out.println(resultO+" "+resultV);

    }

    private static void find(int r, int c){
        int cntO = 0, cntV = 0;
        Queue<Pos> queue = new LinkedList<>();

        if(map[r][c]=='o') cntO++;
        if(map[r][c]=='v') cntV++;

        queue.offer(new Pos(r, c));
        map[r][c] = '#';//큐 넣을 때 방문처리

        while(!queue.isEmpty()){
            Pos p = queue.poll(); //현재 위치 큐 poll

            for(int d=0;d<4;d++) { //사방탐색
                int nextR = p.r + dr[d];
                int nextC = p.c + dc[d];

                if (isIn(nextR, nextC)) {
                    if (map[nextR][nextC] == 'o') cntO++;
                    if (map[nextR][nextC] == 'v') cntV++;
                    if (map[nextR][nextC] == '#') continue;
                    map[nextR][nextC]='#';
                    queue.add(new Pos(nextR, nextC));
                }
            }
        }
        if(cntO>cntV){
            resultO += cntO;
        } else{
            resultV += cntV;
        }

    }

    static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<n && c<m;
    }

    static class Pos{
        int r;
        int c;
        public Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

}

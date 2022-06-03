package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_16929_TwoDots {
    //그래프탐색
    //dfs

    static int R, C;
    static char[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] visited;
    static boolean isCycle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];

        for(int i=0;i<R;i++){
            String str = br.readLine();
            for(int j=0;j<C;j++){
                map[i][j] = str.charAt(j);
            }
        }

        outer : for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                visited = new boolean[R][C];
                dfs(i, j, i, j, 1);
                if(isCycle){
                    break outer;
                }
            }
        }

        if(isCycle){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }



    }

    /**
     *
     * @param sr  처음 시작한 위치의 r
     * @param sc  처음 시작한 위치의 c
     * @param cr  현재 위치의 r
     * @param cc  현재 위치의 c
     */
    private static void dfs(int sr, int sc, int cr, int cc, int count){
        visited[cr][cc] = true;  //현재위치 방문처리
        for(int d=0;d<4;d++){
            int nr = cr+dr[d];
            int nc = cc+dc[d];

            if(!isIn(nr, nc)) continue;  //범위 벗어나면 continue
            if(map[cr][cc]!=map[nr][nc]) continue;  //같은 알파벳 아니면 continue
            if(!visited[nr][nc]){
                //아직 방문 안했는데 map똑같으면 다음 위치로
                dfs(sr, sc, nr, nc, count+1);
            }else{
                //방문했었는데 map똑같음
                if(nr==sr && nc==sc && count>=4){
                    isCycle = true;
                    return;
                }
            }
        }

    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }
}

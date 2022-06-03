package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_16929_TwoDots {
    //�׷���Ž��
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
     * @param sr  ó�� ������ ��ġ�� r
     * @param sc  ó�� ������ ��ġ�� c
     * @param cr  ���� ��ġ�� r
     * @param cc  ���� ��ġ�� c
     */
    private static void dfs(int sr, int sc, int cr, int cc, int count){
        visited[cr][cc] = true;  //������ġ �湮ó��
        for(int d=0;d<4;d++){
            int nr = cr+dr[d];
            int nc = cc+dc[d];

            if(!isIn(nr, nc)) continue;  //���� ����� continue
            if(map[cr][cc]!=map[nr][nc]) continue;  //���� ���ĺ� �ƴϸ� continue
            if(!visited[nr][nc]){
                //���� �湮 ���ߴµ� map�Ȱ����� ���� ��ġ��
                dfs(sr, sc, nr, nc, count+1);
            }else{
                //�湮�߾��µ� map�Ȱ���
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

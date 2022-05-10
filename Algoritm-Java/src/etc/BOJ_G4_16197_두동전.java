package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_16197_두동전 {
    //그래프탐색

    /*
    - 동전이 이동하려는 칸이 벽이면, 동전은 이동하지 않는다.
    - 동전이 이동하려는 방향에 칸이 없으면 동전은 보드 바깥으로 떨어진다.
    - 그 외의 경우에는 이동하려는 방향으로 한 칸 이동한다.
        이동하려는 칸에 동전이 있는 경우에도 한 칸 이동한다.
     */

    static int R, C;
    static int[][] map;  // 0:빈칸, 1:벽, 2:동전
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for(int i=0;i<R;i++){
            String str = br.readLine();
            for(int j=0;j<C;j++){
                char temp = str.charAt(j);
                if(temp=='#') map[i][j] = 1;
                else if(temp=='o') map[i][j] = 2;
            }
        }




    }
}

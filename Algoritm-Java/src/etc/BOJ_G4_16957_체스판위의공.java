package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_16957_ü�������ǰ� {
    //dp, �׷���Ž��

    static int R, C;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for(int i=0;i<R;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<C;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //---------------�Է� �Ϸ�-------------------


    }

    private static class Pos{
        int r;
        int c;
        int num;


    }
}

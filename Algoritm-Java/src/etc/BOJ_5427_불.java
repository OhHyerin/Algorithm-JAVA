package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_5427_�� {

    static int T;
    static int W, H;
    static char[][] map;
    static ArrayList<int[]> fire;
    static int sec = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++) {
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new char[H][W];
            fire = new ArrayList<>();

            int curR=0, curC=0;

            for (int h = 0; h < H; h++) {
                //?? : char�迭 ���� �� StringTokenizer�� �ذ� ���ϴ���?
                //(St.nextToken()).charAt(0);
                String str = br.readLine();
                for (int w = 0; w < W; w++) {
                    map[h][w] = str.charAt(w);
                    if(map[h][w]=='@'){
                        //����� ���� ��ġ
                        curR = h;
                        curC = w;
                    }
                    if(map[h][w]=='*'){
                        fire.add(new int[] {h, w});
                    }
                }
            }
            //---------------�Է¿Ϸ�------------------------

        }
    }
}

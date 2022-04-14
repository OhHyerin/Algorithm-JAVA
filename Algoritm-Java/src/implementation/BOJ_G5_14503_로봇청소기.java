package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_14503_�κ�û�ұ� {
    //����, �ùķ��̼�

    /*
    1. ���� ��ġ�� û���Ѵ�
    2. ���� ��ġ���� �ٷ� ���ʿ� û������ ���� �� ������ �����Ѵٸ�
            ���� �������� ȸ�� -> �� ĭ ���� -> ���� ��ġ û��
       �� ������ �������� �ʴ´ٸ�
            ���� �������� ȸ��
    3. 1������ ���ư��ų� �������� �ʰ� 2���� 4�� ������� ���,
        �ٷ� ������ ���̶�� -> �۵��� �����
        �׷��� �ʴٸ� -> �� ĭ �����Ѵ�
     */

    static int R, C;
    static int[][] map;
    static int[] dr = {-1,0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int sr, sc, sd;
    static int count;

    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        st = new StringTokenizer(br.readLine());
        sr = Integer.parseInt(st.nextToken());
        sc = Integer.parseInt(st.nextToken());
        sd = Integer.parseInt(st.nextToken());

        for(int i=0;i<R;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<C;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        move(sr, sc, sd, 0);


        System.out.println(count);
    }

    private static void move(int r, int c, int d, int depth){
        if(map[r][c]==0){
            map[r][c] = 2;
            count++;
        }


        if(depth==4){
            //b. 1������ ���ư��ų� �������� �ʰ� 2a�ܰ谡 �������� �� �� ����Ǿ��� ���,
            int backDir =  (d+2)%4;
            if(backDir<0) backDir = 4+backDir;
            int br = r+dr[backDir];
            int bc = c+dc[backDir];
            if(isIn(br, bc)) {
                if (map[br][bc] == 1) {
                    //�ٷ� ������ ���̶�� �۵��� �����.
                    return;
                } else {
                    //�ٷ� ������ ���� �ƴ϶�� �� ĭ �����Ѵ�.
                    depth = 0;
                    move(br, bc, d, depth);
                    return;
                }
            }
        }

        int leftDir = d-1;
        if(leftDir<0) leftDir = 4+leftDir;
        int nr = r+dr[leftDir];
        int nc = c+dc[leftDir];

        if(isIn(nr, nc)) {
            if (map[nr][nc] == 0) {
                //���ʿ� û������ ���� �� ������ �����Ѵٸ�
                //���� �������� ȸ���� ���� �� ĭ �����ϰ� û��
                depth = 0;
                move(nr, nc, leftDir, depth);
                return;
            } else {
                //���ʿ� û���� �����̰ų� �� ������ �ƴ� ���
                //���⸸ �������� ȸ��
                move(r, c, leftDir, depth + 1);
                return;
            }
        }


    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }
}

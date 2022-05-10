package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_16197_�ε��� {
    //�׷���Ž��

    /*
    - ������ �̵��Ϸ��� ĭ�� ���̸�, ������ �̵����� �ʴ´�.
    - ������ �̵��Ϸ��� ���⿡ ĭ�� ������ ������ ���� �ٱ����� ��������.
    - �� ���� ��쿡�� �̵��Ϸ��� �������� �� ĭ �̵��Ѵ�.
        �̵��Ϸ��� ĭ�� ������ �ִ� ��쿡�� �� ĭ �̵��Ѵ�.
     */

    static int R, C;
    static int[][] map;  // 0:��ĭ, 1:��, 2:����
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

package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_16234_�α��̵� {
    //����, �ùķ��̼�

    /*
    1. ������ �� ���� �α����̰� L�̻�, R���϶�� ���漱�� �Ϸ絿�� ��
    2. ���漱 ��� ��������, �α� �̵� ����
    3. ���漱�� �����ִ� �� ���� �Ϸ絿�� �����̶�� �Ѵ�.
    4. ������ �̷�� �ִ� �� ĭ�� �α����� (������ �α���)/(������ �̷�� �ִ� ĭ�� ����)�� �ȴ�.(�Ҽ��� ����)
    5. ���� ��ü�ϰ�, ��� ���漱 ����
     */

    static int N; //N*N
    static int L, R;  //L�̻� R����
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }



    }

    private static void open(){
        int index = 0;



    }

}

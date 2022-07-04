package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G3_23288_�ֻ���������2 {
    //����, �ùķ��̼�, �׷���Ž��

    /*
    ���� ���� :
    1. �ֻ����� �̵� �������� �� ĭ ��������. ����, �̵� ���⿡ ĭ�� ���ٸ�, �̵� ������ �ݴ�� �� ���� �� ĭ ��������.
    2. �ֻ����� ������ ĭ(x, y)�� ���� ������ ȹ���Ѵ�.
    3. �ֻ����� �Ʒ��鿡 �ִ� ���� A�� �ֻ����� �ִ� ĭ(x,y)�� �ִ� ���� B�� ���� �̵� ������ �����Ѵ�.
        1) A>B�� ��� �̵������� 90�� �ð�������� ȸ����Ų��.  --> dr, dc�� +1
        2) A<B�� ��� �̵������� 90�� �ݽð�������� ȸ����Ų��  --> dr, dc�� -1
        3) A=B�� ��� �̵����⿡ ��ȭ�� ����.
     */

    static int R, C;  //R*C
    static int K; //�̵��ϴ� Ƚ��
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};  //��->��->��->��
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());  //�̵��ϴ� Ƚ��

        map = new int[R+1][C+1];

        for(int i=1;i<=R;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=C;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

    }
}

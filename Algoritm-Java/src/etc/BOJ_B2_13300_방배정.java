package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_B2_13300_����� {
    //IM���

    static int N, K;
    static int[][] room;
    static int roomCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //�л���
        K = Integer.parseInt(st.nextToken()); //�� �濡 ������ �� �ִ� �ִ� �ο� ��
        room = new int[7][2];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());  //����(��:0, ��:1)
            int y = Integer.parseInt(st.nextToken());  //�г��

            room[y][s]++;  //�� ���� �ο� ����
       }




    }
}

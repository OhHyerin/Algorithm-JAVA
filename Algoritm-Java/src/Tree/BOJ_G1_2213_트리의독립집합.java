package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G1_2213_Ʈ���ǵ������� {
    //���� ���1
    //�����ǹ���

    /*
    ��������
    - ���������̶�, �׷��� �̷п��� � �� �������� ���� �������� �ʴ� �׷����� �ִ� ���������� ������ �̸��� ���̴�.
    - �������տ� �����ִ� ������ ���� �ڽŰ� ����� ��� ���� ���������� �ʴ�.
     */

    static int N;
    static int[] weight; //w1~wn���� ���� i�� ����ġ

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        weight = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            weight[i] = Integer.parseInt(st.nextToken());
        }



    }
}

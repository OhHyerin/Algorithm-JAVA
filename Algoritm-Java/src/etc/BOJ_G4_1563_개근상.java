package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_1563_���ٻ� {
    //dp

    /*
    O : �⼮
    L : ����
    A : �Ἦ

    ���ٻ��� ���� �� ���� ��� : ���� 2���̻� or �Ἦ 3�� ����
    -> ���ٻ� ���� �� �ִ� ��� : ����0��, ����1��, �Ἦ�� 2��+�⼮�� �� �Ǵ� �Ἦ2�� + ����

    ������ 1,000,000���� ���� ������ ���
     */

    static int N;  //N���� �� �б�

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

    }

    private static long attended(int total, int late, int unattended){
        long sum = 0;
        if(total==N) return 1;  //N�� �� ���������� �� ���� ��� ����




        return sum;
    }
}

package divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_��2_18222_�����𽺹��ڿ� {
    //�̺�Ž��
    //ArrayList<Integer>�� Ǫ�ϱ� �޸��ʰ���

    //���̰� n�̸� 0~n-1���� �����ϱ�
    //������ ������ 0~(n-1)/2�� ���ʿ�, (n-1)/2~(n-1)������ �����ʿ�
    //�����ʿ� �ִ� ���� ���ʿ� �ִ� ���� ������Ų ��
    //������ �״�� ã��, �������� ���ʿ��� ã��

    //��Ʈ�� �ǹ��ϴ°� 2�� i������ �ǹ�
    //1�� ��Ʈ�� ���� ���� �� : ū i���� �� �ܰ辿 �������鼭 ������ ��
    //��, ��Ʈ�� 1�̸� �����ʿ� �ִٴ� ���̰�, 0�̸� ���ʿ� �ִٴ� ���

   static long K;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Long.parseLong(br.readLine());

        long cnt = 0;
        while(K>0){

        }

    }

    static void makeString(int cnt){
        //base
        if(cnt>=K) {
            return;
        }

        //inductive


        makeString(cnt*2);
    }


}

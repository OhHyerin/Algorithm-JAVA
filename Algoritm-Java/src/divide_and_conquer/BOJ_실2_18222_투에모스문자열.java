package divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_��2_18222_�����𽺹��ڿ� {
    //�̺�Ž��
    //ArrayList<Integer>�� Ǫ�ϱ� �޸��ʰ���
    //����? ���ذ�? �� ? �ȵ�?????????

    //���̰� n�̸� 0~n-1���� �����ϱ�
    //������ ������ 0~(n-1)/2�� ���ʿ�, (n-1)/2~(n-1)������ �����ʿ�
    //�����ʿ� �ִ� ���� ���ʿ� �ִ� ���� ������Ų ��
    //������ �״�� ã��, �������� ���ʿ��� ã��

    //��Ʈ�� �ǹ��ϴ°� 2�� i������ �ǹ�
    //1�� ��Ʈ�� ���� ���� �� : ū i���� �� �ܰ辿 �������鼭 ������ ��
    //��, ��Ʈ�� 1�̸� �����ʿ� �ִٴ� ���̰�, 0�̸� ���ʿ� �ִٴ� ��

    //f(x)�� x��°�� �ش��ϴ� ���� ��� Ī�ϸ� f(1) = 0�� �ǰ�, x���� ���� 2�� �ŵ������� y��� �Ѵ�
    //�׷� f(x) = 1-f(x-y)�� �ȴ�. �̰� ��ͷ� �����ָ� �ȴ�.

   static long K;
   static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Long.parseLong(br.readLine());
        arr = new long[64];
        arr[0] = 1;
        for(int i=1;i<64;i++){
            arr[i] = arr[i-1]*2; //ũ��?
        }
        System.out.println(sol(K));

    }

    private static int sol(long x){
        if(x==1) return 0;
        for(int i=0;i<64;i++){
            if(arr[i]>=x) return 1-sol(x-arr[i-1]);
        }
        return 0;
    }


}

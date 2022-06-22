package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_16916_�κй��ڿ�_KMP {
    //KMP

    /*
    KMP?
    ���λ�� ���̻��� ������ Ȱ���Ͽ� ��� ��츦 ������� �ʰ�
    �ݺ��Ǵ� ������ �ٿ����� ��Ī ���ڿ��� ������ �����ϴ� ����̴�.
    2�� for���� ����ϴ� �ͺ��� �ξ� ȿ��������
    */

    static String S;
    static String P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine();
        P = br.readLine();

        System.out.println(KMP(S, P));

    }

    static int KMP(String parent, String pattern){
        int[] table = makeTable(pattern);
        int n1 = parent.length();  //��ü ����
        int n2 = pattern.length();  //ã�� ���� ����

        int index = 0;

        for(int i=0;i<n1;i++){
            //��ü ���� Ž��
            //table�Լ��� �̿��Ͽ� parent���ڿ��� find ���ڿ��� index 0 ���� �˻�
            while(index>0 && parent.charAt(i) != pattern.charAt(index)){
                index = table[index-1];  //��ġ���� ������
                //��Ī�� �ٽ� �����ؾ� �ϹǷ� index���� �ٽ� ������
                //0�� �ƴ϶� ��ġ�ϴ� ���̸�ŭ ������ (���λ� �κ� �����ϰ� Ž�� ����)
            }

            if(parent.charAt(i)==pattern.charAt(index)){
                if(index == n2-1){  //index�� ã�� ������ ���̸�ŭ �������� Ž�� ����
                    index = table[index];
                    return 1;
                }else{  //���� �����ִ� ���̸� ���� Ž��
                    index += 1;
                }
            }
        }
        return 0;  //��Ī �ȵ����� 0
    }

    /**
     * ã�� ���ڿ�(find)�� ���λ� ���̻� �ִ� ���̸� �����ϴ� table[] �迭�� �����Ѵ�.
     * @param pattern : ã�� ���ڿ�
     * @return
     */
    static int[] makeTable(String pattern){
        int n = pattern.length();
        int[] table = new int[n];

        int index = 0;
        for(int i=1;i<n;i++){
            /*
            idx>0 : ��ġ�ϴ� ���ڰ� �߻����� ��
            pattern.charAt(i) != pattern.charAt(index) : ���������� �� ��ġ���� ����
             */
            while(index>0 && pattern.charAt(i) != pattern.charAt(index)){
                index = table[index-1];
            }

            if(pattern.charAt(i)==pattern.charAt(index)){
                index += 1;
                table[i] = index;
            }
        }
        return table;
    }
}

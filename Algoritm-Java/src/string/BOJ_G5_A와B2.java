package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_A��B2 {
    //����Ž��, ���
    //������ ��ġ �߿�

    /*
    - ���ڿ� �ڿ� A�� �߰��Ѵ�.
    - ���ڿ� �ڿ� B�� �߰��ϰ� ���ڿ��� �����´�.

    �� �ݴ��

    - ���ڿ� �ڿ� A�� �����Ѵ�.
    - ���ڿ��� ������ �����Ѵ�
     */

    static String a;
    static String b;
    static int answer;
    static StringBuilder sb;
    static int aLength;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        a = br.readLine();
        b = br.readLine();

        aLength = a.length();

        make(b);
        System.out.println(answer);

    }

    private static void make(String b){
//        System.out.println(b);
        if(b.length()==aLength){
            if(b.equals(a)){
                answer = 1;
                return;
            }
            answer = 0;
            return;
        }

        if(b.charAt(b.length()-1)=='A'){  //�� �ڰ� A��� A����
            make(b.substring(0, b.length()-1));
        }
        if(answer==1) return;
        if(b.charAt(0)=='B'){  //���� B�̸� ����� �� �ڿ� ���� B����
            sb = new StringBuilder();
            sb.append(b).reverse();
            make(sb.substring(0, sb.length()-1));
        }
    }


}

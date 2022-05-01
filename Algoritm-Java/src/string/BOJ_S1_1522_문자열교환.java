package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S1_1522_���ڿ���ȯ {
    //���ڿ�

    //a�� ������ ����, �� ���� b�� �ּ� ������ ��

    static String str;
    static char[] chars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine();
        chars = str.toCharArray();
        int aCount = 0;

        for(int i=0;i<chars.length;i++){
            if(chars[i]=='a'){
                aCount++;  //a ���� ����
            }
        }

        int minCount = chars.length;
        int bCount;
        for(int i=0;i<chars.length;i++){
            bCount = 0;
            for(int j=i;j<i+aCount;j++){
                if(chars[j%chars.length]=='b'){
                    bCount++;  //a�� �ѷ����� ���� b�� ���� ����
                }
            }
            minCount = Math.min(minCount, bCount);
        }

        System.out.println(minCount);

    }
}

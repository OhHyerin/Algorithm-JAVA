package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_20437_���ڿ�����2 {

    /*
    1. ���ĺ� �ҹ��ڷ� �̷���� ���ڿ� W�� �־�����.
    2. ���� ���� K�� �־�����.
    3. � ���ڸ� ��Ȯ�� K�� �����ϴ� ���� ª�� ���� ���ڿ��� ���̸� ���Ѵ�.
    4. � ���ڸ� ��Ȯ�� K�� �����ϰ�,
        ���ڿ��� ù ��°�� ������ ���ڰ� �ش� ���ڷ� ���� �� ���� ���ڿ��� ���̸� ���Ѵ�.
     */

    static String W;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){

            W = br.readLine();
            K = Integer.parseInt(br.readLine());



        }//t
    }
}

package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_9251_LCS {
    //���� ���5
    //���͵� - dp

    static char[] chars1;  //���ڿ�1 char����
    static char[] chars2;  //���ڿ�2 char����
    static int[][] dp; //���ڿ�1 * ���ڿ�2 ���̸�ŭ dp�迭 ����
    static int maxLength = Integer.MIN_VALUE; //LCS�� �ִ�

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();

        chars1 = str1.toCharArray();
        chars2 = str2.toCharArray();

        //dp�迭���� i=0, j=0�� �� i-1, j-1�� Ž���ϱ����� +1�� ũ�⸸ŭ �迭 ����
        dp = new int[chars1.length+1][chars2.length+1];

        for(int i=1;i<=chars1.length;i++){
            for(int j=1;j<=chars2.length;j++){

                int temp = Math.max(dp[i-1][j], dp[i][j-1]);  //temp = dp�迭�� ��, ���� �� �� ū �� ����
                if(chars1[i-1]==chars2[j-1]){ //���� �� char�� ������
                    /*
//                    temp += 1;
                        �� �� �ȵǴ� �ݷ�
                        str1 = ABAABA
                        str2 = AA
                        temp = Math.max(dp[i-1][j], dp[i][j-1])+1 �� �� -> ���� 5
                        temp = dp[i-1][j-1]+1�� �� -> ���� 2
                    */
                    temp = dp[i-1][j-1]+1;  //temp�� dp�迭 �밢�� ���� ���� +1
                }
                dp[i][j] = temp;  //dp�迭�� temp�� ����
                maxLength = Math.max(maxLength, dp[i][j]);  //dp�迭 ������ ������ ���� ū �� ����
            }
        }

//        for(int i=1;i<=chars1.length;i++){
//            for(int j=1;j<=chars2.length;j++){
//                System.out.print(dp[i][j]+" ");
//            }
//            System.out.println();
//        }

        System.out.println(maxLength);

    }
}

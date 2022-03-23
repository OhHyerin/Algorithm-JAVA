package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_9251_LCS {
    //백준 골드5
    //스터디 - dp

    static char[] chars1;  //문자열1 char집합
    static char[] chars2;  //문자열2 char집합
    static int[][] dp; //문자열1 * 문자열2 길이만큼 dp배열 선언
    static int maxLength = Integer.MIN_VALUE; //LCS의 최댓값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();

        chars1 = str1.toCharArray();
        chars2 = str2.toCharArray();

        //dp배열에서 i=0, j=0일 때 i-1, j-1을 탐색하기위해 +1의 크기만큼 배열 선언
        dp = new int[chars1.length+1][chars2.length+1];

        for(int i=1;i<=chars1.length;i++){
            for(int j=1;j<=chars2.length;j++){

                int temp = Math.max(dp[i-1][j], dp[i][j-1]);  //temp = dp배열의 위, 왼쪽 값 중 큰 값 저장
                if(chars1[i-1]==chars2[j-1]){ //만약 두 char가 같으면
                    /*
//                    temp += 1;
                        일 때 안되는 반례
                        str1 = ABAABA
                        str2 = AA
                        temp = Math.max(dp[i-1][j], dp[i][j-1])+1 일 땐 -> 답이 5
                        temp = dp[i-1][j-1]+1일 땐 -> 답이 2
                    */
                    temp = dp[i-1][j-1]+1;  //temp는 dp배열 대각선 위의 값에 +1
                }
                dp[i][j] = temp;  //dp배열에 temp값 저장
                maxLength = Math.max(maxLength, dp[i][j]);  //dp배열 갱신할 때마다 가장 큰 값 저장
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

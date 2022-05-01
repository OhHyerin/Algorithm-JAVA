package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S1_1522_문자열교환 {
    //문자열

    //a의 갯수를 세고, 그 안의 b의 최소 갯수를 셈

    static String str;
    static char[] chars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine();
        chars = str.toCharArray();
        int aCount = 0;

        for(int i=0;i<chars.length;i++){
            if(chars[i]=='a'){
                aCount++;  //a 개수 세기
            }
        }

        int minCount = chars.length;
        int bCount;
        for(int i=0;i<chars.length;i++){
            bCount = 0;
            for(int j=i;j<i+aCount;j++){
                if(chars[j%chars.length]=='b'){
                    bCount++;  //a로 둘러쌓인 곳에 b의 개수 세기
                }
            }
            minCount = Math.min(minCount, bCount);
        }

        System.out.println(minCount);

    }
}

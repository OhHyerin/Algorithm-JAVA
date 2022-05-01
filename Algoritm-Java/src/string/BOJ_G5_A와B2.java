package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_A와B2 {
    //완전탐색, 재귀
    //조건의 위치 중요

    /*
    - 문자열 뒤에 A를 추가한다.
    - 문자열 뒤에 B를 추가하고 문자열을 뒤집는다.

    를 반대로

    - 문자열 뒤에 A를 삭제한다.
    - 문자열을 뒤집고 삭제한다
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

        if(b.charAt(b.length()-1)=='A'){  //맨 뒤가 A라면 A삭제
            make(b.substring(0, b.length()-1));
        }
        if(answer==1) return;
        if(b.charAt(0)=='B'){  //앞이 B이면 뒤집어서 맨 뒤에 오는 B삭제
            sb = new StringBuilder();
            sb.append(b).reverse();
            make(sb.substring(0, sb.length()-1));
        }
    }


}

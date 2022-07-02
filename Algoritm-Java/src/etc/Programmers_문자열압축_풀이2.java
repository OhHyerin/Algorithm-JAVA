package etc;

public class Programmers_문자열압축_풀이2 {
    //문자열
    //2020 카카오 BLIND RECRUITMENT

    public static void main(String[] args) {
        String s = "aabbaccc";  //answer : 7
//        String s = "ababcdcdababcdcd";  //answer : 9
//        String s = "abcabcdede";  //answer : 8
//        String s = "abcabcabcabcdededededede";  //answer : 14
//        String s = "xababcdcdababcdcd";  //answer : 17

        System.out.println(solution(s));
    }

    public static int solution(String s) {
        int answer = 0;

        String pattern = "";
        String newStr = "";

        for(int i=0;i<s.length();i++){  //문자열 s
            for(int j=2;j<s.length();j++){  //패턴의 길이


            }
        }

        return answer;
    }

}

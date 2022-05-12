package string;

public class Programmers_문자열압축 {
    // 문자열
    // https://programmers.co.kr/learn/courses/30/lessons/60057

    public static void main(String[] args) {
        System.out.println(solution("aabbaccc"));
        System.out.println(solution("ababcdcdababcdcd"));
        System.out.println(solution("abcabcdede"));
        System.out.println(solution("abcabcabcabcdededededede"));
        System.out.println(solution("xababcdcdababcdcd"));
    }

    static int solution(String s){
        int answer = s.length();

        for(int i=1;i<=s.length()/2;i++){  //크기가 1부터 s.length()/2까지 검사
            int leng = compression(s, i).length();
            answer = Math.min(answer, leng);
        }


        return answer;
    }

    static String compression(String str, int i){

        int count = 1;
        String pattern = "";
        String compression = "";

        for(int j=0;j<=str.length()+i;j+=i){
            String cur;

            //전 문자열과 비교할 현재 문자열
            if(j>=str.length()){  //현재 문자열이 없을 때
                cur="";
            }else if(str.length()<j+i) { //마지막 현재 문자열일 때
                cur = str.substring(j);
            }else{
                cur = str.substring(j, j+i);  //그 외
            }

            //전 문자열과 똑같은지 비교한다. (맨 처음빼고)
            if(j!=0){
                if(cur.equals(pattern)){  // 똑같으면 count++
                    count++;
                }else if(count>=2){
                    //문자열이 다르고 count가 2 이상이면
                    compression+=count+pattern; //compression에 추가
                    count = 1;  //count는 다시 초기화
                }else{  //압축 불가능하면 문자열 그냥 붙이기
                    compression+=pattern;
                }
            }
            pattern = cur;  //i길이만큼 문자열 자르기
        }
        return compression;

    }

}

package etc;

public class Programmers_모음사전 {

    /*
    f(0) = 1
    f(1) = 1+5^1 = 6
    f(2) = 6+5^2 = 31
    f(3) = 31+5^3 = 156
    f(4) = 156+5^4 = 781
     */

    public static void main(String[] args) {
        String word = "AAAAE";

        System.out.println(solution(word));

    }

    public static int solution(String word){
        int answer = 0;

        answer = word.length();
        char[] alpha = {'A', 'E', 'I', 'O', 'U'};
        int[] value = {781, 156, 31, 6, 1};

        for(int i=0;i<word.length();i++){
            for(int j=0;j<5;j++){
                if(word.charAt(i)==alpha[j]){
                    answer += value[i]*j;
                }
            }
        }

        return answer;
    }

}

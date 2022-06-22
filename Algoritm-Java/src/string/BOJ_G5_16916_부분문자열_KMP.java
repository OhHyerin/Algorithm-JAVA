package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_16916_부분문자열_KMP {
    //KMP

    /*
    KMP?
    접두사와 접미사의 개념을 활용하여 모든 경우를 계산하지 않고
    반복되는 연산을 줄여나가 매칭 문자열을 빠르게 점프하는 기법이다.
    2중 for문을 사용하는 것보다 훨씬 효율적임임
    */

    static String S;
    static String P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine();
        P = br.readLine();

        System.out.println(KMP(S, P));

    }

    static int KMP(String parent, String pattern){
        int[] table = makeTable(pattern);
        int n1 = parent.length();  //전체 문자
        int n2 = pattern.length();  //찾을 패턴 문자

        int index = 0;

        for(int i=0;i<n1;i++){
            //전체 문자 탐색
            //table함수를 이용하여 parent문자열과 find 문자열을 index 0 부터 검사
            while(index>0 && parent.charAt(i) != pattern.charAt(index)){
                index = table[index-1];  //일치하지 않으면
                //매칭을 다시 시작해야 하므로 index값을 다시 돌려줌
                //0이 아니라 일치하는 길이만큼 돌려줌 (접두사 부분 생략하고 탐색 가능)
            }

            if(parent.charAt(i)==pattern.charAt(index)){
                if(index == n2-1){  //index가 찾을 패턴의 길이만큼 돌았으면 탐색 종료
                    index = table[index];
                    return 1;
                }else{  //아직 돌고있는 중이면 다음 탐색
                    index += 1;
                }
            }
        }
        return 0;  //매칭 안됐으면 0
    }

    /**
     * 찾는 문자열(find)의 접두사 접미사 최대 길이를 저장하는 table[] 배열을 생성한다.
     * @param pattern : 찾는 문자열
     * @return
     */
    static int[] makeTable(String pattern){
        int n = pattern.length();
        int[] table = new int[n];

        int index = 0;
        for(int i=1;i<n;i++){
            /*
            idx>0 : 일치하는 문자가 발생했을 때
            pattern.charAt(i) != pattern.charAt(index) : 연속적으로 더 일치하지 않음
             */
            while(index>0 && pattern.charAt(i) != pattern.charAt(index)){
                index = table[index-1];
            }

            if(pattern.charAt(i)==pattern.charAt(index)){
                index += 1;
                table[i] = index;
            }
        }
        return table;
    }
}

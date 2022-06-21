package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G4_2661_좋은수열 {
    //백트래킹

    static int N;
    static String result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        combi(0, new int[N]);

        System.out.println(result);
    }

    public static void combi(int cnt, int[] selected) {
        if(result!=null) return;
        if (cnt == N) {
            //N개 다 뽑았으면
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<N;i++){
                sb.append(selected[i]);
            }
            result = sb.toString();
            return;
        }

        for (int i = 1; i <= 3; i++) {
            selected[cnt] = i;
            if (isBad(cnt, selected)) {
                //중복되는게 있는 나쁜 배열이면
                continue;  //해당 숫자 말고 다음 숫자
            }else{
                //좋은 배열이면
                combi(cnt+1, selected);  //다음숫자 뽑으러
            }
        }
    }

    public static boolean isBad(int cnt, int[] selected) {
        StringBuilder strSelected = new StringBuilder();
        for (int i = 0; i < cnt + 1; i++) {  //cnt까지 selected가 유효한지 검사
            strSelected.append(selected[i]);
        }
        String str = strSelected.toString();
//        System.out.println(str);

        for (int i = 1; i <= str.length(); i++) {  //몇 개의 문자열을 검사할건지 (1부터 str의 길이만큼)
            for (int j = 0; j < str.length(); j++) {  //str에 있는지 검사
                if(i*2 + j > str.length()) break;  //str이 123이고 검사할길이*2+검사해야하는위치가 str의 길이를 넘어가면 break

                String str1 = str.substring(j, j+i);  //j부터 j+i까지 (검사할 길이가 i니까)
                String str2 = str.substring(j+i, j+i*2);  //j+i부터 j+i*2까지
                if(str1.equals(str2)) return true;  //중복되므로 나쁜 배열
            }
        }

        return false;  //똑같은게 없으면 좋은 배열
    }
}

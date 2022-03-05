package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S3_9996_한국이그리울땐서버에접속하지_fail {
    //백준 실버3
    //오늘의문제
    //index 비교로 풀려다 실패 - 5%에서 틀렸습니다 나옴

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        String pattern = br.readLine();
        int patternSize = pattern.length();

        for(int i=0;i<n;i++){
            String str = br.readLine();

            //패턴보다 입력받은 String이 짧을 때
            if(patternSize-1>str.length()){
                sb.append("NE").append("\n");
                continue;
            }
            //첫 글자 검사
            if(str.charAt(0)!=pattern.charAt(0)){
                sb.append("NE").append("\n");
                continue;
            }

            int idx = 1;
            for(int j=1;j<str.length();j++){
                if(pattern.charAt(idx)=='*'){
                    idx++;  //*만나면 다음 pattern으로
                }
                if(idx==patternSize-1){  //pattern마지막 char면
                    if(j!=str.length()-1) {  //j가 마지막위치가 아니면
                        if (str.charAt(str.length() - 1) != pattern.charAt(idx)) {
                            idx = 0;
                            break;
                        } else {
                            idx++;
                            break;
                        }
                    }
                }
                if(str.charAt(j)==pattern.charAt(idx)){
//                    System.out.println("j : "+j+" idx : "+idx+" : true");
//                    System.out.println("str.chatAt(j) : "+str.charAt(j)+"   pattenrn.charAt(idx) : "+pattern.charAt(idx));
                    idx++;
                }
            }
            if(idx==pattern.length()){
                sb.append("DA").append("\n");
            } else{
                sb.append("NE").append("\n");
            }
        }

        System.out.println(sb);



    }
}

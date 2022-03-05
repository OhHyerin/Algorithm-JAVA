package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S3_9996_한국이그리울땐서버에접속하지 {
    //백준 실버3
    //오늘의문제

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        String pattern = br.readLine();
        int starIdx = pattern.indexOf("*");
//        System.out.println(starIdx);

        String patternStart = pattern.substring(0, starIdx);  //pattern 처음부터 * 전까지
        String patternEnd = pattern.substring(starIdx+1); //pattern *다음부터 끝까지

        int patternStartSize = patternStart.length();
        int patternEndSize = patternEnd.length();

        for(int i=0;i<n;i++){
            String str = br.readLine();
            int strSize = str.length();


            if(strSize<pattern.length()-1){  //패턴사이즈보다 String길이가 짧으면
                sb.append("NE").append("\n");
            }else{
                String strStart = str.substring(0, patternStartSize);
                String strEnd = str.substring(str.length()-patternEndSize);
                if(patternStart.equals(strStart) && patternEnd.equals(strEnd)){
                    sb.append("DA").append("\n");
                }else{
                    sb.append("NE").append("\n");
                }
            }
        }
        System.out.println(sb);


    }
}

package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_1474_밑줄 {
    //백준 실버1
    //그리디인 줄 알았는데 완탐으로 풀 수 있었음.

    static int N, M;
    static String[] word;
    static int len;
    static String result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        word = new String[N*2-1]; //N개의 단어, N-1개의 밑줄

        len = 0;
        for(int i=0;i<N;i++){
            String str = br.readLine();
            word[i*2] = str; //밑줄 자리 남겨두고 짝수번째에 단어 추가
            len += str.length(); //단어 길이
        }

        int left_len = (M-len)/(N-1); //넣어야되는 _갯수 / 단어개수
//        System.out.println("len : "+len);
//        System.out.println("left_len : "+left_len);
        for(int i=0;i<left_len;i++){
            sb.append("_");  //left_len길이만큼 각 단어들 사이에 밑줄 추가
        }

        for(int i=1;i<N*2-1;i+=2){
            word[i] = sb.toString();
            len += sb.length();  //밑줄 개수도 len에 추가
        }

        addMore(0, 1);
        System.out.println(result);

    }

    static void addMore(int cnt, int pos){
        //base
        if(cnt==M-len){ //남은 밑줄 개수 다 채웠으면
            StringBuilder sb = new StringBuilder();
            for(String words : word){
                sb.append(words);
            }
            if(result == null){
                //우선 현재꺼 result에 저장(result가 비어있을 때 쓸 초기값)
                result = sb.toString();
            }
            else if(result.compareTo(sb.toString())>0){
                //우선순위 비교 후 사전상 앞이라면 result에 저장
                result = sb.toString();
            }
            return;
        }
        if(pos >= N*2-1) return;
        //inductive
        addMore(cnt, pos+2); //_추가 안하고 다음 _위치로
        word[pos] = word[pos].concat("_");  //concat : 문자열 붙이기
        addMore(cnt+1, pos+2); //_개수 1 추가하고 다음 _위치로
        word[pos] = word[pos].substring(0, word[pos].length()-1); //substring(int i, int j) :  i~j외에 문자열 자르기
    }
}

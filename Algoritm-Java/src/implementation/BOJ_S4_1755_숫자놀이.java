package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_S4_1755_숫자놀이 {
    static int M, N;
    //각 자리의 숫자 변환할 String배열 미리 만들어주기
    static String[] transStr = {"zero", "one", "two", "three", "four", "five", "six", "seven" ,"eight", "nine"};
    static List<Num> list;  //Num클래스를 담을 list

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()); //한 줄 입력받음
        StringBuilder sb = new StringBuilder();

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        int size = N-M+1;  //M~N까지의 크기

        for(int i=M;i<=N;i++) {  //M부터 N까지의 숫자를
            list.add(new Num(i));  //list에 추가
        }

//        System.out.println(list);

        Collections.sort(list);  //Comparable을 기준으로 정렬

        for(int i=0;i<list.size();i++) {  //list 크기만큼 반복
            sb.append(list.get(i).num).append(" ");
            if((i+1)%10==0) {  //한 줄에 10개 출력 후
                sb.append("\n");  //줄바꿈
            }
        }

        System.out.println(sb);


    }

    private static class Num implements Comparable<Num>{
        int num;
        String str="";

        public Num(int num) {  //num만 입력 받은 후
            super();
            this.num = num;

            if(num>=10) {  //num이 10보다 크면 num/10 값을 String으로 변환 (10보다 작은 수는 "zero"를 출력 안하기위해)
                str = transStr[num/10]+" ";
            }
            str += transStr[num%10];  //num%10값을 String으로 변환
        }

        @Override
        public int compareTo(Num o) {
            return str.compareTo(o.str);  //str을 사전순으로 정렬
        }

        @Override
        public String toString() {
            return "Num{" +
                    "num=" + num +
                    ", str='" + str + '\'' +
                    '}';
        }
    }
}

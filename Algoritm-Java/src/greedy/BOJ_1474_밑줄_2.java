package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1474_밑줄_2 {
    //다 대문자면 될 수 있으면 _는 뒤쪽에
    //다 소문자면 될 수 있으면 _는 앞쪽에
    //대문자+소문자면 _는 대문자 소문자 사이에

    static int n, m;
    static ArrayList<String> list= new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int index = 0;
        for(int i=0;i<n;i++){
            list.add(br.readLine());
        }
        //-------------입력완료-----------------
        int str_length = 0;
        for(int i=0;i<list.size();i++){
//            System.out.print(list.get(i)+" ");
            str_length += list.get(i).length();
        }
//        System.out.println("문자열 길이 : "+str_length);

        int _length = m-str_length; //남은 _의 최소 개수
        int min_length = _length/(n-1);
        int left_length = _length-(min_length*(n-1));
        for(int i=0;i<n-1;i++){
            String str="_";
            for(int j=0;j<min_length-1;j++){
                str = str+"_";
            }
            list.set(i, list.get(i)+str);
        }

        int upper = 0, lower = 0;
        for(int i=0;i<n;i++){
            if(list.get(i).charAt(0)<'_'){
//                대문자
                upper += 1;
            }else if(list.get(i).charAt(0)>'_'){
                lower += 1;
            }
        }
//        System.out.println("upper : "+upper);
//        System.out.println("lower : "+lower);
//        System.out.println("left_length : "+left_length);

        if(upper!=0 && lower!=0){
            for(int i=0;i<n-1;i++) {
                if(left_length==0) break;

                if (list.get(i).charAt(0) < '_') {
                    list.set(i, list.get(i) + '_');
                    left_length--;
//                    System.out.println("if문 안에서 left_length : "+left_length);
                }
//                System.out.println("if문 밖에서 left_length : "+left_length);
            }
            if(left_length>0){
                int i=0;
                while(left_length!=0){
                    if(list.get(i).charAt(0)>'_'){
                        list.set(i, list.get(i)+'_');
                        left_length--;
                    }
                    i++;
                }
            }
        }else if(lower==0){
            //다 대문자
            int i=n-2;
            while(left_length>0){
                list.set(i, list.get(i)+'_');
                i--;
                left_length--;
            }
        }else if(upper==0){
            //다 소문자
            int i=0;
            while(left_length>0){
                list.set(i, list.get(i)+'_');
                i++;
                left_length--;
            }
        }


        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i));
        }

    }
}

package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_1474_밑줄 {
    //백준 실버1
    //그리디?
    //예제 다 맞는데 틀렸습니다 나옴

    static int n, m;
    static ArrayList<String> list= new ArrayList<>();
//    static char[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
//        result = new char[m];
        int index = 0;
        for(int i=0;i<n;i++){
            list.add(br.readLine());
//            st = new StringTokenizer(br.readLine());
//            index += st.countTokens();
//            for(int j=index;st.hasMoreTokens();j++){
//                result[j] = (st.nextToken()).charAt(0);
            }
        //-------------입력완료-----------------
//        list.sort(new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.compareTo(o2);
//            }
//        });
        //---------정렬했는데 필요없는거같음----------
        int str_length = 0;
        for(int i=0;i<list.size();i++){
//            System.out.print(list.get(i)+" ");
            str_length += list.get(i).length();
        }

        int _length = m-str_length; //남은 _의 최소 개수
        int min_length = _length/(n-1);
        _length = _length-(min_length*(n-1));

        for(int i=0;i<n-1;i++){
            String str="_";
            for(int j=0;j<min_length-1;j++){
                str = str+"_";
            }
            list.set(i, list.get(i)+str);
        }

        for(int i=0;i<n-1;i++){
            if(_length>0){
                if((list.get(i)).charAt(0)<'_' && (list.get(i+1)).charAt(0)>'_'){
                    //대문자와 소문자 사이면 추가
                    list.set(i, list.get(i)+"_");
                    _length--;
                }else if((list.get(i)).charAt(0)>'_' && (list.get(i+1)).charAt(0)<'_') {
                    //소문자와 대문자 사이면 추가 X
                    continue;
                }else if(((list.get(i)).charAt(0)<'_' && (list.get(i)).charAt(0)<'_') || ((list.get(i)).charAt(0)<'_' && (list.get(i)).charAt(0)<'_')){
                    //대문자와 대문자 또는 소문자와 소문자면
                    //뒤에서부터 남은 _를 추가
                    for(int j=n-2;j>0;j--){
                        if(_length<=0){
                            break;
                        }
                        list.set(j, list.get(j)+"_");
                        _length--;
                    }
                }
            }else{
                break;
            }
        }

        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i));
        }

    }
}

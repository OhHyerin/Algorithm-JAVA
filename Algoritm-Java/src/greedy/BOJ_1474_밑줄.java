package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_1474_���� {
    //���� �ǹ�1
    //�׸���?
    //���� �� �´µ� Ʋ�Ƚ��ϴ� ����

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
        //-------------�Է¿Ϸ�-----------------
//        list.sort(new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.compareTo(o2);
//            }
//        });
        //---------�����ߴµ� �ʿ���°Ű���----------
        int str_length = 0;
        for(int i=0;i<list.size();i++){
//            System.out.print(list.get(i)+" ");
            str_length += list.get(i).length();
        }

        int _length = m-str_length; //���� _�� �ּ� ����
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
                    //�빮�ڿ� �ҹ��� ���̸� �߰�
                    list.set(i, list.get(i)+"_");
                    _length--;
                }else if((list.get(i)).charAt(0)>'_' && (list.get(i+1)).charAt(0)<'_') {
                    //�ҹ��ڿ� �빮�� ���̸� �߰� X
                    continue;
                }else if(((list.get(i)).charAt(0)<'_' && (list.get(i)).charAt(0)<'_') || ((list.get(i)).charAt(0)<'_' && (list.get(i)).charAt(0)<'_')){
                    //�빮�ڿ� �빮�� �Ǵ� �ҹ��ڿ� �ҹ��ڸ�
                    //�ڿ������� ���� _�� �߰�
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

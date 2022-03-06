package data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_S5_1181_�ܾ����� {
    //���� �ǹ�5
    //�����ǹ���

    //�ߺ��� ��� ���ϹǷ� HashSet���� �Է¹��� ��
    //ArrayList�� ��ȯ �� Collection.sort���

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        HashSet<String> set = new HashSet<>(); //String�� String����

        for(int i=0;i<n;i++){
            String str = br.readLine();
            set.add(str);
        }

        List<String> list = new ArrayList<>(set);

        Collections.sort(list, new Comparator<String>(){

            @Override
            public int compare(String o1, String o2) {
                if(o1.length()!=o2.length()){  //���̰� �ٸ��� length�� ��
                    return o1.length()-o2.length();
                }else{  //���̰� ������ ���������� ��
                    return o1.compareTo(o2);
                }
            }
        });

        for(String word:list){
            sb.append(word).append("\n");
        }

        System.out.println(sb);

    }
}

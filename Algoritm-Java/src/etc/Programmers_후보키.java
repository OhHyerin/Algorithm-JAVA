package etc;

import java.util.*;

public class Programmers_�ĺ�Ű {
    //��Ž, ��Ʈ����ŷ
    //���ϼ� -> �ּҼ�

    public static void main(String[] args) {
        String[][] relation = {
                {"100", "ryan", "music", "2"},
                {"200", "apeach", "math", "2"},
                {"300", "tube", "computer", "3"},
                {"400", "con", "computer", "4"},
                {"500", "muzi", "music", "3"},
                {"600", "apeach", "music", "2"}
        };

        System.out.println(solution(relation));

    }

    static public int solution(String[][] relation){
        int answer = 0 ;

        int rowSize = relation.length;
        int colSize = relation[0].length;

        List<Integer> list = new ArrayList<>();  //��Ʈ ���� list

        //�÷� ������ ���ɿͰ��� 4���
        //i�� ó���� 0001
        //i�� 1111�� �� �� ���� �ݺ�
        for(int i=1;i< 1<<colSize ; i++){
            Set<String> hashSet = new HashSet<>();

            //�迭 ��ü Ž��
            for(int r=0;r<rowSize;r++){
                String tmp = "";

                //�迭�� col Ž��
                for(int c=0;c<colSize;c++){
                    if((i&1 << c) > 0){  //���� Ž���ϰ��ִ� �÷��� i�� ���ԵǸ�
                        tmp += relation[r][c];  //�÷� �׳� �� �̾����
                        //ex. ���� ���õ� �÷��� 0,1���� ��
                        //100ryan  <- �̷���
                    }
                }

                hashSet.add(tmp);  //���� �÷��� hashSet�� �־ �ߺ� ����

            }

            //hashSet�� ũ�Ⱑ ��ü row�� ũ��� ������ ���ϼ� ����
            //check���ؼ� �ּҼ��� �����ϸ�
            if(hashSet.size()==rowSize && check(i, list)){
                list.add(i);  //�ش� �÷� ������ list�� �߰�
            }
        }

        answer = list.size();

        return answer;
    }

    static boolean check(int i, List<Integer> list){
        for(int j:list){
            if((i&j)==j) return false;  //i�� j�� ��� ���ԵǾ������� j�� i�� �κ�����
            //���� �ּҼ� ���� ����
        }
        return true;  //�κ����� �ƴ϶�� �ּҼ� ����
    }
}

package string;

public class Programmers_���ڿ����� {
    // ���ڿ�
    // https://programmers.co.kr/learn/courses/30/lessons/60057

    public static void main(String[] args) {
        System.out.println(solution("aabbaccc"));
        System.out.println(solution("ababcdcdababcdcd"));
        System.out.println(solution("abcabcdede"));
        System.out.println(solution("abcabcabcabcdededededede"));
        System.out.println(solution("xababcdcdababcdcd"));
    }

    static int solution(String s){
        int answer = s.length();

        for(int i=1;i<=s.length()/2;i++){  //ũ�Ⱑ 1���� s.length()/2���� �˻�
            int leng = compression(s, i).length();
            answer = Math.min(answer, leng);
        }


        return answer;
    }

    static String compression(String str, int i){

        int count = 1;
        String pattern = "";
        String compression = "";

        for(int j=0;j<=str.length()+i;j+=i){
            String cur;

            //�� ���ڿ��� ���� ���� ���ڿ�
            if(j>=str.length()){  //���� ���ڿ��� ���� ��
                cur="";
            }else if(str.length()<j+i) { //������ ���� ���ڿ��� ��
                cur = str.substring(j);
            }else{
                cur = str.substring(j, j+i);  //�� ��
            }

            //�� ���ڿ��� �Ȱ����� ���Ѵ�. (�� ó������)
            if(j!=0){
                if(cur.equals(pattern)){  // �Ȱ����� count++
                    count++;
                }else if(count>=2){
                    //���ڿ��� �ٸ��� count�� 2 �̻��̸�
                    compression+=count+pattern; //compression�� �߰�
                    count = 1;  //count�� �ٽ� �ʱ�ȭ
                }else{  //���� �Ұ����ϸ� ���ڿ� �׳� ���̱�
                    compression+=pattern;
                }
            }
            pattern = cur;  //i���̸�ŭ ���ڿ� �ڸ���
        }
        return compression;

    }

}

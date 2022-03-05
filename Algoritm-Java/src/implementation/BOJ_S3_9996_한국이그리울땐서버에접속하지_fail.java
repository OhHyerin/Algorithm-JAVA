package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S3_9996_�ѱ��̱׸��ﶩ��������������_fail {
    //���� �ǹ�3
    //�����ǹ���
    //index �񱳷� Ǯ���� ���� - 5%���� Ʋ�Ƚ��ϴ� ����

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        String pattern = br.readLine();
        int patternSize = pattern.length();

        for(int i=0;i<n;i++){
            String str = br.readLine();

            //���Ϻ��� �Է¹��� String�� ª�� ��
            if(patternSize-1>str.length()){
                sb.append("NE").append("\n");
                continue;
            }
            //ù ���� �˻�
            if(str.charAt(0)!=pattern.charAt(0)){
                sb.append("NE").append("\n");
                continue;
            }

            int idx = 1;
            for(int j=1;j<str.length();j++){
                if(pattern.charAt(idx)=='*'){
                    idx++;  //*������ ���� pattern����
                }
                if(idx==patternSize-1){  //pattern������ char��
                    if(j!=str.length()-1) {  //j�� ��������ġ�� �ƴϸ�
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

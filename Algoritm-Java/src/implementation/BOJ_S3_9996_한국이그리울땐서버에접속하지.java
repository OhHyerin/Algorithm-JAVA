package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S3_9996_�ѱ��̱׸��ﶩ�������������� {
    //���� �ǹ�3
    //�����ǹ���

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        String pattern = br.readLine();
        int starIdx = pattern.indexOf("*");
//        System.out.println(starIdx);

        String patternStart = pattern.substring(0, starIdx);  //pattern ó������ * ������
        String patternEnd = pattern.substring(starIdx+1); //pattern *�������� ������

        int patternStartSize = patternStart.length();
        int patternEndSize = patternEnd.length();

        for(int i=0;i<n;i++){
            String str = br.readLine();
            int strSize = str.length();


            if(strSize<pattern.length()-1){  //���ϻ������ String���̰� ª����
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

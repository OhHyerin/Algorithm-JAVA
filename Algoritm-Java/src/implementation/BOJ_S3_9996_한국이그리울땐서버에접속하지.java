package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S3_9996_�ѱ��̱׸��ﶩ�������������� {
    //���� �ǹ�3
    //�����ǹ���

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        String pattern = br.readLine();
        int patterSize = pattern.length();

        for(int i=0;i<n;i++){
            String str = br.readLine();

            int idx = 0;
            for(int j=0;j<str.length();j++){
                if(pattern.charAt(idx)=='*'){
                    idx++;
                    continue;
                }
                if(str.charAt(j)==pattern.charAt(idx)){

                }
            }

        }




    }
}

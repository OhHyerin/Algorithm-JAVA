package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G4_2661_�������� {
    //��Ʈ��ŷ

    static int N;
    static String result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        combi(0, new int[N]);

        System.out.println(result);
    }

    public static void combi(int cnt, int[] selected) {
        if(result!=null) return;
        if (cnt == N) {
            //N�� �� �̾�����
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<N;i++){
                sb.append(selected[i]);
            }
            result = sb.toString();
            return;
        }

        for (int i = 1; i <= 3; i++) {
            selected[cnt] = i;
            if (isBad(cnt, selected)) {
                //�ߺ��Ǵ°� �ִ� ���� �迭�̸�
                continue;  //�ش� ���� ���� ���� ����
            }else{
                //���� �迭�̸�
                combi(cnt+1, selected);  //�������� ������
            }
        }
    }

    public static boolean isBad(int cnt, int[] selected) {
        StringBuilder strSelected = new StringBuilder();
        for (int i = 0; i < cnt + 1; i++) {  //cnt���� selected�� ��ȿ���� �˻�
            strSelected.append(selected[i]);
        }
        String str = strSelected.toString();
//        System.out.println(str);

        for (int i = 1; i <= str.length(); i++) {  //�� ���� ���ڿ��� �˻��Ұ��� (1���� str�� ���̸�ŭ)
            for (int j = 0; j < str.length(); j++) {  //str�� �ִ��� �˻�
                if(i*2 + j > str.length()) break;  //str�� 123�̰� �˻��ұ���*2+�˻��ؾ��ϴ���ġ�� str�� ���̸� �Ѿ�� break

                String str1 = str.substring(j, j+i);  //j���� j+i���� (�˻��� ���̰� i�ϱ�)
                String str2 = str.substring(j+i, j+i*2);  //j+i���� j+i*2����
                if(str1.equals(str2)) return true;  //�ߺ��ǹǷ� ���� �迭
            }
        }

        return false;  //�Ȱ����� ������ ���� �迭
    }
}

package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class BOJ_G5_20437_���ڿ�����2 {

    /*
    1. ���ĺ� �ҹ��ڷ� �̷���� ���ڿ� W�� �־�����.
    2. ���� ���� K�� �־�����.
    3. � ���ڸ� ��Ȯ�� K�� �����ϴ� ���� ª�� ���� ���ڿ��� ���̸� ���Ѵ�.
    4. � ���ڸ� ��Ȯ�� K�� �����ϰ�,
        ���ڿ��� ù ��°�� ������ ���ڰ� �ش� ���ڷ� ���� �� ���� ���ڿ��� ���̸� ���Ѵ�.
     */

    static String W;
    static int K;
    static char[] chars;
    static int[] alpha;
    static HashMap<Character, ArrayList<Integer>> hashMap;
    static int minStrLength = Integer.MAX_VALUE; //���� ª�� ���� ���ڿ� ����
    static int maxStrLength = Integer.MIN_VALUE; //���� �� ���� ���ڿ��� ����
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){

            W = br.readLine();
            K = Integer.parseInt(br.readLine());

            alpha = new int[26];
            chars = new char[W.length()];

            hashMap = new HashMap<>();
            for(int i=0;i<26;i++){
                hashMap.put((char)(i+'a'), new ArrayList<>());  //�� ���ĺ� hashMap�� Key�� �߰�
            }

            for(int i=0;i<W.length();i++){
                hashMap.get(W.charAt(i)).add(i);  //hashMap���� �� Character�� ã�� ����Ʈ�� ��ġ�� value�� ��� �߰�
            }

//            System.out.println(Arrays.toString(chars));


            check();

            if(minStrLength==Integer.MAX_VALUE){
                sb.append("-1\n");
                continue;
            }
            sb.append(minStrLength).append(" ").append(maxStrLength).append("\n");

        }//t
        System.out.println(sb);
    }

    private static void check(){
        for(int i=0;i<26;i++){
            char key = (char)(i+'a');
            ArrayList<Integer> list = hashMap.get(key);  //�� char�� ���ԵǾ��ִ� list(��ġ��) �޾ƿ���

            if(list.size()<K) continue; //����Ǿ��ִ� ��ġ���� ����(list�� ũ��)�� K���� ������ continue;
            for(int j=0;j<list.size();j++){  //list�� ũ�Ⱑ K���� ũ�ų� ������

            }
        }
    }

}

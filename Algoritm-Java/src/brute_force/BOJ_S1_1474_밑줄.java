package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_1474_���� {
    //���� �ǹ�1
    //�׸����� �� �˾Ҵµ� ��Ž���� Ǯ �� �־���.

    static int N, M;
    static String[] word;
    static int len;
    static String result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        word = new String[N*2-1]; //N���� �ܾ�, N-1���� ����

        len = 0;
        for(int i=0;i<N;i++){
            String str = br.readLine();
            word[i*2] = str; //���� �ڸ� ���ܵΰ� ¦����°�� �ܾ� �߰�
            len += str.length(); //�ܾ� ����
        }

        int left_len = (M-len)/(N-1); //�־�ߵǴ� _���� / �ܾ��
//        System.out.println("len : "+len);
//        System.out.println("left_len : "+left_len);
        for(int i=0;i<left_len;i++){
            sb.append("_");  //left_len���̸�ŭ �� �ܾ�� ���̿� ���� �߰�
        }

        for(int i=1;i<N*2-1;i+=2){
            word[i] = sb.toString();
            len += sb.length();  //���� ������ len�� �߰�
        }

        addMore(0, 1);
        System.out.println(result);

    }

    static void addMore(int cnt, int pos){
        //base
        if(cnt==M-len){ //���� ���� ���� �� ä������
            StringBuilder sb = new StringBuilder();
            for(String words : word){
                sb.append(words);
            }
            if(result == null){
                //�켱 ���粨 result�� ����(result�� ������� �� �� �ʱⰪ)
                result = sb.toString();
            }
            else if(result.compareTo(sb.toString())>0){
                //�켱���� �� �� ������ ���̶�� result�� ����
                result = sb.toString();
            }
            return;
        }
        if(pos >= N*2-1) return;
        //inductive
        addMore(cnt, pos+2); //_�߰� ���ϰ� ���� _��ġ��
        word[pos] = word[pos].concat("_");  //concat : ���ڿ� ���̱�
        addMore(cnt+1, pos+2); //_���� 1 �߰��ϰ� ���� _��ġ��
        word[pos] = word[pos].substring(0, word[pos].length()-1); //substring(int i, int j) :  i~j�ܿ� ���ڿ� �ڸ���
    }
}

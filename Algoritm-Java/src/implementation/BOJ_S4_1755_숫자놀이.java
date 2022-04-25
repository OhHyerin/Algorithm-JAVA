package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_S4_1755_���ڳ��� {
    static int M, N;
    //�� �ڸ��� ���� ��ȯ�� String�迭 �̸� ������ֱ�
    static String[] transStr = {"zero", "one", "two", "three", "four", "five", "six", "seven" ,"eight", "nine"};
    static List<Num> list;  //NumŬ������ ���� list

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()); //�� �� �Է¹���
        StringBuilder sb = new StringBuilder();

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        int size = N-M+1;  //M~N������ ũ��

        for(int i=M;i<=N;i++) {  //M���� N������ ���ڸ�
            list.add(new Num(i));  //list�� �߰�
        }

//        System.out.println(list);

        Collections.sort(list);  //Comparable�� �������� ����

        for(int i=0;i<list.size();i++) {  //list ũ�⸸ŭ �ݺ�
            sb.append(list.get(i).num).append(" ");
            if((i+1)%10==0) {  //�� �ٿ� 10�� ��� ��
                sb.append("\n");  //�ٹٲ�
            }
        }

        System.out.println(sb);


    }

    private static class Num implements Comparable<Num>{
        int num;
        String str="";

        public Num(int num) {  //num�� �Է� ���� ��
            super();
            this.num = num;

            if(num>=10) {  //num�� 10���� ũ�� num/10 ���� String���� ��ȯ (10���� ���� ���� "zero"�� ��� ���ϱ�����)
                str = transStr[num/10]+" ";
            }
            str += transStr[num%10];  //num%10���� String���� ��ȯ
        }

        @Override
        public int compareTo(Num o) {
            return str.compareTo(o.str);  //str�� ���������� ����
        }

        @Override
        public String toString() {
            return "Num{" +
                    "num=" + num +
                    ", str='" + str + '\'' +
                    '}';
        }
    }
}

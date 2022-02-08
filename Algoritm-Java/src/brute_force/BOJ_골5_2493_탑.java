package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_��5_2493_ž {
    //���� ���5
    //����
    //1. ������ ����ִٸ� 0�� ����ϰ�, ���� ž�� ���ÿ� push�Ѵ�.
    //2. ������ ������� �ʴٸ�,
    //  2-1. ���ÿ� peek�� ž�� ���̰� ���� ž�� ���̺��� ���ٸ�, peek�� ž�� ��ȣ�� ����ϰ�,
    //          ���� ž�� ���ÿ� push�Ѵ�.
    //  2-2. ���ÿ� peek�� ž�� ���̰� ���� ž�� ���̺��� ���ٸ�, peek�� ž�� pop�ϰ� 2������ �ٽ� ���ư���.


    static int N;
    static Stack<Top> top;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        top = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=1;i<=N;i++){
            int temp = Integer.parseInt(st.nextToken());
            while(!top.isEmpty()){
                if(top.peek().val >= temp){
                    sb.append(top.peek().index).append(" ");
                    break;
                }
                top.pop();
            }
            if(top.isEmpty()){
                sb.append("0 ");
            }
            top.push(new Top(i, temp));

        }

        System.out.println(sb);

    }

    static class Top{
        int index;
        int val;

        Top(int index, int val){
            this.index = index;
            this.val = val;
        }
    }



}

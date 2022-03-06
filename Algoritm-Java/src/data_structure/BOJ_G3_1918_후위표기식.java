package data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_G3_1918_����ǥ��� {
    //���� ���3
    //�����ǹ���
    //����, �켱��������

    /*
    ���ڰ� ������ �״�� ����ϰ�, �����ڰ� ������ ���ÿ� ��Ƽ� ��ȣ�� �켱���� ������ �ϸ鼭 ������ش�.
    1. ��ȣ����
        case1 : '('��ȣ�� ������ �� -> ���ÿ� �״�� ����ش�
        case2 : ')'��ȣ�� ������ �� -> '('��ȣ�� ���� ������ ������ ���ÿ� ��Ƶ� �����ڸ� ��� ������ ����� �� '('��ȣ�� ������� �ʰ� �����ش�.
        case3 : +, -, /, * �����ڰ� ������ ��
                -> ���� �����ں��� ������ ���ÿ� ��Ƶ� �������� �켱������ ���ų� ���ٸ� ���� ������־�� �ϹǷ� ������ ����Ѵ�.
                -> �� �� ������ ���ÿ��� '('�� ���� �� ����
                -> '('�� ������ �� �̻� ������ �ȵǹǷ� '('�� �켱������ ���� ���� �������ش�.
    2. ������ �켱����
        *, / : 2
        +, - : 1
        { : 0
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        String str = br.readLine();
        char[] input = new char[str.length()];

        for(int i=0;i<str.length();i++){
            input[i] = str.charAt(i);  //�Է¹��� str�� char�迭�� �־���
        }

        for(int i=0;i<input.length;i++){
            if(input[i]>='A' && input[i]<='Z'){
                //�ǿ����ڸ� �ٷ� �߰�
                sb.append(input[i]);
            }
            else{
                //�����ڸ�
                if(input[i]=='('){
                    //(�� ���ÿ� push
                    stack.push(input[i]);
                } else if(input[i]==')'){
                    //)�� (�� ���ö����� ���ڿ��� �߰�
                    while(!stack.isEmpty() && stack.peek()!='('){
                        sb.append(stack.pop());
                    }
                    if(!stack.isEmpty()){
                        stack.pop(); // ( ������ ����
                    }
                }
                else{
                    // + - / * �������� ���
                    while(!stack.isEmpty() && priority(stack.peek()) >= priority(input[i])){
                        //�켱������ ������ �ͺ��� pop
                        sb.append(stack.pop());
                    }
                    stack.push(input[i]);
                }
            }
        }

        //input��ŭ �� ������ �� stack�� �Ⱥ�������� ��� ���
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        System.out.println(sb);
    }

    private static int priority(char oper){
        if(oper=='*' || oper=='/') return 2;
        else if(oper=='+' || oper=='-') return 1;
        else return 0;
    }
}

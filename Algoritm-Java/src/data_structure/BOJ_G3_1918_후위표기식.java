package data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_G3_1918_후위표기식 {
    //백준 골드3
    //오늘의문제
    //스택, 우선순위결정

    /*
    숫자가 들어오면 그대로 출력하고, 연산자가 들어오면 스택에 담아서 괄호와 우선순위 연산을 하면서 출력해준다.
    1. 괄호연산
        case1 : '('괄호가 들어왔을 때 -> 스택에 그대로 담아준다
        case2 : ')'괄호가 들어왔을 때 -> '('괄호가 나올 때까지 연산자 스택에 담아둔 연산자를 모두 꺼내어 출력한 후 '('괄호는 출력하지 않고 꺼내준다.
        case3 : +, -, /, * 연산자가 들어왔을 때
                -> 현재 연산자보다 연산자 스택에 담아둔 연산자의 우선순위가 높거나 같다면 먼저 출력해주어야 하므로 꺼내어 출력한다.
                -> 이 때 연산자 스택에는 '('도 들어올 수 있음
                -> '('를 만나면 더 이상 꺼내면 안되므로 '('의 우선순위를 가장 낮게 설정해준다.
    2. 연산자 우선순위
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
            input[i] = str.charAt(i);  //입력받은 str을 char배열에 넣어줌
        }

        for(int i=0;i<input.length;i++){
            if(input[i]>='A' && input[i]<='Z'){
                //피연산자면 바로 추가
                sb.append(input[i]);
            }
            else{
                //연산자면
                if(input[i]=='('){
                    //(면 스택에 push
                    stack.push(input[i]);
                } else if(input[i]==')'){
                    //)면 (이 나올때까지 문자열에 추가
                    while(!stack.isEmpty() && stack.peek()!='('){
                        sb.append(stack.pop());
                    }
                    if(!stack.isEmpty()){
                        stack.pop(); // ( 연산자 꺼냄
                    }
                }
                else{
                    // + - / * 연산자일 경우
                    while(!stack.isEmpty() && priority(stack.peek()) >= priority(input[i])){
                        //우선순위가 먼저인 것부터 pop
                        sb.append(stack.pop());
                    }
                    stack.push(input[i]);
                }
            }
        }

        //input만큼 다 돌았을 때 stack이 안비어있으면 모두 출력
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

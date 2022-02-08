package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_골5_2493_탑 {
    //백준 골드5
    //스택
    //1. 스택이 비어있다면 0을 출력하고, 현재 탑을 스택에 push한다.
    //2. 스택이 비어있지 않다면,
    //  2-1. 스택에 peek한 탑의 높이가 현재 탑의 높이보다 높다면, peek한 탑의 번호를 출력하고,
    //          현재 탑을 스택에 push한다.
    //  2-2. 스택에 peek한 탑의 높이가 현재 탑의 높이보다 낮다면, peek한 탑을 pop하고 2번으로 다시 돌아간다.


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

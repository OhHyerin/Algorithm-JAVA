package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_G5_5639_이진검색트리 {
    //백준 골드5
    //스택으로 풀기

    static StringBuilder sb = new StringBuilder();

    static List<Integer> preorder;
    static List<Integer> postoder;
    static int root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        preorder = new ArrayList<>();
        postoder = new ArrayList<>();

        root = Integer.parseInt(br.readLine());
        while(true){
            String input = br.readLine();
            if(input==null || input.equals("")) break;
            preorder.add(Integer.parseInt(input));
        }

//        System.out.println(preorder);

        postOrder();

//        System.out.println(postoder);
    }

    private static void postOrder(){
        Stack<Integer> stack = new Stack<>();

        stack.push(preorder.get(0));
        int index = 1;
        int cur = stack.peek();
        while(true){
            if(index==preorder.size()){ //index만큼 다 돌았는데
                if(!stack.isEmpty()){  //아직 스택이 안비어있으면
                    while(!stack.isEmpty()){ //스택 빌때까지
                        int temp = stack.pop(); //스택 pop
                        postoder.add(temp); //pop한 값 postorder에 추가
                    }
                    System.out.println("postorder : "+postoder);
                }
                return;
            }
//            if(stack.isEmpty()){
//                stack.push(preorder.get(index));
//            }
            if (cur > preorder.get(index)) {
                //스택의 현재 값보다 작으면 push
                stack.push(preorder.get(index));
//                System.out.println("작을 때 " + stack);
            }
            else{
                //스택의 현재 값보다 크면
                int temp = stack.pop();
                postoder.add(temp);  //스택 현재 값 push -> postorder list에 추가
                stack.push(preorder.get(index)); //들어온 값 스택에 push
                //스택에 남아있는 수 중 현재 들어온 값보다 클때까지 pop
                while(preorder.get(index)>cur){
                    cur = stack.pop();
                    postoder.add(cur);
                }
//                System.out.println("클 때 " + stack);
            }
            if(stack.isEmpty()){ //스택이 비어있으면 다음께 무조건 push되어야하니까
                cur = Integer.MAX_VALUE; //cur값을 MAX로
            } else{
                cur = stack.peek();
            }

            System.out.println("index : " +index);
            index++;

            System.out.println("전체 스택 "+stack);


        }
    }


}

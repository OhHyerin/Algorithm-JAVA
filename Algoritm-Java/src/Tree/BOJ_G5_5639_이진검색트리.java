package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_G5_5639_�����˻�Ʈ�� {
    //���� ���5
    //�������� Ǯ��

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
            if(index==preorder.size()){ //index��ŭ �� ���Ҵµ�
                if(!stack.isEmpty()){  //���� ������ �Ⱥ��������
                    while(!stack.isEmpty()){ //���� ��������
                        int temp = stack.pop(); //���� pop
                        postoder.add(temp); //pop�� �� postorder�� �߰�
                    }
                    System.out.println("postorder : "+postoder);
                }
                return;
            }
//            if(stack.isEmpty()){
//                stack.push(preorder.get(index));
//            }
            if (cur > preorder.get(index)) {
                //������ ���� ������ ������ push
                stack.push(preorder.get(index));
//                System.out.println("���� �� " + stack);
            }
            else{
                //������ ���� ������ ũ��
                int temp = stack.pop();
                postoder.add(temp);  //���� ���� �� push -> postorder list�� �߰�
                stack.push(preorder.get(index)); //���� �� ���ÿ� push
                //���ÿ� �����ִ� �� �� ���� ���� ������ Ŭ������ pop
                while(preorder.get(index)>cur){
                    cur = stack.pop();
                    postoder.add(cur);
                }
//                System.out.println("Ŭ �� " + stack);
            }
            if(stack.isEmpty()){ //������ ��������� ������ ������ push�Ǿ���ϴϱ�
                cur = Integer.MAX_VALUE; //cur���� MAX��
            } else{
                cur = stack.peek();
            }

            System.out.println("index : " +index);
            index++;

            System.out.println("��ü ���� "+stack);


        }
    }


}

package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_5639_이진검색트리_tree구현 {
    //백준 골드5
    //트리 구현해서 푸는 방법
    //입력받는 방법 기억하기 (정해진 테케가 없음)


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Node root = new Node(Integer.parseInt(br.readLine()));

        //이런 입력값은 while(br.ready())

        while (true) {
            String input = (br.readLine());
            if (input == null || input.equals(""))
                break;
            root.insert(Integer.parseInt(input));
        }

        postOrder(root);  //생성된 완전이진트리를 후위순회로 변경

    }

    private static void postOrder(Node node){
        if (node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.num);
    }

    static class Node {
        int num;
        Node left, right;

        public Node(int index) {
            this.num = index;
        }

        public Node(int num, Node left, Node right) {
            this.num = num;
            this.left = left;
            this.right = right;
        }

        void insert(int n) {
            if (n < this.num) { //새로 들어올 값이 현재 노드보다 작으면
                if (this.left == null)  //현재 왼쪽자식노드가 비어있으면
                    this.left = new Node(n);  //자신의 왼쪽 자식 노드에 값 삽입
                else //왼쪽 자식 노드가 차있으면
                    this.left.insert(n); //자신의 자리에 새로 들어올 값을 넣고
                //자신으로부터 새로운 트리 생성
            } else { //새로 들어올 값이 현재 노드보다 크면
                if (this.right == null) {  //오른쪽 자식 노드가 비어있으면
                    this.right = new Node(n);  //오른쪽 자식 노드에 삽입
                } else  //오른쪽 노드가 차있으면
                    this.right.insert(n); //자신의 자리에 새로 들어올 값을 넣고
                //자신으로부터 새로운 트리 생성
            }
        }
    }
}

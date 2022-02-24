package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_5639_�����˻�Ʈ��_tree���� {
    //���� ���5
    //Ʈ�� �����ؼ� Ǫ�� ���
    //�Է¹޴� ��� ����ϱ� (������ ���ɰ� ����)


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Node root = new Node(Integer.parseInt(br.readLine()));

        //�̷� �Է°��� while(br.ready())

        while (true) {
            String input = (br.readLine());
            if (input == null || input.equals(""))
                break;
            root.insert(Integer.parseInt(input));
        }

        postOrder(root);  //������ ��������Ʈ���� ������ȸ�� ����

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
            if (n < this.num) { //���� ���� ���� ���� ��庸�� ������
                if (this.left == null)  //���� �����ڽĳ�尡 ���������
                    this.left = new Node(n);  //�ڽ��� ���� �ڽ� ��忡 �� ����
                else //���� �ڽ� ��尡 ��������
                    this.left.insert(n); //�ڽ��� �ڸ��� ���� ���� ���� �ְ�
                //�ڽ����κ��� ���ο� Ʈ�� ����
            } else { //���� ���� ���� ���� ��庸�� ũ��
                if (this.right == null) {  //������ �ڽ� ��尡 ���������
                    this.right = new Node(n);  //������ �ڽ� ��忡 ����
                } else  //������ ��尡 ��������
                    this.right.insert(n); //�ڽ��� �ڸ��� ���� ���� ���� �ְ�
                //�ڽ����κ��� ���ο� Ʈ�� ����
            }
        }
    }
}

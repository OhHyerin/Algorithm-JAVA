//package Tree;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.StringTokenizer;
//
//public class BOJ_S1_1991_Ʈ����ȸ {
//    //���� �ǹ�1
//    //�־��� Ʈ���� ����, ����, ���� ��ȸ�ϴ� ���
//
//    static class Node{
//        Node left;
//        Node right;
//
//        public Node(Node left, Node right) {
//            this.left = left;
//            this.right = right;
//        }
//
//    }
//
//    static Node root;
//    static StringBuilder sb = new StringBuilder();
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//
//
//        int N = Integer.parseInt(br.readLine());
//
//        ArrayList<Node>[] adjList = new ArrayList[N];
//        for(int i=0;i<N;i++){
//            adjList[i] = new ArrayList<>();
//        }
//
//        for(int i=0;i<N;i++){
//            String[] str = br.readLine().split(" ");
//            char p = str[0].charAt(0);
//            char l = str[1].charAt(0);
//            char r = str[2].charAt(0);
//            adjList[p].add(new Node(l, r));
//        }
//    }
//
//    //---------------------��� �߰��ϸ鼭 Ʈ�� ����------------------
//    private void add(char data, char left, char right){
//        if(root==null){
//            //root�� null�̸�
//            //.�� �ƴϸ� root�� parent���� ���� ��� ����
//            if(data!='.') root = new Node(data);
//            //���� �ڽ� ��尡 .�� �ƴϸ� ���� �ڽ� ��� ����
//            if(left!='.') root.left = new Node(left);
//            //������ �ڽ� ��尡 .�� �ƴϸ� ������ �ڽ� ��� ����
//            if(right !='.') root.right = new Node(right);
//        }
//        //root�� null�� �ƴ϶�� Ž��
//        else search(root, data, left, right);
//    }
//
//    private void search(Node root, char data, char left, char right){
//        //���� ��Ʈ��尡 null�̸� ����
//        if(root==null) return;
//
//        //��Ʈ����� �����Ͱ� data���
//        else if(root.c==data){
//            if(left!='.') root.left = new Node(left);
//            if(right!='.') root.right = new Node(right);
//        }
//        //��ã�Ҵٸ�? �ƴ϶��?
//        else{
//            search(root.left, data, left, right);
//            search(root.right, data, left, right);
//        }
//    }
//    //------------------------------------------------------------------
//
//    private static void preOrder(Node root){
//        //������ȸ
//        //��Ʈ -> ���� -> ������
//        sb.append(root.c);
//        if(root.left!=null) preOrder(root.left);
//        if(root.right!=null) preOrder(root.right);
//    }
//
//    private static void inOrder(Node root){
//        //������ȸ
//        //���� -> ��Ʈ -> ������
//        if(root.left!=null) inOrder(root.left);
//        sb.append(root.c);
//        if(root.right!=null) inOrder(root.right);
//    }
//
//    private static void postOrder(Node root){
//        //������ȸ
//        //���� -> ������ -> ��Ʈ
//        if(root.left!=null) inOrder(root.left);
//        if(root.right!=null) inOrder(root.right);
//        sb.append(root.c);
//    }
//}

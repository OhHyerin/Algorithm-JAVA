//package Tree;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.StringTokenizer;
//
//public class BOJ_S1_1991_트리순회 {
//    //백준 실버1
//    //주어진 트리를 전위, 중위, 후위 순회하는 방법
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
//    //---------------------노드 추가하면서 트리 생성------------------
//    private void add(char data, char left, char right){
//        if(root==null){
//            //root가 null이면
//            //.이 아니면 root에 parent값을 가진 노드 생성
//            if(data!='.') root = new Node(data);
//            //왼쪽 자식 노드가 .이 아니면 왼쪽 자식 노드 생성
//            if(left!='.') root.left = new Node(left);
//            //오른쪽 자식 노드가 .이 아니면 오르쪽 자식 노드 생성
//            if(right !='.') root.right = new Node(right);
//        }
//        //root가 null이 아니라면 탐색
//        else search(root, data, left, right);
//    }
//
//    private void search(Node root, char data, char left, char right){
//        //만약 루트노드가 null이면 종료
//        if(root==null) return;
//
//        //루트노드의 데이터가 data라면
//        else if(root.c==data){
//            if(left!='.') root.left = new Node(left);
//            if(right!='.') root.right = new Node(right);
//        }
//        //못찾았다면? 아니라면?
//        else{
//            search(root.left, data, left, right);
//            search(root.right, data, left, right);
//        }
//    }
//    //------------------------------------------------------------------
//
//    private static void preOrder(Node root){
//        //전위순회
//        //루트 -> 왼쪽 -> 오른쪽
//        sb.append(root.c);
//        if(root.left!=null) preOrder(root.left);
//        if(root.right!=null) preOrder(root.right);
//    }
//
//    private static void inOrder(Node root){
//        //중위순회
//        //왼쪽 -> 루트 -> 오른쪽
//        if(root.left!=null) inOrder(root.left);
//        sb.append(root.c);
//        if(root.right!=null) inOrder(root.right);
//    }
//
//    private static void postOrder(Node root){
//        //후위순회
//        //왼쪽 -> 오른쪽 -> 루트
//        if(root.left!=null) inOrder(root.left);
//        if(root.right!=null) inOrder(root.right);
//        sb.append(root.c);
//    }
//}

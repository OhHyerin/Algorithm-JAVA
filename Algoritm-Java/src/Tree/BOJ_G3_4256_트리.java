package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G3_4256_트리 {
    //백준 골드3
    //이진트리

    //전위순회(preorder) : root, 왼쪽, 오른쪽
    //중위순회(inorder) : 왼쪽, root, 오른쪽
    //후위순회(postorder) : 왼쪽, 오른쪽, root

    static int T;
    static int n;
    static int[] preorder;
    static int[] inorder;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++){
            n = Integer.parseInt(br.readLine());
            preorder = new int[n];
            inorder = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<n;i++){
                preorder[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i=0;i<n;i++){
                inorder[i] = Integer.parseInt(st.nextToken());
            }
            //-------------------입력완료--------------------
            postorder(0, 0, n);
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
    static void postorder(int root, int start, int end){
        for(int i=start;i<end;i++){
            //시작부터 끝까지
            if(preorder[root]==inorder[i]){
                //중위순회를하면서 루트노드를만나면(부모노드)
//                System.out.print(" root : "+root);
//                System.out.println(" i : "+i);
                postorder(root+1, start, i);
                postorder(root+i-start+1, i+1, end);
                sb.append(preorder[root]+" ");
            }
        }
    }




}

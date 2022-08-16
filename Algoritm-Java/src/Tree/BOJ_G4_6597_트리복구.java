package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_6597_트리복구 {
    //트리, 그래프탐색, dfs
    // BOJ G3 4256 트리 문제랑 동일

    /*
    예제 :
    전위순회(pre) : D B A C E G F
    중위순회(in)  : A B C D E F G

    BAC D EGF

     */

    static char[] preorder;
    static char[] inorder;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            String str = br.readLine();
            if(str==null) break;

            st = new StringTokenizer(str);
            String str1 = st.nextToken();
            String str2 = st.nextToken();

            int size = str1.length();

            preorder = new char[size];
            inorder = new char[size];

            preorder = str1.toCharArray();
            inorder = str2.toCharArray();

            makePostorder(0, 0, size);
            sb.append("\n");

        }

        System.out.println(sb);


    }

    private static void makePostorder(int root, int start, int end){
        for(int i = start; i<end ; i++){
            //start부터 end까지 검사
            if(preorder[root]==inorder[i]){
                //inorder에서 parent노드를 만나면 그 기점으로 왼쪽 오른쪽 나눔
                makePostorder(root+1, start, i);
                makePostorder(root+i-start+1, i+1, end);
                sb.append(preorder[root]);
            }
        }
    }
}

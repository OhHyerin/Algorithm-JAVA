package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_6597_Ʈ������ {
    //Ʈ��, �׷���Ž��, dfs
    // BOJ G3 4256 Ʈ�� ������ ����

    /*
    ���� :
    ������ȸ(pre) : D B A C E G F
    ������ȸ(in)  : A B C D E F G

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
            //start���� end���� �˻�
            if(preorder[root]==inorder[i]){
                //inorder���� parent��带 ������ �� �������� ���� ������ ����
                makePostorder(root+1, start, i);
                makePostorder(root+i-start+1, i+1, end);
                sb.append(preorder[root]);
            }
        }
    }
}

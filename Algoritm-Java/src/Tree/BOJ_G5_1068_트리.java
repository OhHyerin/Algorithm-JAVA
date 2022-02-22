package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_1068_Ʈ�� {
    //���� ���5
    //��������� ���� ã�� (�θ��带 ����� �ڽĳ�� ��� remove)

    static int n; //n:����� ����
    static ArrayList<Integer>[] tree; //�� parent�κ��� ����� �ڽĳ��
//    static int[] leaf;  //leaf ��� ������ �迭
    //leaf��带 ���� ������ �ʿ�� ����
    static int r; //r:���� ����� ��ȣ
    static int root; //root��� ��ȣ
    static int count; //answer

    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n];  //��� ������ŭ ����
//        leaf = new int[n];

        //tree setting
        for(int i=0;i<n;i++) {
            tree[i] = new ArrayList<>();  //��帶�� list����
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            int parent = Integer.parseInt(st.nextToken());
            if(parent==-1){  //�Է°��� -1�̸� ��Ʈ���
                root = i; //root������ �ε��� ����
            }
            else{
                tree[parent].add(i); //�� ����� �ڽĵ� ����
            }
        }
        r = Integer.parseInt(br.readLine());
        //---------------------�Է� �Ϸ�-------------------
        remove(r);  //��ü ��ȸ �� r ������ leaf������ ����

        //��Ʈ��尡 ������ ����� �� 1�� �ƴ� 0�� ���;� ��
        if(r==root){
            System.out.println(0);
        } else{
            countLeaf();
            System.out.println(count);
        }
//        countLeaf();
//        System.out.println(count);

    }
    static void remove(int node){
        //tree ��ȸ
        if(tree[node].size()>0){
            //������尡 �ƴϸ�
            int size = tree[node].size();
            while(size>0){
                //leaf������ �ݺ�
                remove(tree[node].get(--size)); //size�� �����ϸ鼭 leaf��� ã��
            }
        }

        //tree���� ����
        for(int i=0;i<n;i++) {
            if (tree[i].contains(node)) {
                //����Ʈ �ȿ� node�� ���ԵǾ�������
                for (int j = 0; j < tree[i].size(); j++) {
                    //leaf������ �ݺ�
                    if (tree[i].get(j) == node) {
                        tree[i].remove(j); //��� ����
                    }
                }
            }
        }
    }

    static void countLeaf(){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);  //queue�� ��Ʈ ��� �߰�

        while(!queue.isEmpty()){
            //ť�� �� �� ���� bfs
            int cur = queue.poll();
            if(tree[cur].size()==0){
                //��������
                count++;
            } else{
                for(int next:tree[cur]){
                    queue.add(next);
                }
            }
        }

    }


}

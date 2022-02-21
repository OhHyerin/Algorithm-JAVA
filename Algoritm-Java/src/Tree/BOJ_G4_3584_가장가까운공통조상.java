package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_G4_3584_���尡���������� {
    //���� ���4
    //��������Ʈ�� X

    //��Ʈ��尡 ������ ���� �ʱ� ������ �Ʒ����� ���� �ö󰡸鼭 ��Ʈ��带 ã�ƾ� ��
    //�� ������ ���̸� ���� �ϰ� �ϳ��� �ö󰡸鼭 ���� ������ ���ö����� �ݺ���

   static int n; //n:����� ��
    static LinkedList<Integer>[] list;
    static int[] parent;
    static int[] depth;
    static boolean[] vertex;
    static int findA, findB;  //������ ã�ƾ� �ϴ� �� ���

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            n = Integer.parseInt(br.readLine());

            list = new LinkedList[n+1]; //������ ����Ǿ��ִ� ��������Ʈ
            parent = new int[n+1];
            depth = new int[n+1];
            vertex = new boolean[n+1];  //�θ� ������ true, ������ false

            //���� ����Ʈ ����
            for(int i=1;i<=n;i++){
                list[i] = new LinkedList<Integer>();
            }

            for(int i=1;i<n;i++){
                st = new StringTokenizer(br.readLine());
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());

                vertex[child] = true;

                list[parent].add(child);
                list[child].add(parent);  //��������� ��������Ʈ ����
            }

            //��Ʈ ��� ã��
            int root = 0;
            for(int i=1;i<=n;i++){
                //��������Ʈ ��� ���鼭
                if(vertex[i]==false){
                    root = i;
                }
            }

            //����θ� ã�� �� ���
            st = new StringTokenizer(br.readLine());
            findA = Integer.parseInt(st.nextToken());
            findB = Integer.parseInt(st.nextToken());

            //dfs
            //�� ��� ���̿� �θ��� �迭�� ����
            dfs(root, 0, -1);
            find();
            sb.append(findA).append("\n");

        }//t
        System.out.println(sb);
    }

    static void dfs(int cur, int d, int p){
        depth[cur] = d;
        parent[cur] = p;

        for(int next:list[cur]){
            if(next != p){
                dfs(next, d+1, cur);
            }
        }
    }

    static void find(){
        int depthA = depth[findA];
        int depthB = depth[findB];

        //�� ����� ���̸� ���� ���߱�
        while(depthA>depthB){
            findA = parent[findA];
            depthA--;
        }

        while(depthB>depthA){
            findB = parent[findB];
            depthB--;
        }

        //���� depth���� ���� �ö󰡸� ���� �θ� ��� ã��
        while(findA != findB){
            findA = parent[findA];
            findB = parent[findB];
        }
    }

}

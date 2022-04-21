package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_G5_13023_ABCDE {
    //dfs

    static int N;  //����� ��
    static int M;  //ģ�� ������ ��
    static ArrayList<Integer>[] list;  //��������Ʈ
    static boolean isPossible;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N];
        for(int i=0;i<N;i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);  //�ֹ������� ����
            list[b].add(a);
        }

//        for(int i=0;i<N;i++){
//            System.out.print(i+" : ");
//            System.out.println(list[i]);
//        }

        outer : for(int i=0;i<N;i++){
            if(list[i].size()==0) continue ;
            for(int j=0;j<list[i].size();j++){
                dfs(i, new boolean[N], 1);  //����Ʈ�� ��� �� Ž��
                if(isPossible){  //�߰��� ������ ��� ������
                    break outer;  //Ż��
                }
            }
        }

        System.out.println(isPossible? 1:0);


    }

    private static void dfs(int cur, boolean[] visited, int depth){
        if(isPossible) return;

        if(depth==5){  //5���� ����Ǿ�������
            isPossible = true;  
            return;  //Ż��
        }

        visited[cur] = true;  //�ڱ� �ڽ� �湮 ó��

        for(int i=0;i<list[cur].size();i++){  //������ ����Ʈ ��� ���鼭
            int next = list[cur].get(i);

            if(!visited[next]){  //���� �湮���� ���� ���� ������
                dfs(next, visited, depth+1);  //depth+1 �� dfs���
            }
        }
        visited[cur] = false;  //���� dfs�� ���� �Ȱ����� �ʱ�ȭ
    }
}

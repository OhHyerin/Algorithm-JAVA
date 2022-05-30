package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G5_13265_��ĥ�ϱ� {
    //�׷���Ž��, dfs
    //color, visited �迭 ���� ������ �� Ʋ�Ƚ��ϴ�.

    static int N;  //���׶�� ����
    static int M;  //������ ����
    static int[] color;
//    static boolean[] color;
//    static boolean[] visited;
    static List<Integer>[] list;
    static boolean isPossible;

   public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

//            color = new boolean[N+1];
//            visited = new boolean[N+1];
            color = new int[N+1];
            list = new List[N+1];
            isPossible = true;

            for(int i=0;i<list.length;i++){
                list[i] = new ArrayList<>();
            }

            for(int i=0;i<M;i++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                //����� ����
                list[from].add(to);
                list[to].add(from);
            }

//            for(int i=0;i<M;i++){
//                System.out.println(list[i]);
//            }
//            System.out.println("-----------------------");

            for(int i=1;i<=N;i++){
//                if(!visited[i]){
                if(color[i]==0){
//                    color[i] = true;
                    color[i] = 1;
//                    visited[i] = true;
                    dfs(i);
                }
            }

            if(isPossible) sb.append("possible\n");
            else sb.append("impossible\n");


        }

       System.out.println(sb);

    }


    private static void dfs(int depth){
        for(int i=0;i<list[depth].size();i++){
            int cur = list[depth].get(i);  //�� ��忡 ����Ǿ��ִ� ���


//            if((color[cur]&&color[depth]) || (!color[cur] && !color[depth])){  //���� ��尡 depth���� ������ ���� �Ȱ�����
            if(color[cur]==color[depth]){  //���� ��尡 depth���� ������ ���� �Ȱ�����
                //���� �Ȱ����� �ȵ�
//                System.out.println("cur : "+cur+",   depth : "+depth);
//                System.out.println("--------------");

                isPossible = false;  //�Ұ��� ǥ��
                return;
//            }else if(!visited[cur]){
            }else if(color[cur]==0){
                //���� �湮 ���Ѱ��̸�
//                color[cur] = !color[depth];  //depth�� �ݴ��� ���� �־���
                color[cur] = color[depth]*-1;  //depth�� �ݴ��� ���� �־���
//                visited[cur] = true;
                dfs(cur);
            }
        }
   }
}

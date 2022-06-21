package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G4_19538_��� {
    //�׷���Ž��

    /*
    ���� ����
    - ���� �����ڴ� �������� �� �ִ�.
    - ���� �����ڸ� �����ϰ� ������ ��Ӹ� ����� �ϴ� ����� ����
    - �ź� ��Ӹ� �ϴ� ����� ��� �ֺ��ο��� ��Ӹ� ���ÿ� ��Ʈ����.
    - ���� �� ����� �ֺ����� ���� �̻��� ��Ӹ� ���� �� ���ε� ��Ӹ� �ϴ´�.
    - �ѹ� ���� ��Ӵ� ��� �ϴ´�.
     */

    static int N;  //����� ��
    static int M;  //���� �������� ��
    static ArrayList<Integer>[] list;
    static Queue<Integer> queue;
    static int[] neighbours;
    static int[] depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        list = new ArrayList[N+1];
        queue = new LinkedList<>();
        depth = new int[N+1];  //answer ����
        Arrays.fill(depth, -1); //ó�� �����ڴ� 0, ������ ���� �ȵǸ� -1
        neighbours = new int[N+1];

        for(int i=1;i<=N;i++){
            list[i] = new ArrayList<>();
        }

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            while(true){
                int n = Integer.parseInt(st.nextToken());
                if(n==0) break;
                list[i].add(n);  //�Է¿��� �˾Ƽ� ��������� �־����� ������ ���⼱ �ܹ������� �����ص� ��
            }
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++){
            int a = Integer.parseInt(st.nextToken());
            queue.add(a);
            depth[a] = 0;
        }

        //---------------�Է¿Ϸ�--------------------

        bfs();

        for(int i=1;i<=N;i++){
            System.out.print(depth[i]+" ");
        }

    }

    private static void bfs(){
//        boolean[] visited = new boolean[N+1];
        while(!queue.isEmpty()){
            int cur = queue.poll();

            for(int i=0;i<list[cur].size();i++){
                //cur�� ����Ǿ��ִ� ��� ���ε鿡�� ����
                int next = list[cur].get(i);  //cur�� ����Ǿ��ִ� ����

                neighbours[next] ++;  //���� ������� ��� ����

                if(depth[next]==-1 && neighbours[next] >= ((list[next].size()+1)/2)) {  //���� �����Ǵ°Ű�, �ֺ������ �� �̻��� ��Ӹ� ���� ��
//                    visited[next] = true;  //depth�� ��� ����
                    queue.add(next);
                    depth[next] = depth[cur]+1;
                }
            }
        }
    }

}

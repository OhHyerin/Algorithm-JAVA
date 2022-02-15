package data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G1_20304_��й�ȣ���� {
    // ���� ���1
    // ��Ʈ������ + BFS
    // ��Ʈ�����ڷ� ��Ǯ�� �ð��ʰ�

    static int N;
    static int M;
    static int[] attacks;
    static boolean[] visited;
    static Queue<Integer> queue;
    static int depth = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        attacks = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++){
            attacks[i] = Integer.parseInt(st.nextToken());
        }

        queue = new LinkedList<>();
        //�ش� ���ڵ��� ���� �Ǿ����� Ȯ��, �ִ� �Ÿ��� ���ϸ� �ǹǷ� boolean����
        visited = new boolean[N+1];

        //�ʱ� ť ���� �� �湮 ó�� - ���������� ã�ƾ� �ϴ� ��й�ȣ�� o���̰� ���� ��
        //���� 1�� �־����� �� ã��
        for(int m=0;m<M;m++){
            queue.offer(attacks[m]);
            visited[attacks[m]] = true;
        }

        while(!queue.isEmpty()){
            bfs();
        }
        depth--;
        System.out.println(depth);

    }

    private static void bfs(){
        int size = queue.size();
        while(size --> 0){
            int front = queue.poll();
            //front�� 1��Ʈ ���� ���� ���� ã�´�
            //0000000001�� ������ ��ĭ�� shift�ϸ鼭 ���� ���ڿ� ��Ī
            for(int i=1;i<=N;i<<=1){
                //front�� 1��Ʈ�� �ٸ� ���� �����
                //front�� i�� ����ִٸ� ���ְ� ���ٸ� �ֱ�
                int next;

                /*
                if((front&i)>0){
                next = frint & ~i;
                //next = front-i;
                } else{
                next = front | i;
                //next = front+i;
                }
                 */

                next = front^i;

                //���ο� ���� ���� �ȿ� �ְ� ���� �̹湮�� �����̶�� �湮ó��
                if(next <= N && !visited[next]){
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
        depth++;
    }

}

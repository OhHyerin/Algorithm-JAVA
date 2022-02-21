package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_S1_6118_���ٲ��� {
    //���� �ǹ�1
    //���ٲ��� �ø���
    //�׷��� Ž��?

    static int n, m; //n:����, m:����
    static ArrayList<Integer> list[];
    static boolean[] visited;
    static int index = 2;
    static int max = Integer.MIN_VALUE;
    static int count = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+1];
        visited = new boolean[n+1];

        for(int i=0;i<n+1;i++){
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            list[from].add(to);
            list[to].add(from);  //������̴ϱ� ���� ���� �� �־��ֱ�
        }

        bfs();

        System.out.println(index+" "+max+" "+count);
    }

    static void bfs(){
       Queue<int[]> queue = new LinkedList<>();
       visited[1] = true;
       queue.offer(new int[]{1, 0});

       while(!queue.isEmpty()){
           int arr[] = queue.poll();
           int from = arr[0];
           int to = arr[1];

           if(to>max){
               //���� max���� ũ��
               max = to; //max �ʱ�ȭ
               index = from; //�ε����� from���� ����
               count = 1;  //�ִ񰪺��� �� ū ���� ������ count�� 1�� �ʱ�ȭ
           } else if(to==max){
               //���� max���� ������ ����������
               if(index>from) index = from;
               count++;  //���� max�� ���� ���� ����
           }
           for(int i=0;i<list[from].size();i++){
               int to_to = list[from].get(i);
               if(!visited[to_to]){
                   visited[to_to] = true;
                   queue.offer(new int[]{to_to, to+1});
               }
           }
       }


    }


}

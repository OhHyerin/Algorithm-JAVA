package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_G5_2668_���ڰ��� {
    //�׷���Ž��, dfs

    static int N;
    static int[] arr;
    static List<Integer> list;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        list = new ArrayList<>();

        for(int i=1;i<=N;i++){
            visited = new boolean[N+1];
            visited[i] = true;
            /*
            dfs(1,1)�� �� list�� 3�� �߰�
            dfs(3,3)�� �� list�� 1�� �߰�
             */
            dfs(i,i);
        }

        Collections.sort(list);  //���� ������ ����ϵ��� list �������� ����

        sb.append(list.size()).append("\n");
        for(int i=0;i<list.size();i++){
            sb.append(list.get(i)).append("\n");
        }

        System.out.println(sb);


    }

    private static void dfs(int index, int start){
        if(arr[index]==start){
            //cycle �߻�!
            //���� ���� ���� ó�� ������ index�� ���ٸ� list�� �߰�
            //�ڽŰ� ����Ǿ��ִ� �ٷ� ������ index�� �߰���
            //(for���� ��ü������ ���⶧���� ���������� �� �� ����!)
            list.add(index);
        }
        if(!visited[arr[index]]){
            visited[arr[index]] = true;
            dfs(arr[index], start);
        }

    }
}

package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G5_15591_MooTube {
    //���� ���5
    //���͵� - ������ ���ױ�Ż

    //������ �����ϴµ� �����ɸ�
    //MST�� �����ϰ�
    //�־��� k,v -> v���� �����ؼ� ��������� k���� ũ�� ����(count++)

    //union-find�� �����Ϸ��ߴٰ�
    //��θ� ���ؾ��ؼ� Video����Ʈ �迭�� ����

    static int N, Q;
    static List<Video>[] list;
    static int[][] question;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        list = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        question = new int[Q][2];


        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            list[a].add(new Video(b, d));
            list[b].add(new Video(a, d)); //����� ����
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken()); //���絵 K
            int v = Integer.parseInt(st.nextToken()); //������ V

            question[i][0] = k;
            question[i][1] = v;
        }

        for (int i = 0; i < Q; i++) {
            sb.append(bfs(question[i][0], question[i][1])).append("\n");
        }

        System.out.println(sb);

    }

    private static int bfs(int k, int v) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        int count = 0;

        queue.add(v);  //������ v
        visited[v] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();


            for(Video next : list[cur]){
                if(!visited[next.to] && next.distance<k){
                    visited[next.to] = true;
                    count++;
                    queue.add(next.to);
                }
            }
        }
        return count;
    }


    private static class Video {
        int to;
        int distance;

        public Video(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }

    }


}

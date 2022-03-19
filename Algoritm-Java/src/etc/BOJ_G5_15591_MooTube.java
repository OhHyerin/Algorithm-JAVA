package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G5_15591_MooTube {
    //백준 골드5
    //스터디 - 문제집 코테광탈

    //문제를 이해하는데 오래걸림
    //주어진 k,v -> v에서 시작해서 간선비용이 k보다 크면 진출(count++)

    //union-find로 구축하려했다가
    //경로를 구해야해서 Video리스트 배열로 변경

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

        question = new int[Q][2];  //k, v를 저장할 question 배열


        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            list[a].add(new Video(b, d));
            list[b].add(new Video(a, d)); //양방향 연결
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken()); //유사도 K
            int v = Integer.parseInt(st.nextToken()); //시작점 V

            question[i][0] = k;
            question[i][1] = v;
        }

        for (int i = 0; i < Q; i++) {
            sb.append(bfs(question[i][0], question[i][1])).append("\n");
        }

        System.out.println(sb);

    }

    private static int bfs(int k, int v) {
        Queue<Video> queue = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        int min;
        int count = 0;

        queue.add(new Video(v, Integer.MAX_VALUE));

        while(!queue.isEmpty()){
            Video cur = queue.poll();  //현재 비디오
            visited[cur.to] = true;    //방문처리
            for(Video next : list[cur.to]){ //현재 비디오의 모든 경로
                if(!visited[next.to]){
                    min = Math.min(cur.distance,next.distance); //둘 연관도 중 최솟값
                    if(min>=k){  //그 최솟값이 k보다 크면 이동
                        queue.add(new Video(next.to, min));
                        count++;
                        visited[next.to] = true;
                    }
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

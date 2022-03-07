package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_12851_숨바꼭질2 {
    //백준 골드5
    //bfs  //dr,dc대신 갈 수 있는 방법 3가지 경우 다 찾아보기

    static int N, K;
    static int[] time;
    static int INF = 100001;
    static int minTime = INF;
    static int count = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfs();

        System.out.println(minTime);
        System.out.println(count);

    }

    private static void bfs() {
        Queue<Pos> queue = new LinkedList<>();
        boolean[] visited = new boolean[INF];

        queue.add(new Pos(N, 0));
        visited[N] = true;

        while (!queue.isEmpty()) {
            Pos cur = queue.poll();
            visited[cur.pos] = true;

            if (cur.pos == K) {
                if (minTime > cur.sec) {
                    minTime = cur.sec;
                } else if (minTime == cur.sec) {
                    count++;
                }
            }

            int nextP = cur.pos + 1;
            int nextM = cur.pos - 1;
            int nextD = cur.pos * 2;

            if (isIn(nextP) && !visited[nextP]) {
                queue.add(new Pos(nextP, cur.sec + 1));
            }
            if (isIn(nextM) && !visited[nextM]) {
                queue.add(new Pos(nextM, cur.sec + 1));
            }
            if (isIn(nextD) && !visited[nextD]) {
                queue.add(new Pos(nextD, cur.sec + 1));
            }

        }
    }

    private static boolean isIn(int pos) {
        return pos >= 0 && pos < INF;
    }

    private static class Pos {
        int pos;
        int sec;

        public Pos(int pos, int sec) {
            this.pos = pos;
            this.sec = sec;
        }
    }
}

package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_13549_숨바꼭질3 {
    //백준 (골드5)

    //큐에 새로운 노드를 넣을 때 마다
    //노드들을 비용기준 오름차순으로 정렬하고,
    //가장 비용이 적은 노드를 꺼내며 최단거리를 갱신한다.
    static int n, k;
    static int[] time;
    static int MAX_POS = 100001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //n : 수빈이가 있는 위치
        n = Integer.parseInt(st.nextToken());
        //k : 동생이 있는 위치
        k = Integer.parseInt(st.nextToken());
        time = new int[MAX_POS];
        dijkstra();
        System.out.println(time[k]);

    }

    static void dijkstra(){
        PriorityQueue<Node> queue= new PriorityQueue<>();
        Arrays.fill(time, MAX_POS);
        time[n] = 0;

        queue.add(new Node(n, 0)); //수빈이가 있는 위치로 초기화

        while(!queue.isEmpty()) {
            //큐가 빌 때 까지
            Node curNode = queue.poll();
            int pos = curNode.pos;
            int sec = curNode.sec;
            if (time[pos] < sec)
                continue; //현재 노드에 저장되어있는 시간(거리)가 더 작으면 패스

            //+1이동
            int nextPos = pos + 1;
            int nextSec = sec + 1;
            if (nextPos < MAX_POS && time[nextPos] > nextSec) {
                //더 짧은 시간으로 갱신
                time[nextPos] = nextSec;
                queue.add(new Node(nextPos, nextSec));
            }
            //-1이동
            nextPos = pos - 1;
            nextSec = sec + 1;
            if (nextPos >= 0 && nextPos < MAX_POS && time[nextPos] > nextSec) {
                time[nextPos] = nextSec;
                queue.add(new Node(nextPos, nextSec));
            }

            //2x로 이동
            nextPos = pos * 2;
            nextSec = sec;
            if (nextPos < MAX_POS && time[nextPos] > nextSec) {
                time[nextPos] = nextSec;
                queue.add(new Node(nextPos, nextSec));
            }
        }

    }


    static class Node implements Comparable<Node> {
        private int pos;
        private int sec;

        public Node(int pos, int sec){
            this.pos = pos;
            this.sec = sec;
        }


        @Override
        public int compareTo(Node o) {
            return sec-o.sec;
        }

    }

}

package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_13549_���ٲ���3 {
    //���� (���5)

    //ť�� ���ο� ��带 ���� �� ����
    //������ ������ ������������ �����ϰ�,
    //���� ����� ���� ��带 ������ �ִܰŸ��� �����Ѵ�.
    static int n, k;
    static int[] time;
    static int MAX_POS = 100001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //n : �����̰� �ִ� ��ġ
        n = Integer.parseInt(st.nextToken());
        //k : ������ �ִ� ��ġ
        k = Integer.parseInt(st.nextToken());
        time = new int[MAX_POS];
        dijkstra();
        System.out.println(time[k]);

    }

    static void dijkstra(){
        PriorityQueue<Node> queue= new PriorityQueue<>();
        Arrays.fill(time, MAX_POS);
        time[n] = 0;

        queue.add(new Node(n, 0)); //�����̰� �ִ� ��ġ�� �ʱ�ȭ

        while(!queue.isEmpty()) {
            //ť�� �� �� ����
            Node curNode = queue.poll();
            int pos = curNode.pos;
            int sec = curNode.sec;
            if (time[pos] < sec)
                continue; //���� ��忡 ����Ǿ��ִ� �ð�(�Ÿ�)�� �� ������ �н�

            //+1�̵�
            int nextPos = pos + 1;
            int nextSec = sec + 1;
            if (nextPos < MAX_POS && time[nextPos] > nextSec) {
                //�� ª�� �ð����� ����
                time[nextPos] = nextSec;
                queue.add(new Node(nextPos, nextSec));
            }
            //-1�̵�
            nextPos = pos - 1;
            nextSec = sec + 1;
            if (nextPos >= 0 && nextPos < MAX_POS && time[nextPos] > nextSec) {
                time[nextPos] = nextSec;
                queue.add(new Node(nextPos, nextSec));
            }

            //2x�� �̵�
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

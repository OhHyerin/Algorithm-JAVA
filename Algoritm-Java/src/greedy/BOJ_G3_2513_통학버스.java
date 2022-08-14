package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G3_2513_���й��� {
    //�׸���


    static int N;  //����Ʈ ������ �� (2<=N<=30000)
    static int K;  //���й����� ���� (1<=K<=2000)
    static int S;  //�б��� ��ġ
    static Queue<Pos> left;
    static Queue<Pos> right;
    static int dist;
    static int vacanct;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        left = new PriorityQueue<>();
        right = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int pos = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());

            if (pos < S) {
                left.add(new Pos(pos, cnt, "left"));
            } else {
                right.add(new Pos(pos, cnt, "right"));
            }

        }
        dist += fillSeat(left);
        vacanct = 0;
        dist += fillSeat(right);

        System.out.println(dist);

    }

    private static int fillSeat(Queue<Pos> list) {
        int move = 0;
        while (!list.isEmpty()) {
            Pos cur = list.poll();
            while (cur.cnt > 0) { //�ش� ����Ʈ�� �л��� ��� �¿ﶧ���� �ݺ�
                if (vacanct == 0) {  //�����ڸ� ������
                    //���� ���� ������ �� (���簡 ���� �б����� �� �Ÿ��ϱ�)
                    move += Math.abs(cur.pos - S)*2;
                    vacanct = K;  //��� �����ϱ� K��ŭ�� �¼� ����
                    continue;
                }
                if (cur.cnt > vacanct) {  //�� �� Ż �� �ִٸ�
                    cur.cnt -= vacanct;  //���� �ڸ���ŭ�� �л����� �¿�
                    vacanct = 0;  //�����ڸ� 0
                } else if (cur.cnt < vacanct) {  //�л� ��� �¿� �� �ִٸ�
                    vacanct -= cur.cnt;  //���� �л�����ŭ ä��
                    cur.cnt = 0;  //��� Ž
                }
            }
        }
        return move;
    }

    private static class Pos implements Comparable<Pos> {
        int pos;
        int cnt;
        String relpos;

        public Pos(int pos, int cnt, String relpos) {
            this.pos = pos;
            this.cnt = cnt;
            this.relpos = relpos;
        }

        @Override
        public int compareTo(Pos o) {
            if (relpos.equals("left")) {
                return this.pos - o.pos;
            } else {
                return (this.pos - o.pos) * -1;
            }
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "pos=" + pos +
                    ", cnt=" + cnt +
                    ", relpos='" + relpos + '\'' +
                    '}';
        }
    }
}

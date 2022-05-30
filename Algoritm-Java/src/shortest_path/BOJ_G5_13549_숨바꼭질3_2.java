package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_13549_���ٲ���3_2 {
    //���� ���5
    //�ִܰ�� - ���ͽ�Ʈ��
    //�ٽ�Ǯ��


    //dp������ �ϴ� time�迭�� �����ؼ� �̵� case���� time�迭 ����
    //queue�� add�ؼ� �ּҺ�� ��ġ�� �̵�

    static int N, K;
    static int[] time;  //�ش� ��ġ���� �ּҽð��� �����ϴ� dp�迭
    static int MAX_POS = 100001; //�Ѿ�ϱ� ���� //10������ ������ �ε���������

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        time = new int[MAX_POS];  //�������� MAX_POS��ŭ ��ġ�� �ִٰ� ����

        dijkstra();
        System.out.println(time[K]);  //K��ġ������ �ּҽð� ��ȯ

    }

    private static void dijkstra() {
        Queue<Pos> queue = new LinkedList<>();
        Arrays.fill(time, Integer.MAX_VALUE);  //time�� �ִܰŸ��� �����ϱ����� �ʱⰪ ����

        time[N] = 0; //�����̰� ���� �ִ� ��ġ : time:0
        queue.add(new Pos(N, 0));  //queue�� �߰�

        while (!queue.isEmpty()) {
            Pos cur = queue.poll();
            int curPos = cur.pos;  //���� ��ġ
            int curSec = cur.sec;  //���� ��ġ�� ���� �ð�

            if (time[cur.pos] < cur.sec) continue; //�̹� time�迭�� ����Ǿ��ִ� ���� ���� ��ġ�� ���� �ð����� �� ������ continue

            //+1�̵�
            int nextPos = curPos + 1;  //��ġ 1 �̵�
            int nextSec = curSec + 1;  //�ð� 1�� �߰�
            if (isIn(nextPos, nextSec) && time[nextPos] > nextSec) {
                //time�迭���� nextSec�� �� ������ time�迭�� ����
                time[nextPos] = nextSec;
                queue.add(new Pos(nextPos, nextSec)); //���� ��ġ�� �̵�
            }

            //-1�̵�
            nextPos = curPos - 1;  //��ġ -1 �̵�
            nextSec = curSec + 1;  //�ð� 1�� �߰�
            if (isIn(nextPos, nextSec) && time[nextPos] > nextSec) {
                time[nextPos] = nextSec;
                queue.add(new Pos(nextPos, nextSec));
            }

            //*2�� �����̵�
            nextPos = curPos * 2;  //��ġ *2 �̵�
            nextSec = curSec;      //�ð� �߰� ����
            if (isIn(nextPos, nextSec) && time[nextPos] > nextSec) {
                time[nextPos] = nextSec;
                queue.add(new Pos(nextPos, nextSec));
            }

        }
    }

    private static boolean isIn(int pos, int sec){
        return pos>=0 && sec>=0 && pos<MAX_POS;
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

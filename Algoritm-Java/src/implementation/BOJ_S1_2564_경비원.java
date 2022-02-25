package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_S1_2564_���� {
    // ���� �ǹ�1
    // IM���
    //�簢���� �� �켭 �� ���̶�� ���� (�� ���ο� �ִٰ� �����ϰ� �ּڰ�)

    static int n, m;
    static int how;
    static int dx, dy;
    static ArrayList<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        how = Integer.parseInt(br.readLine()); //���� �
        list = new ArrayList<>();
        int totalLength = (n+m)*2;

        for (int i = 0; i <= how; i++) {
            st = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(st.nextToken());
            int position = Integer.parseInt(st.nextToken());

            if (direction == 1) {
                list.add(position);
            } else if (direction == 2) {
                list.add(m + n + (m - position));
            } else if (direction == 3) {
                list.add(2 * m + n + (n - position));
            } else {
                list.add(m + position);
            }
        }

        int leng = list.get(list.size()-1);
        int answer = 0;

        for(int i=0;i<how;i++){
            int tmp = Math.abs(list.get(i)-leng);
            answer += Math.min(tmp, totalLength-tmp);
        }

        sb.append(answer).append("\n");
        System.out.println(sb);


    }


}

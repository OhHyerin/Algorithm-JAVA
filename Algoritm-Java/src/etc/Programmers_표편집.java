package etc;

import java.util.ArrayList;
import java.util.List;

public class Programmers_ǥ���� {
    //2021 īī�� ä��

    /*
    ���� ����
    "U X" : ���� ���õ� �࿡�� Xĭ ���� �ִ� ���� �����մϴ�.
    "D X" : ���� ���õ� �࿡�� Xĭ �Ʒ��� �ִ� ���� �����մϴ�.
    "C" : ���� ���õ� ���� ������ ��, �ٷ� �Ʒ� ���� �����մϴ�. ��, ������ ���� ���� ������ ���� ��� �ٷ� �� ���� �����մϴ�.
    "Z" : ���� �ֱٿ� ������ ���� ������� �����մϴ�. ��, ���� ���õ� ���� �ٲ��� �ʽ��ϴ�.
     */


    public static void main(String[] args) {
        int n = 8;  //�� ������ ��Ÿ���� ����
        int k = 2;  //ó���� ���õ� ���� ��ġ
        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};

        System.out.println(solution(n, k, cmd));

    }

    static public String solution(int n, int k, String[] cmd) {
        String answer = "";

        List<Row> rows = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            rows.add(new Row(i, true));
        }

        int cur = k;

        for (int i = 0; i < cmd.length; i++) {
            if (cmd[i].charAt(0) == 'U') {
                int cnt = cmd[i].charAt(2)-'0';

            } else if (cmd[i].charAt(0) == 'D') {
                int cnt = cmd[i].charAt(2)-'0';

            } else if (cmd[i].charAt(0) == 'C') {

            } else if (cmd[i].charAt(0) == 'Z') {

            }

        }


        return answer;
    }

    static class Row implements Comparable<Row> {
        int idx;
        boolean exist;

        public Row(int idx, boolean exist) {
            this.idx = idx;
            this.exist = exist;
        }

        @Override
        public int compareTo(Row o) {
            return idx - o.idx;
        }
    }


}

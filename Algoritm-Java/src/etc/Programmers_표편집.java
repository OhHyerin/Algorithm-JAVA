package etc;

import java.util.ArrayList;
import java.util.List;

public class Programmers_표편집 {
    //2021 카카오 채용

    /*
    문제 설명
    "U X" : 현재 선택된 행에서 X칸 위에 있는 행을 선택합니다.
    "D X" : 현재 선택된 행에서 X칸 아래에 있는 행을 선택합니다.
    "C" : 현재 선택된 행을 삭제한 후, 바로 아래 행을 선택합니다. 단, 삭제된 행이 가장 마지막 행인 경우 바로 윗 행을 선택합니다.
    "Z" : 가장 최근에 삭제된 행을 원래대로 복구합니다. 단, 현재 선택된 행은 바뀌지 않습니다.
     */


    public static void main(String[] args) {
        int n = 8;  //행 개수를 나타내는 정수
        int k = 2;  //처음에 선택된 행의 위치
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

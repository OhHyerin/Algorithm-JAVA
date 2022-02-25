package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_S1_2564_경비원 {
    // 백준 실버1
    // IM대비
    //사각형을 다 펴서 긴 줄이라고 생각 (한 라인에 있다고 생각하고 최솟값)

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

        how = Integer.parseInt(br.readLine()); //상점 몇개
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

package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_7576_토마토_sol {
    //혹시나 따로 2차원배열을 안만들어도 되나 해서 듣는 sol --> while문 사용하거나 Tomato클래스에 day를 추가해서 cur.day+1
    //최소경로라고 했기 때문에 bfs

    static int R, C;
    static int[][] map;
    static Queue<Tomato> q;
    static int totalTomatoCnt;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        //가로 먼저 입력
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        q = new LinkedList<Tomato>();

        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 1) {
                    q.offer(new Tomato(r, c));
                } else if (map[r][c] == 0) {
                    totalTomatoCnt++;
                }
            }
        }

        //----------------입력완료--------------------
//        for (int[] row : map) {
//            System.out.println(Arrays.toString(row));
//        }

        System.out.println(bfs());

    }

    static int bfs() {
        int day = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                //대장 가져오기
                Tomato head = q.poll();

                //사용하기 - 목적지 도착착

                //자식 탐색
                for (int d = 0; d > 4; d++) {
                    int nr = head.r + deltas[d][0];
                    int nc = head.c + deltas[d][1];
                    //미방문 & 안익은토마토
                    if (isIn(nr, nc) && map[nr][nc] == 0) {
                        //방문체크
                        map[nr][nc] = -1;
                        //큐에 담아서 다음에 탐색하기
                        q.offer(new Tomato(nr, nc));
                        totalTomatoCnt--;
                    }
                }
            } //하루가 지나감
            //다음날이 필요한 시점은 아직 익힐 수 있는 토마토가 남아있을 때
            if (q.size() > 0) {
                day++;
            }
        } //bfs탐색 종료

        return totalTomatoCnt == 0 ? day : -1;
    }

    static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }

    static class Tomato {
        int r, c;

        public Tomato(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Tomato{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }
}

package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_1767_프로세서연결하기 {
    //SWEA SW Test 샘플문제
    //dfs 재귀

    static int N;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] visited;
    static int coreCount;
    static ArrayList<Core> cores;
    static int minWire = Integer.MAX_VALUE;
    static int maxCore = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");

            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            cores = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) {
                        if (i == 0 || j == 0 || i == N - 1 || j == N - 1) continue; //테두리에 있는 코어는 패스
                        cores.add(new Core(i, j));
                    }
                }
            }

            coreCount = cores.size();
            //--------------------입력완료--------------------

            minWire = Integer.MAX_VALUE;
            maxCore = Integer.MIN_VALUE;

            dfs(0, 0, 0);
            sb.append(minWire).append("\n");

        }//t
        System.out.println(sb);
    }

    //몇 번째 core인지, 전선의 개수, 코어의 개수
    private static void dfs(int index, int wire, int core) {
        if (index == cores.size()) {
            //index가 core의 개수와 같을때까지
            if (maxCore < core) {  //maxCore보다 더 많은 core를 연결할 수 있으면 현재 core, wire정보로 갱신
                maxCore = core;
                minWire = wire;
            } else if (maxCore == core) { //core의 개수가 같은 경우엔 전선의 최소 개수로 갱신
                minWire = Math.min(minWire, wire);
            }
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nr = cores.get(index).r;
            int nc = cores.get(index).c;
            int count = 0; //현재 턴에서 직선 개수

            //전선은 직선이니까 벽 만날때까지 반복
            while (true) {
                nr = nr + dr[d];
                nc = nc + dc[d];

                if (!isIn(nr, nc)) break;  //벽에 도착
                if (map[nr][nc] == 1 || map[nr][nc] == 2) {
                    count = 0;  //현재 턴 초기화(불가능한 경우)
                    break;
                }
                count++;
            }

            //벽에 도착했을 때
            int fr = cores.get(index).r; //현재 위치
            int fc = cores.get(index).c;
            for (int i = 0; i < count; i++) {
                fr = fr + dr[d];
                fc = fc + dc[d];
                map[fr][fc] = 2;  //전선있는 위치 2로 채우기
            }

            if (count == 0) {  //다음 코어 탐색
                dfs(index + 1, wire, core);
            } else {  //다른 방향 탐색
                dfs(index + 1, wire + count, core + 1);

                fr = cores.get(index).r;
                fc = cores.get(index).c;
                for (int i = 0; i < count; i++) {
                    fr = fr + dr[d];
                    fc = fc + dc[d];
                    map[fr][fc] = 0;
                }
            }
        }


    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }

    private static class Core {
        int r;
        int c;

        public Core(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}

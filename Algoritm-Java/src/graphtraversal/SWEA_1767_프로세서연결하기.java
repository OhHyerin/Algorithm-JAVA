package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_1767_���μ��������ϱ� {
    //SWEA SW Test ���ù���
    //dfs ���

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
                        if (i == 0 || j == 0 || i == N - 1 || j == N - 1) continue; //�׵θ��� �ִ� �ھ�� �н�
                        cores.add(new Core(i, j));
                    }
                }
            }

            coreCount = cores.size();
            //--------------------�Է¿Ϸ�--------------------

            minWire = Integer.MAX_VALUE;
            maxCore = Integer.MIN_VALUE;

            dfs(0, 0, 0);
            sb.append(minWire).append("\n");

        }//t
        System.out.println(sb);
    }

    //�� ��° core����, ������ ����, �ھ��� ����
    private static void dfs(int index, int wire, int core) {
        if (index == cores.size()) {
            //index�� core�� ������ ����������
            if (maxCore < core) {  //maxCore���� �� ���� core�� ������ �� ������ ���� core, wire������ ����
                maxCore = core;
                minWire = wire;
            } else if (maxCore == core) { //core�� ������ ���� ��쿣 ������ �ּ� ������ ����
                minWire = Math.min(minWire, wire);
            }
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nr = cores.get(index).r;
            int nc = cores.get(index).c;
            int count = 0; //���� �Ͽ��� ���� ����

            //������ �����̴ϱ� �� ���������� �ݺ�
            while (true) {
                nr = nr + dr[d];
                nc = nc + dc[d];

                if (!isIn(nr, nc)) break;  //���� ����
                if (map[nr][nc] == 1 || map[nr][nc] == 2) {
                    count = 0;  //���� �� �ʱ�ȭ(�Ұ����� ���)
                    break;
                }
                count++;
            }

            //���� �������� ��
            int fr = cores.get(index).r; //���� ��ġ
            int fc = cores.get(index).c;
            for (int i = 0; i < count; i++) {
                fr = fr + dr[d];
                fc = fc + dc[d];
                map[fr][fc] = 2;  //�����ִ� ��ġ 2�� ä���
            }

            if (count == 0) {  //���� �ھ� Ž��
                dfs(index + 1, wire, core);
            } else {  //�ٸ� ���� Ž��
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

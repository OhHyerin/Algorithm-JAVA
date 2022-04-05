package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5656_�������� {
    //���� SW �����׽�Ʈ

    static int N, R, C;
    static int[][] map;
    static int defalutCount;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            map = new int[R][C];

            for (int i = 0; i < R; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < C; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] != 0) defalutCount++;
                }
            }

            min = Integer.MAX_VALUE;
            go(0, map);

            sb.append(min).append("\n");

        }//t
        System.out.println(sb);
    }

    static boolean go(int count, int[][] map) { //�ߺ����� �̿��Ͽ� ������ ������, ������ �� �μ����ٸ� true, �ƴϸ� false ����

        int result = getRemain(map);
        //��� ������ �� �μ����ٸ�
        if (result == 0) {
            min = 0;
            return true;
        }

        //��� ������ �� �����ٸ�
        if (count == N) {
            min = Math.min(min, result);
            return false;
        }

        int[][] newMap = new int[R][C];

        //0���� C-1������ ���� ��������
        for (int c = 0; c < C; c++) {
            //������ �´� ���� ã��
            int r = 0;
            while (r < R && map[r][c] == 0) ++r; //�� �����̸� ����ؼ� �Ʒ��� ������

            if (r == R) continue;//�ش� ���� ������ ����

            //�迭 ���¸� ���
            copy(map, newMap);

            //���� ���� �������� �ֺ��� ������ ��� ���� �Բ� ���� ó��
            boom(newMap, r, c);

            //�μ��� ���� ����
            down(newMap);

            //���� ���� ������ go
            if (go(count + 1, newMap)) return true;
        }
        return false;
    }

    static void boom(int[][] map, int r, int c) { //r, c ��ġ���� �ֺ��� ������ ��� ������ �ղ� �μ��� ó��
        Queue<Point> queue = new LinkedList<>();
        if (map[r][c] > 1) { //���� ũ�Ⱑ 1���� Ŭ ��
            queue.offer(new Point(r, c, map[r][c]));
        }
        map[r][c] = 0; //�ڽ��� ����ó��(�� ��������) : visit ó�� ����

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];

                for (int k = 1; k < p.cnt; k++) { //������ ũ��-1��ŭ �ݺ�
                    nr += dr[d];
                    nc += dc[d];
                    if(nr>=0 && nr<R && nc>=0 && nc<C && map[nr][nc]>0) {
                        if (map[nr][nc] > 1) { //�ֺ� ������ ������ �ִ� �����̸�
                            queue.offer(new Point(nr, nc, map[nr][nc]));
                        }
                        map[nr][nc] = 0; //���� ó��
                    }
                }
            }
        }


    }

    static void down(int[][] map) { //�μ��� ���� ����(���� ���� ����)
        for (int c = 0; c < C; c++) {
            int r = R - 1;
            while (r > 0) {
                if (map[r][c] == 0) { //��ĭ�̸� ���� ���� ã��
                    int nr = r - 1;
                    while (nr > 0 && map[nr][c] == 0) nr--;

                    map[r][c] = map[nr][c];
                    map[nr][c] = 0;
                }
                r--;
            }
        }
    }

    //list, queue, stack���� ó�� ����
    static ArrayList<Integer> list = new ArrayList<>();
    static void down2(int[][] map){ //down �� �� ���� ���
        for (int c = 0; c < C; c++) {
            int r = R - 1;
            while (r >= 0) {
                if (map[r][c] > 0) { //���� ã��
                   list.add(map[r][c]);
                   map[r][c] = 0;
                }
                r--;
            } //�μ����� �ʰ� �����ִ� ���� ����Ʈ�� �� ���, ������ �ִ� �ڸ��� �� �������� ó�� �Ϸ�

            r = R-1;
            for(int a : list){ //��������Ʈ
                map[r--][c] = a;
            }
            list.clear();
        }
    }

    static int getRemain(int[][] map) { //���� ���� �� ����
        int count = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != 0) {
                    ++count;
                }
            }
        }
        return count;
    }

    static void copy(int[][] map, int[][] newMap) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                newMap[i][j] = map[i][j];
            }
        }

    }


    static class Point {
        int r, c, cnt;

        public Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }


}

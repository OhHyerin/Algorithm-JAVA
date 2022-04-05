package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5656_벽돌깨기 {
    //모의 SW 역량테스트

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

    static boolean go(int count, int[][] map) { //중복순열 이용하여 구슬을 던지기, 벽돌이 다 부서졌다면 true, 아니면 false 리턴

        int result = getRemain(map);
        //모든 벽돌이 다 부서졌다면
        if (result == 0) {
            min = 0;
            return true;
        }

        //모든 구슬을 다 던졌다면
        if (count == N) {
            min = Math.min(min, result);
            return false;
        }

        int[][] newMap = new int[R][C];

        //0부터 C-1열까지 구슬 던져보기
        for (int c = 0; c < C; c++) {
            //구슬에 맞는 벽돌 찾기
            int r = 0;
            while (r < R && map[r][c] == 0) ++r; //빈 공간이면 계속해서 아래로 내려감

            if (r == R) continue;//해당 열은 벽돌이 없음

            //배열 상태를 백업
            copy(map, newMap);

            //현재 벽돌 기준으로 주변의 가능한 모든 벽돌 함께 연쇄 처리
            boom(newMap, r, c);

            //부서진 벽돌 정리
            down(newMap);

            //다음 구슬 던지러 go
            if (go(count + 1, newMap)) return true;
        }
        return false;
    }

    static void boom(int[][] map, int r, int c) { //r, c 위치에서 주변의 가능한 모든 벽돌도 합께 부수는 처리
        Queue<Point> queue = new LinkedList<>();
        if (map[r][c] > 1) { //벽돌 크기가 1보다 클 때
            queue.offer(new Point(r, c, map[r][c]));
        }
        map[r][c] = 0; //자신은 제거처리(빈 공간으로) : visit 처리 역할

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];

                for (int k = 1; k < p.cnt; k++) { //벽돌의 크기-1만큼 반복
                    nr += dr[d];
                    nc += dc[d];
                    if(nr>=0 && nr<R && nc>=0 && nc<C && map[nr][nc]>0) {
                        if (map[nr][nc] > 1) { //주변 벽돌에 영향을 주는 벽돌이면
                            queue.offer(new Point(nr, nc, map[nr][nc]));
                        }
                        map[nr][nc] = 0; //제거 처리
                    }
                }
            }
        }


    }

    static void down(int[][] map) { //부서진 벽돌 정리(남은 벽돌 정리)
        for (int c = 0; c < C; c++) {
            int r = R - 1;
            while (r > 0) {
                if (map[r][c] == 0) { //빈칸이면 내릴 벽돌 찾기
                    int nr = r - 1;
                    while (nr > 0 && map[nr][c] == 0) nr--;

                    map[r][c] = map[nr][c];
                    map[nr][c] = 0;
                }
                r--;
            }
        }
    }

    //list, queue, stack으로 처리 가능
    static ArrayList<Integer> list = new ArrayList<>();
    static void down2(int[][] map){ //down 좀 더 쉬운 방법
        for (int c = 0; c < C; c++) {
            int r = R - 1;
            while (r >= 0) {
                if (map[r][c] > 0) { //벽돌 찾기
                   list.add(map[r][c]);
                   map[r][c] = 0;
                }
                r--;
            } //부서지지 않고 남아있는 벽돌 리스트에 다 담기, 벽돌이 있던 자리는 빈 공간으로 처리 완료

            r = R-1;
            for(int a : list){ //벽돌리스트
                map[r--][c] = a;
            }
            list.clear();
        }
    }

    static int getRemain(int[][] map) { //남은 벽돌 수 리턴
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

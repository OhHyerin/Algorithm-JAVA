package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G3_16236_아기상어_sol {
    //백준 골드3
    //BFS
    //최단, 최소를 구할 땐 DFS X, BFS로 풀어야 함

    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int n; //배열 크기
    static int[][] map; //nxn배열
    static int sharkMoveCnt ;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        Shark shark = null;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    shark = new Shark(i, j, 2, 0);
                    map[i][j] = 0;  //상어 있던 자리를 0으로 바꿔서 지나다닐 수 있도록 한다.
                }
            }
        }
        //--------------입력완료--------------
        bfs(shark);

        System.out.println(sharkMoveCnt);

    }

    private static void bfs(Shark shark) {
        //방문여부 확인, Queue
        boolean[][] visited = new boolean[n][n];
        Queue<Shark> q = new LinkedList<>();

        //상어 초기 노드 삽입
        q.add(shark);
        visited[shark.row][shark.col] = true;

        //먹이를 찾기 위한 탐색 시작
        Fish target = null;

        int depth = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                //현재 위치 가져오기
                Shark head = q.poll();

                //사방탐색
                for (int d = 0; d < 4; d++) {
                    int nr = head.row + deltas[d][0];
                    int nc = head.col + deltas[d][1];

                    //범위 안에 있고, 방문한 적 없으면
                    if (isIn(nr, nc) && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        //map이 비어있거나 물고기의 사이즈가 현재 상어의 사이즈와 같을 때 지나갈 수 있음
                        if (map[nr][nc] == 0 || map[nr][nc] == head.size) {
                            q.offer(new Shark(nr, nc, head.size, head.eatCnt));
                        }
                        //발견한 물고기가 상어의 크기보다 작을 때 먹을 수 있음. 하지만 비교를 위해 먹지않고 저장해둠
                        else if (map[nr][nc] < head.size) {
                            Fish fish = new Fish(nr, nc, map[nr][nc], depth + 1);
                            if (target == null) {  //처음 만난 물고기면 target 에 저장
                                target = fish;
                            } else { //우선순위에 기반해서 타겟 설정하기. (물고기 잡기)
                                target = target.compareTo(fish) < 0 ? target : fish;
                            }
                        }
                    }
                }
            }  //한 턴 끝남

            //잡은 물고기가 있으면
            if (target != null) {
                break;
            }
            //없으면 다시 한번 검색
            depth++;
        } //bfs종료

        //탐색 종료됐는데 아직 물고기가 없으면 프로그램 끝남
        if (target == null) {
            return;
        }

        // 이제 Fish eat
        shark.eat();
        //먹은 자리(target) 0으로 변경
        map[target.row][target.col] = 0;
        //이동 회수 증가
        sharkMoveCnt += target.dist;

        //---------------------이제 한마리 먹음--------------
        //-------------나머지 새롭게 bfs--------------------
        bfs(new Shark(target.row, target.col, shark.size, shark.eatCnt));
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < n && c < n;
    }


    static class Shark {
        int row, col;
        int size;
        int eatCnt;

        public Shark(int row, int col, int size, int eatCnt) {
            this.row = row;
            this.col = col;
            this.size = size;
            this.eatCnt = eatCnt;
        }

        //상어가 물고기를 먹고 클 수 있다.
        public void eat() {
            eatCnt++;
            if (eatCnt == size) {
                size++;
                eatCnt = 0;
            }
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "row=" + row +
                    ", col=" + col +
                    ", size=" + size +
                    ", eatCnt=" + eatCnt +
                    '}';
        }

    }

    static class Fish implements Comparable<Fish> {
        int row, col;
        int size;
        int dist;

        public Fish(int row, int col, int size, int dist) {
            this.row = row;
            this.col = col;
            this.size = size;
            this.dist = dist;
        }

        @Override
        public int compareTo(Fish o) {
            if (this.dist == o.dist) {
                if (this.row == o.row) {
                    //3순위 정렬 기준 : col
                    return Integer.compare(this.col, o.col);
                } else {
                    //2순위 정렬기준 : row
                    return Integer.compare(this.row, o.row);
                }
            }
            //1순위 비교기준 : 거리
            return Integer.compare(this.dist, o.dist);
        }
    }

}

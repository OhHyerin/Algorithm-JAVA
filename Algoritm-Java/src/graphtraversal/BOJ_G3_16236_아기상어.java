package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G3_16236_아기상어 {
    //백준 골드3
    //BFS

    static int n; //배열 크기
    static int[][] map; //nxn배열
    static boolean[][] visited; //방문여부 체크
    static List<Pos> edible; //자신보다 작은 fish 위치를 가지고있는 리스트

    static int curR, curC; //아기상어 현재 위치
    static int level = 2; //아기 상어 레벨
    static int levelCount = 0;  //먹은 fish의 누적 값
    static int moveCount = 0; //이동거리 (answer)

    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];
        edible = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    //아기상어 위치 저장
                    curR = i;
                    curC = j;
                    map[i][j] = 0;
                }
            }
        }

        bfs();

        System.out.println(moveCount);

    }

    static void bfs() {
        while(true) {
            edible = new ArrayList<>();
            visited = new boolean[n][n];
            Queue<Pos> queue = new LinkedList<>();  //물고기 이동 위치

            queue.offer(new Pos(curR, curC, 0));  //초기 상어위치 queue에 삽입
            visited[curR][curC] = true;  //현재위치 방문처리

            while (!queue.isEmpty()) {
                Pos cur = queue.poll();

                for (int d = 0; d < 4; d++) {  //4방 탐색
                    int nextR = cur.r + dr[d];
                    int nextC = cur.c + dc[d];

                    if (isIn(nextR, nextC) && !visited[nextR][nextC]) {
                        //가려고 하는 곳이 범위안에 있고, 방문한 적 없음
                        if (map[nextR][nextC] != 0 && map[nextR][nextC] < level) {
                            //0이 아니고, 현재 상어 레벨보다 작으면 먹을 수 있음
                            queue.offer(new Pos(nextR, nextC, cur.dist + 1));  //다음위치 queue에 추가해주고, distance는 1 추가
                            edible.add(new Pos(nextR, nextC, cur.dist + 1));  //먹을 수 있는 생선 리스트에 추가
//                        levelCount++;
//                        map[nextR][nextC] = 0;
////                        visited = new boolean[n][n];
                            visited[nextR][nextC] = true;  //방문처리
//                        curC = nextC;
//                        curR = nextR;
//                        if(levelCount==level){
//                            //먹은 생선의 개수가 level과 같으면
//                            level++;  //상어의 level은 1 증가
//                            levelCount = 0;  //먹은 생선 개수 초기화
//                        }
                        } else if (map[nextR][nextC] == level || map[nextR][nextC] == 0) {
                            //0이거나, 현재 상어 레벨과 같으면 지나갈 수 있음
                            queue.offer(new Pos(nextR, nextC, cur.dist + 1));
                            visited[nextR][nextC] = true;
                        }
                    }

                }//d
            }//while
//        System.out.println(edible);
//        for(int i=0;i<edible.size();i++){
//            moveCount += edible.get(i).dist;
//        }

            if (edible.size() == 0) {
                //더이상 먹을 수 있는게 없음
                return;
            }

            Pos fish = edible.get(0); //첫 번재 물고기 꺼냄
            for (int i = 1; i < edible.size(); i++) {
                if (edible.get(i).dist < fish.dist) {
                    //거리순으로 갱신
                    fish = edible.get(i);
                }
                if (edible.get(i).dist == fish.dist) {
                    //거리가 같으면 c값이 작은게 우선
                    if (fish.r > edible.get(i).r) {
                        fish = edible.get(i);
                    }
                }
            }

            moveCount += fish.dist;
            levelCount++;
            map[fish.r][fish.c] = 0;
            if (levelCount == level) {
                level++;
                levelCount = 0;
            }

            curR = fish.r;
            curC = fish.c;

        }
    }


    static boolean isIn(int r, int c) {
        return r < n && c < n && r >= 0 && c >= 0;
    }

    static class Pos {
        int r;
        int c;
        int dist;

        public Pos(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "r=" + r +
                    ", c=" + c +
                    ", dist=" + dist +
                    '}';
        }
    }

}

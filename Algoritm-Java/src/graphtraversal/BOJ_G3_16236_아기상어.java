package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G3_16236_�Ʊ��� {
    //���� ���3
    //BFS

    static int n; //�迭 ũ��
    static int[][] map; //nxn�迭
    static boolean[][] visited; //�湮���� üũ
    static List<Pos> edible; //�ڽź��� ���� fish ��ġ�� �������ִ� ����Ʈ

    static int curR, curC; //�Ʊ��� ���� ��ġ
    static int level = 2; //�Ʊ� ��� ����
    static int levelCount = 0;  //���� fish�� ���� ��
    static int moveCount = 0; //�̵��Ÿ� (answer)

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
                    //�Ʊ��� ��ġ ����
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
            Queue<Pos> queue = new LinkedList<>();  //����� �̵� ��ġ

            queue.offer(new Pos(curR, curC, 0));  //�ʱ� �����ġ queue�� ����
            visited[curR][curC] = true;  //������ġ �湮ó��

            while (!queue.isEmpty()) {
                Pos cur = queue.poll();

                for (int d = 0; d < 4; d++) {  //4�� Ž��
                    int nextR = cur.r + dr[d];
                    int nextC = cur.c + dc[d];

                    if (isIn(nextR, nextC) && !visited[nextR][nextC]) {
                        //������ �ϴ� ���� �����ȿ� �ְ�, �湮�� �� ����
                        if (map[nextR][nextC] != 0 && map[nextR][nextC] < level) {
                            //0�� �ƴϰ�, ���� ��� �������� ������ ���� �� ����
                            queue.offer(new Pos(nextR, nextC, cur.dist + 1));  //������ġ queue�� �߰����ְ�, distance�� 1 �߰�
                            edible.add(new Pos(nextR, nextC, cur.dist + 1));  //���� �� �ִ� ���� ����Ʈ�� �߰�
//                        levelCount++;
//                        map[nextR][nextC] = 0;
////                        visited = new boolean[n][n];
                            visited[nextR][nextC] = true;  //�湮ó��
//                        curC = nextC;
//                        curR = nextR;
//                        if(levelCount==level){
//                            //���� ������ ������ level�� ������
//                            level++;  //����� level�� 1 ����
//                            levelCount = 0;  //���� ���� ���� �ʱ�ȭ
//                        }
                        } else if (map[nextR][nextC] == level || map[nextR][nextC] == 0) {
                            //0�̰ų�, ���� ��� ������ ������ ������ �� ����
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
                //���̻� ���� �� �ִ°� ����
                return;
            }

            Pos fish = edible.get(0); //ù ���� ����� ����
            for (int i = 1; i < edible.size(); i++) {
                if (edible.get(i).dist < fish.dist) {
                    //�Ÿ������� ����
                    fish = edible.get(i);
                }
                if (edible.get(i).dist == fish.dist) {
                    //�Ÿ��� ������ c���� ������ �켱
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

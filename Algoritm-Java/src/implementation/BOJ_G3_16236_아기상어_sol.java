package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G3_16236_�Ʊ���_sol {
    //���� ���3
    //BFS
    //�ִ�, �ּҸ� ���� �� DFS X, BFS�� Ǯ��� ��

    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int n; //�迭 ũ��
    static int[][] map; //nxn�迭
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
                    map[i][j] = 0;  //��� �ִ� �ڸ��� 0���� �ٲ㼭 �����ٴ� �� �ֵ��� �Ѵ�.
                }
            }
        }
        //--------------�Է¿Ϸ�--------------
        bfs(shark);

        System.out.println(sharkMoveCnt);

    }

    private static void bfs(Shark shark) {
        //�湮���� Ȯ��, Queue
        boolean[][] visited = new boolean[n][n];
        Queue<Shark> q = new LinkedList<>();

        //��� �ʱ� ��� ����
        q.add(shark);
        visited[shark.row][shark.col] = true;

        //���̸� ã�� ���� Ž�� ����
        Fish target = null;

        int depth = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                //���� ��ġ ��������
                Shark head = q.poll();

                //���Ž��
                for (int d = 0; d < 4; d++) {
                    int nr = head.row + deltas[d][0];
                    int nc = head.col + deltas[d][1];

                    //���� �ȿ� �ְ�, �湮�� �� ������
                    if (isIn(nr, nc) && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        //map�� ����ְų� ������� ����� ���� ����� ������� ���� �� ������ �� ����
                        if (map[nr][nc] == 0 || map[nr][nc] == head.size) {
                            q.offer(new Shark(nr, nc, head.size, head.eatCnt));
                        }
                        //�߰��� ����Ⱑ ����� ũ�⺸�� ���� �� ���� �� ����. ������ �񱳸� ���� �����ʰ� �����ص�
                        else if (map[nr][nc] < head.size) {
                            Fish fish = new Fish(nr, nc, map[nr][nc], depth + 1);
                            if (target == null) {  //ó�� ���� ������ target �� ����
                                target = fish;
                            } else { //�켱������ ����ؼ� Ÿ�� �����ϱ�. (����� ���)
                                target = target.compareTo(fish) < 0 ? target : fish;
                            }
                        }
                    }
                }
            }  //�� �� ����

            //���� ����Ⱑ ������
            if (target != null) {
                break;
            }
            //������ �ٽ� �ѹ� �˻�
            depth++;
        } //bfs����

        //Ž�� ����ƴµ� ���� ����Ⱑ ������ ���α׷� ����
        if (target == null) {
            return;
        }

        // ���� Fish eat
        shark.eat();
        //���� �ڸ�(target) 0���� ����
        map[target.row][target.col] = 0;
        //�̵� ȸ�� ����
        sharkMoveCnt += target.dist;

        //---------------------���� �Ѹ��� ����--------------
        //-------------������ ���Ӱ� bfs--------------------
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

        //�� ����⸦ �԰� Ŭ �� �ִ�.
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
                    //3���� ���� ���� : col
                    return Integer.compare(this.col, o.col);
                } else {
                    //2���� ���ı��� : row
                    return Integer.compare(this.row, o.row);
                }
            }
            //1���� �񱳱��� : �Ÿ�
            return Integer.compare(this.dist, o.dist);
        }
    }

}

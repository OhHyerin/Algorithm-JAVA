package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_17135_ĳ�����潺 {
    //���� ���4
    //���͵� - ������

    /*
    1. �ü��� 3��, �ϳ��� ĭ�� �ִ� 1�� ���� �� �ִ�.
    2. �� �ϸ��� �ü��� �� �ϳ��� ������ �� �ְ�, ���ÿ� �����Ѵ�.
    3. �ü��� �Ÿ��� D������ �� �� ���� ������ �ִ� ���� �����ϰ�, �Ÿ��� ���� ��� ���ʿ� �ִ� ���� �����Ѵ�.
    4. ���� ���� ���� �ü����� ���ݴ��� �� �ִ�.
    5. ������ ������ ���� �������� �̵��Ѵ�.
    6. ��� ���� ������� ������ ����ȴ�.
     */

    static int R, C; // RxC map
    static int D;   // D : �ü��� ���� �Ÿ� ����
    static int[][] map;
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[R+1][C+1];

        for (int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //--------------�Է� �Ϸ�-------------------------
        combination(0, 1, new int[3]);

        System.out.println(answer);

    }

    private static void combination(int cnt, int start, int[] selected){
        if(cnt==3){
//            System.out.println(Arrays.toString(selected));
            int[][] copyMap = new int[R+1][C+1];
            for(int i=1;i<=R;i++){
                for(int j=1;j<=C;j++){
                    copyMap[i][j] = map[i][j];
                }
            }

            int totalCount = 0;
            for(int i=1;i<=R;i++){
                totalCount += shoot(selected, copyMap);
                moveEnemy(copyMap);
            }

            answer = Math.max(answer, totalCount);
            return;
        }

        for(int i=start;i<=C;i++){
            selected[cnt] = i;
            combination(cnt+1, i+1, selected);
            selected[cnt] = 0;
        }
    }

    private static int shoot(int[] archer, int[][] temp){
        Enemy[] enemies = new Enemy[3];
        int killCount = 0;

        for(int i=0;i<3;i++){
            enemies[i] = findEnemy(R+1, archer[i], temp);
        }

        for(int i=0;i<3;i++){
            if(enemies[i].dist<=D ){
                int dr = enemies[i].r;
                int dc = enemies[i].c;
                if(dr==-1 || temp[dr][dc]==0) continue;
                temp[dr][dc] = 0;
                killCount++;
            }
        }
        return killCount;
    }

    private static Enemy findEnemy(int r, int c, int[][]temp){
        Queue<Enemy> enemies = new PriorityQueue<>();
        Enemy closeEnemy = null;
        for(int i=1;i<=R;i++){
            for(int j=1;j<=C;j++){
                if(temp[i][j]==1){
                    enemies.add(new Enemy(i, j, Math.abs(r-i)+Math.abs(c-j)));
                }
            }
        }
        if(enemies.size()!=0){
            closeEnemy = enemies.poll();
        }else{
            closeEnemy = new Enemy(-1, -1, Integer.MAX_VALUE);
        }
        return closeEnemy;
    }

    private static void moveEnemy(int[][] temp){
        for(int i=R;i>=1;i--){
            for(int j=1;j<=C;j++){
                temp[i][j] = temp[i-1][j];
            }
        }
    }

    private static class Enemy implements Comparable<Enemy>{
        int r;
        int c;
        int dist;

        public Enemy(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }

        @Override
        public int compareTo(Enemy o) {
            if(dist==o.dist) return c-o.c;
            else return dist-o.dist;
        }
    }


}

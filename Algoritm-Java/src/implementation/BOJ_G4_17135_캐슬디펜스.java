package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_17135_캐슬디펜스 {
    //백준 골드4
    //스터디 - 순조부

    /*
    1. 궁수는 3명, 하나의 칸에 최대 1명만 있을 수 있다.
    2. 각 턴마다 궁수는 적 하나를 공격할 수 있고, 동시에 공격한다.
    3. 궁수는 거리가 D이하인 적 중 가장 가까이 있는 적을 공격하고, 거리가 같을 경우 왼쪽에 있는 적을 공격한다.
    4. 같은 적이 여러 궁수에게 공격당할 수 있다.
    5. 공격이 끝나면 적은 성쪽으로 이동한다.
    6. 모든 적이 사라지면 게임이 종료된다.
     */

    static int R, C; // RxC map
    static int D;   // D : 궁수의 공격 거리 제한
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

        //--------------입력 완료-------------------------
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

package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G4_17135_캐슬디펜스_fail {
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
    static int[][] copyMap;
    static int[] selected;
    static int enemyCount = 0;
    static boolean[][] isKilled;
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[R+2][C+1];
        copyMap = new int[R+2][C+1];
        //행 1~R, R+1에 궁수들, 열 1~C
        selected = new int[3];
        isKilled = new boolean[R+2][C+1];

        for(int i=1;i<=R;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=C;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==1) enemyCount++;
            }
        }

//        for(int i=0;i<R;i++){
//            for(int j=0;j<C;j++){
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }

        combination(0, 1);
        System.out.println(answer);

    }



    private static void combination(int cnt, int start){
        //base
        if(cnt==3){
            System.out.println(Arrays.toString(selected));
            //배열 깊은복사
            for(int i=1;i<=R;i++){
                for(int j=1;j<=C;j++){
                    copyMap[i][j] = map[i][j];
                }
            }
            answer = Math.max(answer, shoot(selected));
            return;
        }


        //inductive
        for(int i=start;i<=C;i++){
            selected[cnt] = i;
            combination(cnt+1, i+1);
        }
    }



    private static int shoot(int[] archers){
        int turnEnemyCount = enemyCount;
        int lineKilledCount = 0;
        int totalKillEnemyCount = 0;
        //-----------적 찾기----------------
        Archer archer1 = new Archer(R+1, archers[0]);
        Archer archer2 = new Archer(R+1, archers[1]);
        Archer archer3 = new Archer(R+1, archers[2]);

        while(turnEnemyCount>=0) {
            Enemy enemy1 = null;
            Enemy enemy2 = null;
            Enemy enemy3 = null;

            if(findEnemy(archer1, copyMap)!=null){
                enemy1 = findEnemy(archer1, copyMap);
            }
            if(findEnemy(archer2, copyMap)!=null){
                enemy2 = findEnemy(archer2, copyMap);
            }
            if(findEnemy(archer3, copyMap)!=null){
                enemy3 = findEnemy(archer3, copyMap);
            }

            //-------------적 위치 true처리--------------
            isKilled = new boolean[R + 2][C + 1];
            if(enemy1!=null){
                isKilled[enemy1.r][enemy1.c] = true;
            }
            if (enemy2 != null) {
                isKilled[enemy2.r][enemy2.c] = true;
            }
            if(enemy3!=null){
                isKilled[enemy3.r][enemy3.c] = true;
            }

            //-------------적 위치 0으로 설정------------
            lineKilledCount = 0;
            for (int i = 1; i <= R; i++) {
                for (int j = 1; j <= C; j++) {
                    if (isKilled[i][j]) {
                        lineKilledCount++;
                        copyMap[i][j] = 0;
                    }
                }
            }
            for(int i=1;i<=C;i++){
                if(copyMap[R][i]==1){
                    turnEnemyCount--;
                }
            }
            turnEnemyCount -= lineKilledCount;
            System.out.println("lineKilledCount : "+lineKilledCount);

            //-------------배열 이동-------------
            for(int i=R;i>=1;i--){
                for(int j=1;j<=C;j++){
                    copyMap[i][j] = copyMap[i-1][j];
                }
            }
            totalKillEnemyCount += lineKilledCount;
        }//while

        System.out.println("이턴에서 죽인 몬스터 개수 : "+totalKillEnemyCount);
//        for(int i=1;i<=R;i++){
//            for(int j=1;j<=C;j++){
//                System.out.print(copyMap[i][j]+" ");
//            }
//            System.out.println();
//        }
        return totalKillEnemyCount;
    }

    private static Enemy findEnemy(Archer archer, int[][] temp){
        Queue<Enemy> enemies = new PriorityQueue<>();

        for(int i=archer.r-1;i>R-D;i--){
            //궁수 위치에서 D만큼 떨어진 행까지 탐색
            for(int j=1;j<=C;j++){
                if(temp[i][j]!=0){
                    enemies.add(new Enemy(i, j, Math.abs(archer.r-i)+Math.abs(archer.c-j)));
                }
            }
        }

        return enemies.poll();
    }



    private static boolean shotDist(int ar, int ac, int mr, int mc){
        if( dist(ar, ac, mr, mc) <= D) return true;
        else return false;
    }

    private static int dist(int ar, int ac, int mr, int mc){
        return Math.abs(ar-mr)+Math.abs(ac-mc);
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
            //dist가 다르면 dist가 더 짧은 순으로
            //dist가 같으면 c가 더 작은 순으로(더 왼쪽)
            if(dist==o.dist) return c-o.c;
            else return dist-o.dist;
        }

        @Override
        public String toString() {
            return "Enemy{" +
                    "r=" + r +
                    ", c=" + c +
                    ", dist=" + dist +
                    '}';
        }
    }

    private static class Archer{
        int r;
        int c;
        public Archer(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Archer{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

}

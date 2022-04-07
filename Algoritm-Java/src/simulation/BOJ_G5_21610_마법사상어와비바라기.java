package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G5_21610_��������ͺ�ٶ�� {
    //�Ｚ SW���� �׽�Ʈ ���⹮��

    /*
    1. ���� �̵��ϱ�
    1-1. ���� �ִ� ĭ�� �� 1�� ������ (+1)
    2. ������ �ִ� ���� �밢�� ���⿡ ���� �ִ� ������ŭ ++
    2-2. ���� ������
    3. ���� �־��� ĭ �����ϰ� ������ ĭ �� ���� ���� 2 �̻��� ĭ�� ���� ����
    3-2. ���� ���� ���� -2
     */

    static int N, M;
    static int[][] map;
    static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static Queue<Pos> clouds = new LinkedList<>();
    static boolean[][] visited;
    static int answer;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        visited = new boolean[N+1][N+1];

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        clouds.add(new Pos(N, 1));
        clouds.add(new Pos(N, 2));
        clouds.add(new Pos(N-1, 1));
        clouds.add(new Pos(N-1, 2));

//        System.out.println(clouds);

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            move(d, s);  //�̵��ϰ� �񳻸���
            plusRainy();  //�����̾��� �� �밢���� �� ������ ������ŭ ���ϱ�
            makeCloud();  //�� ���� �����
        }
        count();  //���� 0 �̻��� �� ��������
        System.out.println(answer);

    }

    private static void move(int d, int s){
        int qSize = clouds.size();
        while(qSize-- >0){
            Pos cur = clouds.poll();
            int nr = cur.r + (dr[d] * s);
            int nc = cur.c + (dc[d] * s);

            while(!isValid(nr)) nr = changeLocation(nr);
            while(!isValid(nc)) nc = changeLocation(nc);

            clouds.add(new Pos(nr, nc));  //���� �̵�
            map[nr][nc]++;  //�񳻸���
            visited[nr][nc] = true;  //�����̾��� �ڸ�
        }
    }

    private static void plusRainy(){
        int qSize = clouds.size();

        while(qSize-- >0){
            Pos cur = clouds.poll();
            int cr = cur.r;
            int cc = cur.c;

            if(isIn(cr-1, cc-1) && map[cr-1][cc-1] != 0){
                map[cr][cc]++;
            }
            if(isIn(cr-1, cc+1) && map[cr-1][cc+1] != 0){
                map[cr][cc]++;
            }
            if(isIn(cr+1, cc-1) && map[cr+1][cc-1] != 0){
                map[cr][cc]++;
            }
            if(isIn(cr+1, cc+1) && map[cr+1][cc+1] != 0){
                map[cr][cc]++;
            }
        }
    }

    private static void makeCloud(){
        clouds.clear();
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(map[i][j]>=2 && !visited[i][j]){
                    clouds.add(new Pos(i, j));
                    map[i][j] -= 2;
                }
            }
        }
        for(int i=1;i<=N;i++){
            Arrays.fill(visited[i], false);
        }
    }

    private static void count(){
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                answer += map[i][j];
            }
        }
    }



    private static int changeLocation(int x){
        if(x<1) return x+N;
        else return x-N;
    }

    private static boolean isValid(int x){
        return x>0 && x<=N;
    }

    private static boolean isIn(int r, int c){
        return r>0 && c>0 && r<=N && c<=N;
    }


    private static class Pos{
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }
}

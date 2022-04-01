package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G4_14466_�Ұ������ǳʰ�����6 {
    //���� ���4
    //���͵� - ���ױ�Ż

    static int N; // N : �迭 ũ��
    static int K; // K : ���� ����
    static int R; // R : ���� ����
    static ArrayList<Pos> loads[][];  //���� ������ ��� �ִ� list
    static int[][] map;  //��ü map (�Ұ� �ִ� ���� 1)
    static boolean[][] visited;  //bfs�˻��� �� ����ϴ� visited
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[] isSelected;  //combination���� ���� ���� 2�� �����ϴ� �迭
    static Cow[] cows;  //�� number, r, c �����ϴ� �迭
    static int answer = 0;  //�� �� ����Ǵ���


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        loads = new ArrayList[N+1][N+1];  //�� ����
        map = new int[N+1][N+1]; //��ü �� (�Ұ� �ִ� �ڸ��� 1)

        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                loads[i][j] = new ArrayList<>();
            }
        }

        for(int i=0;i<R;i++){
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            loads[r1][c1].add(new Pos(r2,c2));
            loads[r2][c2].add(new Pos(r1,c1));
        }

        cows = new Cow[K];
        for(int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[r][c] = 1;  //�Ұ� �ִ� �ڸ��� 1
            cows[i] = new Cow(i+1, r, c);
        }

        isSelected = new int[2];
        combination(0, 0);

        System.out.println(K-answer);
    }

    static void combination(int cnt, int start){
        if(cnt==2){
            int fr=0, fc=0, tr=0,tc=0;
            for(int i=0;i<cows.length;i++){
                if(cows[i].num==isSelected[0]){
                    fr = cows[i].r;
                    fc = cows[i].c;
                }else if(cows[i].num==isSelected[1]){
                    tr = cows[i].r;
                    tc = cows[i].c;
                }
            }
            bfs(fr, fc, tr, tc);
            return;
        }
        for(int i=start;i<cows.length;i++){
            isSelected[cnt] = cows[i].num;
            combination(cnt+1, i+1);
        }
    }



    private static void bfs(int r, int c, int tr, int tc){
        Queue<Pos> queue = new LinkedList<>();
        visited = new boolean[N+1][N+1];

        queue.add(new Pos(r, c));
        visited[r][c] = true;

        while(!queue.isEmpty()){
            Pos cur = queue.poll();
            int cr = cur.r;
            int cc = cur.c;

            if(cr==tr && cc==tc){
                answer++;
                return;
            }

            for(int d=0;d<4;d++){
                int nr = cr+dr[d];
                int nc = cc+dc[d];

                boolean flag = true;

                if(isIn(nr,nc)){
                    if(!visited[nr][nc]){
                        for(Pos tmp:loads[cr][cc]){
                            if(tmp.r==nr && tmp.c==nc){
                                flag = false; //�Ұ�
                                continue;
                            }
                        }

                        if(flag){
                            //�ٸ��� �ǳʴ°� �ƴϸ� �̵�
                            queue.add(new Pos(nr, nc));
                            visited[nr][nc] = true;
                        }
                    }
                }
            }
        }
    }

    static boolean isIn(int r, int c){
        return r>0 && c>0 && r<=N && c<=N;
    }

    private static class Pos{
        int r;
        int c;
        Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    private static class Cow{
        int num;
        int r;
        int c;

        public Cow(int num, int r, int c) {
            this.num = num;
            this.r = r;
            this.c = c;
        }
    }
}

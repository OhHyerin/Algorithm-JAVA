package implementation;

import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G2_21609_������б� {
    //����

    /*
    ������ ��� : -1
    ������ ��� : 0
    �Ϲ� ��� : ���� M����

     */

    static int R, C;
    static int M; //������ ����
    static int[][] map;
    static boolean[][] visited;
    static int score;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static List<Block> blockList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = R;
        M = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        visited = new boolean[R][C];
        blockList = new ArrayList<>();

        for(int i=0;i<R;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<C;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

//        print();
//        rotate();  //90�� �ݽð���� ȸ��
//        print();

        while(true){
            findBlock();
            rotate();  //90

            Collections.sort(blockList); //blocklist �켱 �������� ����

            Block b = blockList.get(0);  //�� �� �ϳ����� �����ؼ�
            score = b.size * b.size; //���� ���
            blockBfs(b.r, b.c, map[b.r][b.c]);  //�� �����

//            down();


        }

    }

    private static void findBlock(){
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(!visited[i][j] && map[i][j]>0){  //�湮�� �� ���� �Ϲ� ���̸� bfsŽ��
                    bfs(i, j, map[i][j]);
                }

            }
        }

    }

    private static void bfs(int r, int c, int block){
        Queue<Pos> queue = new LinkedList<>();
        boolean[][] bfsVisited = new boolean[R][C];

        visited[r][c] = true;  //������ġ visited true
        bfsVisited[r][c] = true;  //bfs Ž�� visited true
        queue.add(new Pos(r, c));

        int size = 1;
        int rbCnt = 0;

        while(!queue.isEmpty()){
            Pos cur = queue.poll();

            for(int d=0;d<4;d++){
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];

                if(!isIn(nr, nc)) continue;
                if(map[nr][nc]==-1) continue;
                if(bfsVisited[nr][nc]) continue;

                if(map[nr][nc]==0){
                    //������ ���̸�
                    rbCnt++;
                    size++;
                    bfsVisited[nr][nc] = true;
                    queue.add(new Pos(nr, nc));
                }else if (map[nr][nc]==block){
                    size++;
                    visited[nr][nc] = true;
                    bfsVisited[nr][nc] = true;
                    queue.add(new Pos(nr, nc));
                }

            }
        }

        if(size==1) return;  //������ 1�̸� �ȳ���
        else blockList.add(new Block(r, c, size, rbCnt));  //1���� ũ�� ������Ʈ�� �߰�


    }

    private static void blockBfs(int r, int c, int block){
        Queue<Pos> queue = new LinkedList<>();

        queue.add(new Pos(r, c));

        map[r][c] = -2;  //����͵� -2��
        while(!queue.isEmpty()){
            Pos cur = queue.poll();
            for(int d=0;d<4;d++){
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];

                if(!isIn(nr, nc)) continue;
                if(map[nr][nc]==0 || map[nr][nc]==block){
                    map[nr][nc] = -2;
                    queue.add(new Pos(nr, nc));
                }
            }
        }


    }

    private static void down(){
        for(int j=0;j<C;j++){
            for(int i=R-1;i>=1;i--){

            }
        }

    }

    private static void rotate(){
        for(int t=1;t<R;t++){  //�ٵ� �װ� R-1��ŭ �ݺ�,, (3�� for�� ��ġ��ʹ�,,)

            for(int depth=(R+1)/2  ; depth>0 ; depth--){  //�� ĭ�� �ݽð���� ȸ��,,
                int bound = (R+1)-depth;
                int temp = map[depth][depth];
                //��
                for(int c=depth;c<bound;c++){
                    map[depth][c] = map[depth][c+1];
                }
                //������
                for(int r=depth;r<bound;r++){
                    map[r][bound] = map[r+1][bound];
                }
                //�Ʒ���
                for(int c=bound;c>depth;c--){
                    map[bound][c] = map[bound][c-1];
                }
                //����
                for(int r=bound;r>depth;r--){
                    map[r][depth] = map[r-1][depth];
                }

                map[depth+1][depth] = temp;

            }
        }
    }

    static void rotation() {
        int N = R;
        int[][] rotate = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                rotate[i][j] = map[j][N - 1 - i];
            }
        }

        map = rotate;
    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }

    private static void print(){
        for(int i=1;i<=R;i++){
            for(int j=1;j<=C;j++){
                System.out.print(map[i][j]+"  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static class Block implements Comparable<Block>{
        int r;
        int c;
        int size;
        int rbCnt;

        public Block(int r, int c, int size, int rbCnt) {
            this.r = r;
            this.c = c;
            this.size = size;
            this.rbCnt = rbCnt;
        }

        @Override
        public int compareTo(Block o) {
            if(this.size==o.size){
                if(this.rbCnt==o.rbCnt){
                    if(this.r==o.r){
                        return (this.c-o.c)*-1;
                    }
                    return (this.r-o.r)*-1;
                }
                return (this.rbCnt-o.rbCnt)*-1;
            }
            return (this.size-o.size)*-1;

        }
    }

    public static class Pos{
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}

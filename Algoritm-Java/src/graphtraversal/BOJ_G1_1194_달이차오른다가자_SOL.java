package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G1_1194_�����������ٰ���_SOL {
    //�ַ��
    //�׷���Ž�� - bfs
    //��Ʈ����ŷ (�Ǵ� 8���� �迭)

    /*
    ��ĭ : ������ �̵��� �� �ִ� (.)
    �� : ���� �̵��� �� ����(#)
    ���� : ������ �̵��� �� �ִ�. �� ���� ó�� ���� ���踦 ��´�. (a, b, c, d, e, f)
    �� : �����ϴ� ���谡 ���� ���� �̵��� �� �ִ�.(A, B, C, D, E, F)
    �ν����� ���� ��ġ : �� ���̰�, �ν��̰� ���� �� �ִ� ���̴�.(0)
    �ⱸ : ���� �������� ������, �ν��̰� �����ϴ� ���̴�. �� ���� ���� �̷θ� Ż���Ѵ�.(1)
     */

    //������ ���°� �������� T/F �̹Ƿ� ��Ʈ����ŷ�� �����Ͽ���.


    static int R, C;
    static char[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Pos ms;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for(int i=0;i<R;i++){
            String str = br.readLine();
            for(int j=0;j<C;j++){
                map[i][j] = str.charAt(j);
                if(map[i][j]=='0'){
                    ms = new Pos(i, j, 0);  //���� �ν��̰� ���ִ� ���� ����
                }
            }
        }

        System.out.println(bfs());

    }

    private static int bfs(){
        boolean[][][] visited = new boolean[R][C][1<<6];
        Queue<Pos> queue = new LinkedList<>();

        //�ʱ�ȭ
        queue.offer(ms);
        visited[ms.r][ms.c][ms.key] = true;

        int depth = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size-- >0){
                //1. �� ó�� ��� ��������
                Pos head = queue.poll();

                //2. ����Ѵ�. - ������ ã�ų� �ΰ����� �ൿ
                if(map[head.r][head.c]=='1'){
                    return depth;
                }
                //Ű�� ���� �ִٸ� -> update
                if(map[head.r][head.c] >= 'a' && map[head.r][head.c]<='f'){
                    head.updateKey(map[head.r][head.c]);
                }

                //3. �ڽ� ��� Ž��
                for(int d=0;d<4;d++) {
                    int nr = head.r + dr[d];
                    int nc = head.c + dc[d];

                    //�ش� ������ ���� Ű�� ���·� �������� ���ٸ� go
                    if (isIn(nr, nc) && !visited[nr][nc][head.key]) {
                        if (map[nr][nc] == '#') continue;


                        //���� ������ ���� - ���� ���� Ű�� ���� ��
                        if (map[nr][nc] >= 'A' && map[nr][nc] <= 'F' && !head.hasKey(map[nr][nc])) continue;

                        //�ƴϸ� ����
                        visited[nr][nc][head.key] = true;
                        queue.offer(new Pos(nr, nc, head.key));
                    }
                }
            }//while
            depth++;
        }

        return -1;
    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }

    private static class Pos{
        int r;
        int c;
        int key;

        public Pos(int r, int c, int key) {
            this.r = r;
            this.c = c;
            this.key = key;
        }

        public boolean hasKey(char gate){
            return (key & ( 1<< (gate-'A') ) ) > 0;
        }

        public void updateKey(char key){
            key |= ( 1 << (key-'a') );
        }
    }

}

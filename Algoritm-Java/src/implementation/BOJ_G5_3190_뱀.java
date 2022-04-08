package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G5_3190_�� {
    //����

    static int N; //������ ũ��
    static int K; //����� ����
    static int[][] map;  //1 : �� ��ġ, 2 : ��� ��ġ
    static int L; //���� ���� ��ȯ Ƚ��
    static int[] dr = {0, 1, 0, -1};  //��>��>��>��
    static int[] dc = {1, 0, -1, 0};
    static int time;
    static HashMap<Integer, String> directs = new HashMap<>();  //�迭��� hashmap

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); //������ ũ��
        K = Integer.parseInt(br.readLine()); //����� ����

        map = new int[N+2][N+2];  //r,c==0�̰ų� r,c==N+1�̸� ���� ����
        for(int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = 2; //����ڸ�
        }

         L = Integer.parseInt(br.readLine());

        for(int i=0;i<L;i++){
            st = new StringTokenizer(br.readLine());
            int turn = Integer.parseInt(st.nextToken());
            String d = st.nextToken();

            directs.put(turn, d);

        }
        move();
        System.out.println(time);
    }

    private static void move(){
        Queue<Pos> queue = new LinkedList<>();

        queue.add(new Pos(1, 1));
        int d = 0; //�����ʸ���
        int cr = 1;
        int cc = 1;
        map[1][1] = 1;  //��

        while(!queue.isEmpty()){
            time++;

            int nr = cr+dr[d];
            int nc = cc+dc[d];

            if(!isIn(nr, nc)) return;  //���� �����(���� �ε���) Ż��
//            if(queue.contains(new Pos(nr, nc))) {  //equals�޼ҵ带 �������ؼ� ��������� �ð��� �� �����ɸ�
            if(map[nr][nc]==1){ // ���� �ڱ� ���� �ε����� Ż��
//                System.out.println("nr : " + nr+"  nc : "+nc);
                return;
            }

            if(map[nr][nc]==2){  //���� ��ġ�� ����� �Ӹ��� �����
                queue.add(new Pos(nr, nc));
                map[nr][nc] = 1;
            }else{  //����� �ƴϸ�
                queue.add(new Pos(nr, nc));  //�Ӹ� �̵��ϰ�
                Pos remove = queue.poll();  //���� �ڸ�
                map[nr][nc] = 1;  //�� �Ӹ���ġ 1��
                map[remove.r][remove.c] = 0;  //�ڸ���ġ�� �ٽ� 0����
            }

            if(directs.containsKey(time)){ //�ؽøʿ� ���� �ð��� ���ԵǾ��ִٸ� ���� ��ȯ
                String dir = directs.get(time);
                if(dir.equals("L")){
                    d -= 1;
                    if(d<0){
                        d = 4+d;
                    }
                }else{
                    d = (d+1)%4;
                }
            }
//            System.out.println(queue);

            cr = nr;
            cc = nc;
        }
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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pos pos = (Pos) o;
            return r == pos.r && c == pos.c;
        }

    }
}

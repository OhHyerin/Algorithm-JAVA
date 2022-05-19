package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G4_8972_��ģ�Ƶ��̳� {
    //����, �ùķ��̼�

    static int R, C;
    static int[][] map;  //-1 : ����, 0 : ���, 1�̻� : �Ƶ��̳�
    static Pos JS;
    static List<Pos> robots;
    static Queue<Integer> directs;
    static int[] dr = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int[] dc = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
    static int count;
    static boolean isFinish;
    static Queue<Pos> bomb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        robots = new ArrayList<>();
        directs = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                char temp = str.charAt(j);

                if(temp=='I'){
                    map[i][j] = -1;
                    JS = new Pos(i, j);
                }else if(temp=='R'){
                    map[i][j] = 1;
//                    robots.add(new Pos(i, j));
                }
            }
        }

        String str = br.readLine();
        for (int i = 0; i < str.length(); i++) {
            directs.add(str.charAt(i) - '0');
        }

        //-----------------�Է� �Ϸ�--------------------


        move();



        StringBuilder sb = new StringBuilder();

        if(isFinish){
            sb.append("kraj ").append(count);
        }else{
            for(int i=0;i<R;i++){
                for(int j=0;j<C;j++){
                    char c;
                    if(map[i][j]==-1) c = 'I';
                    else if(map[i][j]==0) c='.';
                    else c='R';
                    sb.append(c);
                }
                sb.append("\n");
            }
        }

        System.out.println(sb);

    }

    private static void move() {
        Queue<Pos> queue = new LinkedList<>();

        queue.add(JS);  //���� ù ��° ��ġ

        while (!directs.isEmpty()) {
            int jsDir = directs.poll();  //�̹� �Ͽ� ������ ������ ����
            Pos cur = queue.poll();
            count++;

            int nr = cur.r + dr[jsDir];
            int nc = cur.c + dc[jsDir];

//            if (isIn(nr, nc)) break;  //������ �̵��ߴµ� ������ ������ �׳� ��
            if(map[nr][nc]>0){  //������ �̵��� ��ġ�� �Ƶ��̳밡 ������ ���� �߰��� ��
                isFinish = true;
                return;
            }

            map[cur.r][cur.c] = 0;
            map[nr][nc] = -1;

//            System.out.println("--------�����̵�-----------");
//            printAll();

            bomb = new LinkedList<>();
            robots = new ArrayList<>();
            for(int i=0;i<R;i++){
                for(int j=0;j<C;j++){
                    if(map[i][j]==1){
                        robots.add(new Pos(i, j));
                    }
                }
            }


            //�Ƶ��̳� �̵�
            for(int i=0;i<robots.size();i++){
                Pos curRobot = robots.get(i);

//                int d = closeMove(nr, nc, curRobot.r, curRobot.c);
                int min = Integer.MAX_VALUE;
                int d = 0;
                for(int j=1;j<10;j++){
                    if(j==5) continue;
                    int dir = Math.abs(nr- (curRobot.r+dr[j])) + Math.abs(nc-(curRobot.c+dc[j]));
                    if(dir<min){
                        d = j;
                        min = dir;
                    }
                }
//                System.out.println("d : "+d+"   min : "+min);


                map[curRobot.r][curRobot.c] = 0;
//                map[curRobot.r+dr[d]][curRobot.c+dc[d]]++;
                bomb.add(new Pos(curRobot.r+dr[d], curRobot.c+dc[d]));
            }

//            System.out.println("---------�Ƶ��̳� ���ΰ��� ����----------");
//            printAll();



            //���ΰ��� �������� �Ƶ��̳� �̵�
            while (!bomb.isEmpty()){
                Pos curBomb = bomb.poll();

//                System.out.println("r:"+curBomb.r+" c:"+curBomb.c);

                if(curBomb.r==nr && curBomb.c==nc){
                    //��������
                    isFinish = true;
                    return;
                }
//                else if(map[curBomb.r][curBomb.c]>0){
                    //�ٸ� �Ƶ��̳� ������
                    map[curBomb.r][curBomb.c]++;
//                    map[curBomb.r][curBomb.c] = 0;  //�Ƶ��̳� ��Ʈ��
//                }
            }

            for(int i=0;i<R;i++){
                for(int j=0;j<C;j++){
                    if(map[i][j]>1){
                        map[i][j] = 0;
                    }
                }
            }

//            System.out.println("-------�Ƶ��̳� ������ �� ��------------");
//            printAll();


            //���� ��Ƴ������� ���� ��ġ ����
            queue.add(new Pos(nr, nc));

        }

    }

//    private static int closeMove(int jr, int jc, int rr, int rc) {
//        int dir = 0;
//        if(rr<jr && rc<jc){
//            dir = 3;
//        }else if(rr<jr && rc==jc){
//            dir = 2;
//        }else if(rr<jr && rc>jc){
//            dir = 1;
//        }else if(rr==jr && rc<jc){
//            dir = 6;
//        }else if(rr==jr && rc>jc){
//            dir = 4;
//        }else if(rr>jr && rc<jc){
//            dir = 9;
//        }else if(rr>jr && rc==jc){
//            dir = 8;
//        }else if(rr>jr && rc>jc){
//            dir = 7;
//        }
//
//        return dir;
//    }

    private static void printAll(){
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }


    private static class Pos {
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

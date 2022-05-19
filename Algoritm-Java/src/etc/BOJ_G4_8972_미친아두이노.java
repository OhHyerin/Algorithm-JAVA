package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G4_8972_미친아두이노 {
    //구현, 시뮬레이션

    static int R, C;
    static int[][] map;  //-1 : 종수, 0 : 빈곳, 1이상 : 아두이노
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

        //-----------------입력 완료--------------------


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

        queue.add(JS);  //종수 첫 번째 위치

        while (!directs.isEmpty()) {
            int jsDir = directs.poll();  //이번 턴에 종수가 가야할 방향
            Pos cur = queue.poll();
            count++;

            int nr = cur.r + dr[jsDir];
            int nc = cur.c + dc[jsDir];

//            if (isIn(nr, nc)) break;  //종수가 이동했는데 밖으로 나가면 그냥 끝
            if(map[nr][nc]>0){  //종수가 이동한 위치에 아두이노가 있으면 게임 중간에 끝
                isFinish = true;
                return;
            }

            map[cur.r][cur.c] = 0;
            map[nr][nc] = -1;

//            System.out.println("--------종수이동-----------");
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


            //아두이노 이동
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

//            System.out.println("---------아두이노 어디로갈지 저장----------");
//            printAll();



            //어디로갈지 정했으면 아두이노 이동
            while (!bomb.isEmpty()){
                Pos curBomb = bomb.poll();

//                System.out.println("r:"+curBomb.r+" c:"+curBomb.c);

                if(curBomb.r==nr && curBomb.c==nc){
                    //종수만남
                    isFinish = true;
                    return;
                }
//                else if(map[curBomb.r][curBomb.c]>0){
                    //다른 아두이노 만나면
                    map[curBomb.r][curBomb.c]++;
//                    map[curBomb.r][curBomb.c] = 0;  //아두이노 터트림
//                }
            }

            for(int i=0;i<R;i++){
                for(int j=0;j<C;j++){
                    if(map[i][j]>1){
                        map[i][j] = 0;
                    }
                }
            }

//            System.out.println("-------아두이노 터지고 난 후------------");
//            printAll();


            //종수 살아남았으면 다음 위치 저장
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

package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G3_18235_지금만나러갑니다 {
    //bfs

    static int N;
    static int A, B;
    static int[] dc = {-1, 1};
    static boolean isMeet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        bfs();



    }

    private static void bfs(){
        Queue<Pos> queue = new LinkedList<>();
        queue.add(new Pos('A', A, 0));
        queue.add(new Pos('B', B, 0));


        while(!queue.isEmpty()){
            Pos cur = queue.poll();




        }


    }

    private static class Pos{
        char who;  //0 : 오리, 1 : 육리
        int loc;  //위치
        int jump; //점프 횟수

        public Pos(char who, int loc, int jump) {
            this.who = who;
            this.loc = loc;
            this.jump = jump;
        }
    }
}

package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G3_18235_���ݸ��������ϴ� {
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
        char who;  //0 : ����, 1 : ����
        int loc;  //��ġ
        int jump; //���� Ƚ��

        public Pos(char who, int loc, int jump) {
            this.who = who;
            this.loc = loc;
            this.jump = jump;
        }
    }
}

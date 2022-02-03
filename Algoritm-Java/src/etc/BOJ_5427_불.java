package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_5427_�� {
    //���� ���4
    //BFS?

    static int T;
    static int W, H;
    static int[][] map;
    static int[] rdir = {-1, 1, 0, 0}; //�����¿�
    static int[] cdir = {0, 0, -1, 1};
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++) {
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new int[H+2][W+2]; //Ż�ⱸ���� ���ؼ� �ʱ�ȭ
            Pos sang = null; //����� ��ġ �ʱ�ȭ
            Queue<Pos> fire = new LinkedList<>();

            for (int h = 1; h <= H; h++) {
                //?? : char�迭 ���� �� StringTokenizer�� �ذ� ���ϴ���?
                //(St.nextToken()).charAt(0);
                String str = br.readLine();
                for (int w = 1; w <= W; w++) {
                    char c = str.charAt(w-1);
                    if(c=='@'){
                        //����� ���� ��ġ
                        sang = new Pos(h,w,0);
                    }else if(c=='*'){
                        //��
                        fire.offer(new Pos(h,w));
                        map[h][w]= -1; //�湮ó��
                    }else if(c=='#'){
                        map[h][w] = -1; //�湮ó��
                    }
                }
            }
            //---------------�Է¿Ϸ�------------------------
            min = Integer.MAX_VALUE;
            bfs(sang, fire, map);

            //MAX_VALUE �״�θ� ���� �������� ���Ѱ��̹Ƿ� IMPOSSIBLE���
            if(min != Integer.MAX_VALUE){
                System.out.println(min);
            } else{
                System.out.println("IMPOSSIBLE");
            }



        }
    }

    private static void bfs(Pos person, Queue<Pos> fire, int[][] visited){
        Queue<Pos> queue = new LinkedList<>();

        //�ʱ�ȭ
        visited[person.r][person.c] = 1; //����� ó����ġ �湮ó�� o
        queue.offer(person);

        //������� ��ġ�� ���� queue�� �������� ����
        while(!queue.isEmpty()){
            //�� ���� �۶߸���
            //�ð� ���� �۶߸��� ���� �ʱ� ����ִ� ���� ������ŭ�� �����ϰ�
            //���� �̵��� ���� ���� �ݺ��� ��Ʈ����.
            for(int i=0;i<fire.size();i++){
                //���� ������ŭ
                Pos f = fire.poll();
                int fr = f.r;
                int fc = f.c;

                for(int j=0;j<4;j++){
                    int dfr = fr+rdir[j];
                    int dfc = fc+cdir[j];
                    //��ġ�� ��ȿ�ϰ� ���� ������ �ʾ����� �̵�
                    //x�� �湮ó��
                    if(dfc>0 && dfr>0 && dfc<W+1 && dfr<H+1 && visited[dfr][dfc] != -1){
                        visited[dfr][dfc] = -1;
                        fire.offer(new Pos(dfr, dfc));
                    }
                }
            }

            //����� �̵�
            for(int i=0;i<queue.size();i++){
                Pos p = queue.poll();
                int r = p.r;
                int c = p.c;
                int time = p.time;

                //���� ������ ��� min�� update
                if(c==0||r==0||c==W+1||r==H+1){
                    min = min>time? time:min;
                    continue;
                }

                for(int j=0;j<4;j++){
                    int dr = r+rdir[j];
                    int dc = c+cdir[j];
                    //������ ��ȿ�ϰ� ���� ������ �ʾҰ� ����̰� �湮�� ���� �ƴϸ� �湮ó��
                    if(dc>=0 && dr>=0 && dc<=W+1 && dr<=H+1){
                        if(visited[dr][dc] != -1 && visited[dr][dc] != 1){
                            queue.offer(new Pos(dr, dc, time+1));
                            visited[dr][dc] = 1;
                        }
                    }
                }
            }

        }
    }



    static class Pos{
        int r;
        int c;
        int time;
        Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
        Pos(int r, int c, int time){
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }
}

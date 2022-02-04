package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_5427_�� {
    //���� ���4
    //BFS?
    /* Ż�ⱸ 2���� �� ��Ǯ��
1
7 6
###.###
#.*...#
#..@..#
#.....#
#.....#
###.###
     */

    static int R, C;
    static char [][] map;
    static Queue<Pos> fires;
    static int min;
    //��, ��, ��, ��
    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            st = new StringTokenizer(br.readLine());
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            map = new char[R+2][C+2];
            for(int i=0;i<R+2;i++){
                for(int j=0;j<C+2;j++){
                    map[i][j] = 'X';
                }
            }
            fires = new LinkedList<>();


            int curR=0, curC=0;

            for(int i=1;i<=R;i++){
                String line = br.readLine();
                for(int j=1;j<=C;j++){
                    map[i][j] = line.charAt(j-1);
                    if(map[i][j]=='@'){
                        curR = i;
                        curC = j;
                    } else if(map[i][j]=='*'){
//                        System.out.println("�� ����! i : "+i +"    j : "+j);
                        fires.add(new Pos(i, j));
                    }
                }
            }

//            for(char[] i:map){
//                System.out.println(Arrays.toString(i));
//            }

            min = Integer.MAX_VALUE;
            maze(curR, curC);


            if(min != Integer.MAX_VALUE){
                sb.append(min).append("\n");
            }else{
                sb.append("IMPOSSIBLE\n");
            }
        }
        System.out.println(sb);


    }

    static void maze(int r, int c){
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(r, c, 0));

        while(!q.isEmpty()){
            //�� ���� �۶߸���
            // �ð����� �۶߸��� ���� �ʱ� ����ִ� ���� ������ŭ�� �����ϰ�
            //���� �̵��� ���� ���� �ݺ��� ��Ʈ����
            for(int i=0, end = fires.size();i<end;i++){ //���� �ٲٴϱ� ����
                Pos f = fires.poll();
                int fr = f.r;
                int fc = f.c;
//                System.out.println("fire! i�� : "+i+"   ���� ��ġ r " +fr+" c : "+fc);
                for(int j=0;j<4;j++){
                    int dfr = fr + dr[j];
                    int dfc = fc + dc[j];
                    //��ġ�� ��ȿ�ϰ� ������̰ų� ����̰������� �̵�
                    if(isIn(dfr, dfc, 'f') && (map[dfr][dfc]=='.' || map[dfr][dfc]=='@')){
//                    if(isIn(dfr, dfc) && (map[dfr][dfc]=='.')){
                        map[dfr][dfc] = '*';
                        fires.offer(new Pos(dfr, dfc));
                    }
                }
            }

            //����� �̵�
            for(int i=0, end=q.size();i<end;i++){
                Pos s = q.poll();
                int sr = s.r;
                int sc = s.c;
                int time = s.time;
//                System.out.println("�����! i�� : "+i+"   ����� ��ġ r " +sr+" c : "+sc+"   �ð� : "+time);

                //�������� ��
                if(sr==0 || sc==0 || sr==R+1 || sc==C+1){
                    min = Math.min(min, time);
                    continue;
                }

                for(int j=0;j<4;j++){
                    int dsr = sr + dr[j];
                    int dsc = sc + dc[j];
                    //������ ��ȿ�ϰ� �ҵ� ���� ����̵� �� �� ���� ��(����϶�)
                    if(isIn(dsr,dsc, 's') && (map[dsr][dsc]=='.'||map[dsr][dsc]=='X')){
                        q.offer(new Pos(dsr, dsc, time+1));
                        map[dsr][dsc] = '@';
                    }
                }
            }


        }
    }


    static boolean isIn(int r, int c, char type){
        if(type=='f')  return r>0 && c>0 && r<=R && c<=C;
        else if(type=='s') return r>=0 && c>=0 && r<=R+1 && c<=C+1;
        return false;
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

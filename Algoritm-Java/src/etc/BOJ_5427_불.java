package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_5427_불 {
    //백준 골드4
    //BFS?
    /* 탈출구 2개일 때 안풀림
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
    //상, 하, 좌, 우
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
//                        System.out.println("불 생성! i : "+i +"    j : "+j);
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
            //불 먼저 퍼뜨리기
            // 시간별로 퍼뜨리기 위해 초기 담겨있던 불의 개수만큼만 진행하고
            //새로 이동한 불은 다음 반복때 퍼트리기
            for(int i=0, end = fires.size();i<end;i++){ //여길 바꾸니까 됐음
                Pos f = fires.poll();
                int fr = f.r;
                int fc = f.c;
//                System.out.println("fire! i는 : "+i+"   불의 위치 r " +fr+" c : "+fc);
                for(int j=0;j<4;j++){
                    int dfr = fr + dr[j];
                    int dfc = fc + dc[j];
                    //위치가 유효하고 빈공간이거나 상근이가있으면 이동
                    if(isIn(dfr, dfc, 'f') && (map[dfr][dfc]=='.' || map[dfr][dfc]=='@')){
//                    if(isIn(dfr, dfc) && (map[dfr][dfc]=='.')){
                        map[dfr][dfc] = '*';
                        fires.offer(new Pos(dfr, dfc));
                    }
                }
            }

            //상근이 이동
            for(int i=0, end=q.size();i<end;i++){
                Pos s = q.poll();
                int sr = s.r;
                int sc = s.c;
                int time = s.time;
//                System.out.println("상근이! i는 : "+i+"   상근이 위치 r " +sr+" c : "+sc+"   시간 : "+time);

                //골인했을 때
                if(sr==0 || sc==0 || sr==R+1 || sc==C+1){
                    min = Math.min(min, time);
                    continue;
                }

                for(int j=0;j<4;j++){
                    int dsr = sr + dr[j];
                    int dsc = sc + dc[j];
                    //범위가 유효하고 불도 없고 상근이도 간 적 없을 때(빈곳일때)
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

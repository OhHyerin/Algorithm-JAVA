package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S1_16918_봄버맨 {
    //백준 실버1
    //스터디 - 코테광탈

    static int R, C;  //R x C map
    static int N;   //N : N초가 흐른 후 격자판 상태
    static int[][] map;
    static int[][] copyMap;
    static int[] dr = {-1 ,1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[R+2][C+2];
        copyMap = new int[R+2][C+2];

        for(int i=1;i<=R;i++){
            String str = br.readLine();
            for(int j=0;j<C;j++){
                char tmp = str.charAt(j);
                if(tmp=='.'){
                    map[i][j+1] = -1;  //비어있는 공간
                }else{
                    map[i][j+1] = 1;  //초기 폭탄 설치 위치
                }
            }
        }

        full();  //1초때 full

        int plus = (N-1)%2;  //1초때 full 1개 빼고 나머지가 짝수인지 홀수인지

        for(int i=2;i<=N;i++){
//            System.out.println("몇 번째 turn? : "+i);
            if(i%2==0){
                full();
            }else{
                bomb();
            }
        }
        if(plus==1){  //홀수면 한번 나머지 공간에 폭탄 채우기
            full();
        }

        for(int i=1;i<=R;i++){
            for(int j=1;j<=C;j++){
                if(map[i][j]==0){
                    sb.append(".");
                }else{
                    sb.append("O");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    static void full(){
        for(int i=1;i<=R;i++){
            for(int j=1;j<=C;j++){
                map[i][j]++;
                copyMap[i][j]++;
            }
        }
//        for(int i=1;i<=R;i++){
//            for(int j=1;j<=C;j++){
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }

    }

    static void bomb(){
        full();
        Queue<Pos> queue = new LinkedList<>();
        for(int i=1;i<=R;i++){
            for(int j=1;j<=C;j++){
                if(map[i][j]==4){
                    queue.add(new Pos(i, j));
//                    copyMap[i][j] = 0;
//                    copyMap[i-1][j] = 0;
//                    copyMap[i][j-1] = 0;
//                    copyMap[i+1][j] = 0;
//                    copyMap[i][j+1] = 0;
                }
            }
        }

        while(!queue.isEmpty()){
            Pos cur = queue.poll();
            int r = cur.r;
            int c = cur.c;

            map[r][c] = 0;
            map[r-1][c] = 0;
            map[r+1][c] = 0;
            map[r][c-1] = 0;
            map[r][c+1] = 0;
        }

//        for(int i=1;i<=R;i++){
//            for(int j=1;j<=C;j++){
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }
    }


    static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }

    static class Pos{
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

}

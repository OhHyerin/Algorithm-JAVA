package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G4_1915_가장큰정사각형 {
    //dp

    static int R, C;
    static int[][] map;
    static int n = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R+1][C+1];

        for(int i=1;i<=R;i++){
            String str = br.readLine();
            for(int j=1;j<=C;j++){
                map[i][j] = str.charAt(j-1)-'0';
            }
        }

//        print();
        int max = Integer.MIN_VALUE;

        for(int i=1;i<=R;i++){
            for(int j=1;j<=C;j++){
                if(map[i][j]==0) continue;

                int min = Integer.MAX_VALUE;
                //내가 포함되지 않았을 때, 위 왼 대각 방향까지 만들어진 정사각형의 크기의 최소값 구함
                min = Math.min(map[i-1][j-1], Math.min(map[i-1][j], map[i][j-1]));
                //현재 내가 1로 사각형이니까 나까지 합하면 변의 크기가 1 증가됨
                map[i][j] = min+1;

                //지금까지 만들어진 변의 크기 중 가장 큰 값 갱신
                max = Math.max(max, map[i][j]);
//                System.out.println("i : "+(i-1)+"  j : "+(j-1));
//                print();
            }
        }

        System.out.println(max*max);


    }

    private static void print(){
        for(int i=1;i<=R;i++){
            for(int j=1;j<=C;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void bfs(int n){
        Queue<Pos> queue = new LinkedList<>();

    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }

    private static class Pos{
        int r;
        int c;
        int n;

        public Pos(int r, int c, int n) {
            this.r = r;
            this.c = c;
            this.n = n;
        }
    }
}

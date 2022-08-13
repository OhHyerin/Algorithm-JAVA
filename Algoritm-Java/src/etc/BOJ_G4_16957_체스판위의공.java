package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G4_16957_체스판위의공 {
    //dp, 그래프탐색

    static int R, C;
    static int[][] map;
    static int[] adj;
    static int[] root;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        root = new int[R*C];


        map = new int[R][C];
        adj = new int[R*C];

        for(int i=0;i<R;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<C;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                root[i*C+j] = i*C+j;
            }
        }
        System.out.println(Arrays.toString(root));

        //---------------입력 완료-------------------

        for(int r=0;r<R;r++){
            for(int c=0;c<C;c++){
                int min = map[r][c];
                int adjR = r;
                int adjC = c;
                for(int d=0;d<8;d++){
                    int nr = r+dr[d];
                    int nc = c+dc[d];

                    if(!isIn(nr, nc)) continue;
                    if(min>map[nr][nc]){
                        System.out.println("nr*C+nc : "+(nr*C+nc));
//                        adj[r*C+c] = nr*C+nc;
                        adjR = nr;
                        adjC = nc;
                        min = map[nr][nc];
                    }

                }
                adj[r*C+c] = adjR*C+adjC;
            }
        }



        for(int i=0;i<R*C;i++){
            int dotA = i;
            int dotB = adj[i];

            System.out.println("dotA : "+dotA +"   dotB : "+dotB);
            System.out.println("find(dotA) : "+find(dotA)+"   find(dotB) : "+find(dotB));

//            if(find(dotA) != find(dotB)){
//                union(dotA, dotB);
//            }
            union(dotA, dotB);
        }

        System.out.println(Arrays.toString(root));



    }

    private static int find(int x){
        if(root[x]==x) return x;
        return root[x] = find(root[x]);
    }

    private static void union(int a, int b){
        a = find(a);
        b = find(b);

        if(a<b) root[b] = a;
        else root[a] = b;
    }



    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }

    private static class Pos{
        int r;
        int c;
        int num;

        public Pos(int r, int c, int num) {
            this.r = r;
            this.c = c;
            this.num = num;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "r=" + r +
                    ", c=" + c +
                    ", num=" + num +
                    '}';
        }
    }
}

package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G4_14658_하늘에서별똥별이빗발친다 {
    //완전탐색

    static int R, C;  //R*C
    static int L;  //트램펄린 한 변의 길이
    static int K; //별똥별의 수
    static int[][] map;
    static List<Pos> stars;
    static int maxCount = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        stars = new ArrayList<>();

        for(int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            stars.add(new Pos(r, c));
        }

        for(int i=0;i< stars.size();i++){
            int count = 0;
            int startR = stars.get(i).r;
            int startC = stars.get(i).c;

            for(int c=startC-L;c<=startC;c++){
                count = 0;
                for(int s=0;s<stars.size();s++){
                    Pos cur = stars.get(s);
                    if(cur.r>=startR && cur.r<=startR+L && cur.c>=c && cur.c<=c+L){
                        count++;
                    }
                }
                maxCount = Math.max(maxCount, count);
            }

        }



        System.out.println(K-maxCount);


    }

    private static int boundOff(int r, int c){
        int count = 0;

        for(int i=r;i<r+L;i++){
            for(int j=c;j<c+L;c++){
                if(isIn(i, j) && map[i][j]==1){
                    count++;
                }
            }
        }


        return count;
    }

    private static boolean isIn(int r, int c){
        return r>0 && c>0 && r<=R && c<=C;
    }


    private static class Pos{
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pos pos = (Pos) o;
            return r == pos.r && c == pos.c;
        }

    }

}

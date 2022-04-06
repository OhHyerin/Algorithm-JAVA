package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class BOJ_G4_2239_½ºµµÄí {
    //HashSet»ç¿ë
    //Àç±Í

    static int N = 9;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Blank> blanks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[N+1][N+1];
        blanks = new ArrayList<>();

        for(int i=0;i<N;i++){
            String str = br.readLine();
            for(int j=0;j<N;j++){
                map[i][j] = str.charAt(j)-'0';
                if(map[i][j]==0){
                    blanks.add(new Blank(i, j));
                }
            }
        }
        fillblank(0);
//        System.out.println(sb);

    }

    static void fillblank(int idx){

        if(idx==blanks.size()){
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);
        }

        int r = blanks.get(idx).r;
        int c = blanks.get(idx).c;


        HashSet<Integer> hashSet = new HashSet<>();
        for(int i=1;i<=9;i++){
            hashSet.add(i);
        }

        for(int i=0;i<9;i++){
            hashSet.remove(map[r][i]);
            hashSet.remove(map[i][c]);
        }

        int sectionR = r/3*3;
        int sectionC = c/3*3;

        for(int i=sectionR;i<sectionR+3;i++){
            for(int j=sectionC; j<sectionC+3;j++){
                hashSet.remove(map[i][j]);
            }
        }

        for(int i=1;i<10;i++){
            if(hashSet.contains(i)){
                map[r][c] = i;
                fillblank(idx+1);
                map[r][c] = 0;
            }
        }

    }


    static class Blank{
        int r;
        int c;

        public Blank(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }



}

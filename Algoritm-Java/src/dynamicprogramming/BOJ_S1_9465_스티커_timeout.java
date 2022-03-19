package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S1_9465_스티커_timeout {
    //백준 실버1
    //스터디 - DP

    static int N;
    static int [][] stickers;
    static int [][] isSelect;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            stickers = new int[4][N+2];
            isSelect = new int[4][N+2];

            for(int i=1; i<=2;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=1;j<=N;j++){
                    stickers[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            Queue<Pos> pq = new PriorityQueue<>();
            for(int i=1;i<=2;i++){
                for(int j=1;j<=N;j++){
                    pq.add(new Pos(i, j, stickers[i][j]));
                }
            }

            int result = 0;

            while(!pq.isEmpty()){
                boolean isFin = true;
                for(int i=1;i<=2;i++){
                    for(int j=1;j<=N;j++){
                        if(isSelect[i][j]==0){
                            isFin = false;
                        }
                    }
                }
                if(isFin) break;

                Pos cur = pq.poll();
                int cr = cur.r;
                int cc = cur.c;

                if(isSelect[cr][cc]==0){
                    isSelect[cr][cc]=2;
                    result += stickers[cr][cc];
                    isSelect[cr-1][cc] = 1;
                    isSelect[cr+1][cc] = 1;
                    isSelect[cr][cc-1] = 1;
                    isSelect[cr][cc+1] = 1;
                }
            }


            sb.append(result).append("\n");

        } //t
        System.out.println(sb);

    }


    static class Pos implements Comparable<Pos>{
        int r;
        int c;
        int cost;

        public Pos(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "r=" + r +
                    ", c=" + c +
                    ", cost=" + cost +
                    '}';
        }

        @Override
        public int compareTo(Pos o) {
            return -(cost-o.cost);
        }
    }
}

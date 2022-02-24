package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D5_1247_최적경로 {
    //SWEA D3
    //최단경로지만, 이 문제는 완전탐색으로 해결해도 됨

    static int n;
    static Pos[] customer;
    static Pos company;
    static Pos home;
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            min = Integer.MAX_VALUE;
            sb.append('#').append(t).append(" ");
            n = Integer.parseInt(br.readLine()); //5
            customer = new Pos[n];
            visited = new boolean[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            company = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            home = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            for(int i=0;i<n;i++){
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                customer[i] = new Pos(r, c);
            }

            delivery(company, 0, 0);
            sb.append(min).append("\n");


        }//t
        System.out.println(sb);

    }

    private static void delivery(Pos cur, int index, int dist){
        if(index==n){
            dist += getDistance(cur, home);
            min = Math.min(min, dist);
            return;
        }

        for(int i=0;i<n;i++){
            if(!visited[i]){ //아직 방문 안한 집이 있으면
                visited[i] = true; //방문처리
                delivery(customer[i], index+1, dist+getDistance(cur, customer[i]));
                visited[i] = false;
            }
        }

    }

    private static int getDistance(Pos a, Pos b){
        return Math.abs(a.r-b.r) + Math.abs(a.c-b.c);
    }

    private static class Pos{
        int r, c;
        Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

}

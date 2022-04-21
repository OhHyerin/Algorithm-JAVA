package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_1405_미친로봇 {
    //dfs, 재귀
    //모든 경우를 탐색해야 하는 경우 dfs사용
    //문제를 이해하고, 좌표 잡는것만 잘하면 dfs는 어렵지 않음

    static int N;  //1~14
    //동>서>남>북
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    /*
    로봇이 움직일 수 있는 범위
    로봇의 최대 움직일 수 있는 횟수가 14이므로
    로봇의 위치가 (15,15)일 때 1~29까지 움직일 수 있음
     */
    static boolean[][] visited = new boolean[30][30];
    static double[] direct = new double[4]; //동,서,남,북
    static double answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        for(int i=0;i<4;i++){
            direct[i] = Double.parseDouble(st.nextToken())/100.0;
        }

        visited[15][15] = true;  //로봇의 시작점

        dfs(15, 15, 1, 0);
        System.out.println(answer);

    }

    private static void dfs(int r, int c, double per, int count){

        if(count==N){
            //로봇이 N번 다 움직였다면
            answer += per;
            return;
        }

        for(int d=0;d<4;d++){
            int nr = r+dr[d];
            int nc = c+dc[d];

            if(!visited[nr][nc] && per!=0){
                //방문하지 않았고, 확률이 0이 아니라면 > 이동할 수 있음
                visited[nr][nc] = true;
                dfs(nr, nc, per*direct[d], count+1); //확률 : per*direct[d]
                visited[nr][nc] = false;  //원상태로 돌려놓기
            }
        }


    }

}

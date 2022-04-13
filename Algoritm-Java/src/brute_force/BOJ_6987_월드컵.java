package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6987_월드컵 {
    //완전탐색, 백트래킹
    //경기 개수로 비교하기(재귀)

    static int[][] game;
    static int[][] match = new int[15][2];
    static boolean isPossible;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int idx = 0;
        for(int i=0;i<6;i++){
            for(int j=i+1;j<6;j++){  //게임 match 지정
                match[idx][0] = i;
                match[idx][1] = j;
                idx++;
            }
        }

        outer : for(int t=0;t<4;t++){
            st = new StringTokenizer(br.readLine());

            game = new int[6][3];

            for(int i=0;i<6;i++){ //6팀 각 이긴수, 비긴수, 진수
                int win = Integer.parseInt(st.nextToken());
                int draw = Integer.parseInt(st.nextToken());
                int lose = Integer.parseInt(st.nextToken());

                if(win+draw+lose!=5){  //한 팀의 경기 수가 5가 아니라면 불가
                    sb.append("0 ");
                    continue outer;
                }

                game[i][0] = win;
                game[i][1] = draw;
                game[i][2] = lose;
            }

            isPossible = false;
            matching(0);
            sb.append(isPossible? "1 ":"0 ");

        }
        System.out.println(sb);

    }

    private static void matching(int round){
        if(round==15){
            //15경기 다 가능하다면
            isPossible = true;
            return;
        }

        int team1 = match[round][0];
        int team2 = match[round][1];

        //승/무/패의 수가 남아있으면 모든 경우 검사
        if(game[team1][0] > 0 && game[team2][2] > 0){
            //team1:승, team2:패
            game[team1][0]--;
            game[team2][2]--;
            matching(round+1);
            //상태 다시 돌려놓기
            game[team1][0]++;
            game[team2][2]++;
        }
        if(game[team1][1] > 0 && game[team2][1] > 0){
            //team1:무, team2:무
            game[team1][1]--;
            game[team2][1]--;
            matching(round+1);
            //상태 다시 돌려놓기
            game[team1][1]++;
            game[team2][1]++;
        }
        if(game[team1][2] > 0 && game[team2][0] > 0){
            //team1:패, team2:승
            game[team1][2]--;
            game[team2][0]--;
            matching(round+1);
            //상태 다시 돌려놓기
            game[team1][2]++;
            game[team2][0]++;
        }



    }


}

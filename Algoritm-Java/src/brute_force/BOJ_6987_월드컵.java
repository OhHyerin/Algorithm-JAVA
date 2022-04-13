package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6987_������ {
    //����Ž��, ��Ʈ��ŷ
    //��� ������ ���ϱ�(���)

    static int[][] game;
    static int[][] match = new int[15][2];
    static boolean isPossible;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int idx = 0;
        for(int i=0;i<6;i++){
            for(int j=i+1;j<6;j++){  //���� match ����
                match[idx][0] = i;
                match[idx][1] = j;
                idx++;
            }
        }

        outer : for(int t=0;t<4;t++){
            st = new StringTokenizer(br.readLine());

            game = new int[6][3];

            for(int i=0;i<6;i++){ //6�� �� �̱��, ����, ����
                int win = Integer.parseInt(st.nextToken());
                int draw = Integer.parseInt(st.nextToken());
                int lose = Integer.parseInt(st.nextToken());

                if(win+draw+lose!=5){  //�� ���� ��� ���� 5�� �ƴ϶�� �Ұ�
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
            //15��� �� �����ϴٸ�
            isPossible = true;
            return;
        }

        int team1 = match[round][0];
        int team2 = match[round][1];

        //��/��/���� ���� ���������� ��� ��� �˻�
        if(game[team1][0] > 0 && game[team2][2] > 0){
            //team1:��, team2:��
            game[team1][0]--;
            game[team2][2]--;
            matching(round+1);
            //���� �ٽ� ��������
            game[team1][0]++;
            game[team2][2]++;
        }
        if(game[team1][1] > 0 && game[team2][1] > 0){
            //team1:��, team2:��
            game[team1][1]--;
            game[team2][1]--;
            matching(round+1);
            //���� �ٽ� ��������
            game[team1][1]++;
            game[team2][1]++;
        }
        if(game[team1][2] > 0 && game[team2][0] > 0){
            //team1:��, team2:��
            game[team1][2]--;
            game[team2][0]--;
            matching(round+1);
            //���� �ٽ� ��������
            game[team1][2]++;
            game[team2][0]++;
        }



    }


}

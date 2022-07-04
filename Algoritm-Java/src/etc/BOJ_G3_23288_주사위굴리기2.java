package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G3_23288_주사위굴리기2 {
    //구현, 시뮬레이션, 그래프탐색

    /*
    문제 이해 :
    1. 주사위가 이동 방향으로 한 칸 굴러간다. 만약, 이동 방향에 칸이 없다면, 이동 방향을 반대로 한 다음 한 칸 굴러간다.
    2. 주사위가 도착한 칸(x, y)에 대한 점수를 획득한다.
    3. 주사위의 아랫면에 있는 정수 A와 주사위가 있는 칸(x,y)에 있는 정수 B를 비교해 이동 방향을 결정한다.
        1) A>B인 경우 이동방향을 90도 시계방향으로 회전시킨다.  --> dr, dc를 +1
        2) A<B인 경우 이동방향을 90도 반시계방향으로 회전시킨다  --> dr, dc를 -1
        3) A=B인 경우 이동방향에 변화는 없다.
     */

    static int R, C;  //R*C
    static int K; //이동하는 횟수
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};  //북->동->남->서
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());  //이동하는 횟수

        map = new int[R+1][C+1];

        for(int i=1;i<=R;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=C;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

    }
}

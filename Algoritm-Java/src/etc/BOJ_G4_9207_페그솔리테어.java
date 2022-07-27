package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_9207_페그솔리테어 {
    //구현, 백트래킹

    static char[][] map;
    static int R = 5;
    static int C = 9;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int cntPin;
    static int leftPin;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){

            map = new char[R][C];
            leftPin = Integer.MAX_VALUE;
            cntPin = 0;

            int cnt = 0;

            for(int i=0;i<R;i++){
                String str = br.readLine();
                for(int j=0;j<C;j++){
                    map[i][j] = str.charAt(j);
                    if(map[i][j]=='o'){
                        cnt++;
                    }
                }
            }

            br.readLine();  //공백

            cntPin = cnt;  //남은 pin의 개수
            leftPin = cnt; //남은 pin의 개수 중 최소

            for(int i=0;i<R;i++){
                for(int j=0;j<C;j++){
                    if(map[i][j]=='o'){
                        dfs(i, j);
                    }
                }
            }

            int answer = cnt-leftPin; //처음 pin의 개수에서 남은 pin의 개수를 빼면 최소 이동 횟수 (움직이면 핀이 없어진다는 거니까!)


            System.out.println(leftPin +" "+answer);

        }

    }
    private static void dfs(int r, int c){
        for(int d=0;d<4;d++){
            // pin1 (인접한 핀)
            int nr1 = r+dr[d];
            int nc1 = c+dc[d];
            
            if(!isIn(nr1, nc1) || map[nr1][nc1]!='o') continue;  //핀의 인접한 곳이 범위를 벗어나거나, o가 아니라면 continue
            
            
            // pin2 (핀이 넘어간 위치)
            int nr2 = nr1+dr[d];
            int nc2 = nc1+dc[d];

            if(!isIn(nr2, nc2) || map[nr2][nc2]!='.') continue;  //넘어갈 위치가 범위를 벗어나거나 빈 곳이 아니라면 continue

            map[r][c] = '.';  //원래 자리 빈공간으로 갱신
            map[nr1][nc1] = '.';  //핀 하나 원래 있던 자리 빈 공간으로 갱신 (없어짐)
            map[nr2][nc2] = 'o';  //넘어간 자리 핀으로 설정

            cntPin--;  //핀 개수 --

            for(int i=0;i<R;i++){
                for(int j=0;j<C;j++){
                    if(map[i][j]=='o')
                        dfs(i, j);  //다음 핀 찾아감
                }
            }

            leftPin = Math.min(leftPin, cntPin);   //남은 핀의 최소 개수
            //다음 dfs를 위해 상태 돌려놓기
            map[r][c] = 'o';
            map[nr1][nc1] = 'o';
            map[nr2][nc2] = '.';

            cntPin++;  //안움직인거로~

        }

    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }
}

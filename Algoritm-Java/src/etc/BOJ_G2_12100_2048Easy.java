package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G2_12100_2048Easy {
    //πÈ¡ÿ ∞ÒµÂ2

    static int N;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new int[N+2][N+2];

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        leftMove();
        rightMove();

        for(int i=0;i<=N+1;i++){
            for(int j=0;j<=N+1;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    private static void leftMove(){
        row : for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                for(int c=j+1;c<=N;c++){
                    if(map[i][j]!=0){
                        if(map[i][j]==map[i][c]){
                            map[i][j] *= 2;
                            map[i][c] = 0;
                        }
                        map[i][c] = map[i][c+1];
                    }
                }
            }
        }
    }

    private static void rightMove(){
        for(int i=0;i<=N;i++){
            for(int j=N;j>=1;j--){
                for(int c=j-1;c>=1;c--){
                    if(map[i][j]!=0){
                        if(map[i][j]==map[i][c]){
                            map[i][j] *= 2;
                            map[i][c] = 0;
                        }
                        map[i][c] = map[i][c-1];
                    }
                }
            }
        }
    }




}

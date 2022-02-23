package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_B1_10163_색종이 {
    //백준 브론즈1
    //IM대비

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] map = new int[1001][1001];

        int n = Integer.parseInt(br.readLine());

        for(int i=1;i<=n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            for(int x = X;x<X+w;x++){
                for(int y=Y;y<Y+h;y++){
                    map[x][y] = i;
                }
            }
        }

        for(int num = 1;num<=n;num++){
            int count = 0;
            for(int i=0;i<1001;i++){
                for(int j=0;j<1001;j++){
                    if(map[i][j]==num){
                        count++;
                    }
                }
            }
            System.out.println(count);
        }

    }
}

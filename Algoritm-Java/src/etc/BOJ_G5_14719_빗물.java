package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_14719_ºø¹° {
    //½Ã¹Ä·¹ÀÌ¼Ç, ±¸Çö

    static int R, C;
    static int[][] map;
    static int[] height;
    static int first, second;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        height = new int[C];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<C;i++){
            int h = Integer.parseInt(st.nextToken());
            height[i] = h;

            if(first<=h){
                second = first;
                first = h;
            }

            for(int r=0;r<h;r++) {
                map[r][i] = 1;
            }
        }

        System.out.println(first);
        System.out.println(second);

        int sum = 0;
        for(int i=0;i<C;i++){
            if(height[i]!=first || height[i]!=second){
                sum += (second-height[i]);
            }
        }
        System.out.println(sum);


    }
}

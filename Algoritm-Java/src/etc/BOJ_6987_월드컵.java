package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6987_월드컵 {

    static int[][] worldcup = new int[4][18];
    static int[] result = new int[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        for(int i=0;i<4;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<18;j++){
                worldcup[i][j] = Integer.parseInt(st.nextToken());
            }
        }
//---------------------입력완료----------------------
        outer : for(int i=0;i<4;i++){
            int row_sum = 0;
            for(int j=0;j<18;j++){
                row_sum+=worldcup[i][j];
            }
//            if(row_sum!=30){
//                System.out.print(0+" ");
//                continue;
//            }
            int win_sum = 0;
            int lose_sum = 0;
            int draw_sum = 0;
            int draw_count = 0;
            int total_sum = 0;
            for(int c=0;c<=15;c+=3){
                if (worldcup[i][c]==6 || worldcup[i][c]==6 || worldcup[i][c]==6){
                    //이 조건은 없어도 될 것 같은데?
                    System.out.println(0+" ");
                    continue outer;
                }
                win_sum += worldcup[i][c];
                draw_sum += worldcup[i][c+1];
                lose_sum += worldcup[i][c+2];
                if(worldcup[i][c+1]!=0){
                    draw_count++;
                }
                total_sum = worldcup[i][c]+worldcup[i][c+1]+worldcup[i][c+2];
                if(total_sum!=5){
//                    System.out.println(i);
                    System.out.print(0+" ");
                    continue outer;
                }
            }
            if(win_sum==lose_sum){
                if(draw_sum%2==0 && draw_count%2==0){
//                    System.out.println(i);
                    System.out.print(1+" ");
                }else{
//                    System.out.println(i);
                    System.out.print(0+" ");
                }
            }else{
//                System.out.println(i);
                System.out.print(0+" ");
            }

        }
    }
}

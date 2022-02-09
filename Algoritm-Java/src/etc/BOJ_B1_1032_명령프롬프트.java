package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_B1_1032_명령프롬프트 {


    static char[][] input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        input = new char[N][];

        for(int i=0;i<N;i++){
            input[i] = br.readLine().toCharArray();
        }
        int len = input[0].length;
        for(int c=0;c<len;c++){
            boolean isSame=true;
            for(int r=1;r<N;r++){
                if(input[r-1][c]!=input[r][c]){
                    isSame = false;
                }
            }
            if(isSame){
                sb.append(input[0][c]);
            }else{
                sb.append("?");
            }
        }

        System.out.println(sb);

    }

}

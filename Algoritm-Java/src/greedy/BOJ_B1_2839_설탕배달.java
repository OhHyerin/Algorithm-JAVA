package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_B1_2839_설탕배달 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int cnt = 0;

        while(true){
            if(n%5==0){
                //5로 나누어떨어지면
                cnt += n/5;
                sb.append(cnt);
                break;
            }
            else{
                n -= 3;
                cnt++;
            }
            if(n<0){
                //5랑 3으로 구성하지못하면
                sb.append("-1");
                break;
            }
        }
        System.out.println(sb);
    }
}

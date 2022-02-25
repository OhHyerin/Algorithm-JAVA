package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S4_10158_개미 {
    //IM대비
    //시간이 0.15초라 O(1)의 시간복잡도가 되도록 해야함
    //while문으로도 다시 풀어보기


    static int W, H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int t = Integer.parseInt(br.readLine());
//        int count = t % (W * H);  //(W+H)만큼 반복하면 제자리로 돌아오니까


        x = W-Math.abs(W-(x+t)%(2*W));
        y = H-Math.abs(H-(y+t)%(2*H));

        sb.append(x).append(" ").append(y);
        System.out.println(sb);


    }
}

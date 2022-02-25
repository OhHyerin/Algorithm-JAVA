package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S4_10158_���� {
    //IM���


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
//        int count = t % (W * H);  //(W+H)��ŭ �ݺ��ϸ� ���ڸ��� ���ƿ��ϱ�


        x = W-Math.abs(W-(x+t)%(2*W));
        y = H-Math.abs(H-(y+t)%(2*H));

        sb.append(x).append(" ").append(y);
        System.out.println(sb);


    }
}

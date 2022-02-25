package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S3_1244_스위치켜고끄기 {
    //백준 실버3

    static int N;
    static boolean[] bolb;
    static int S; //학생수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        bolb = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if (n == 0) {
                bolb[i] = false;
            } else {
                bolb[i] = true;
            }
        }

//        System.out.println(Arrays.toString(bolb));

        S = Integer.parseInt(br.readLine());
        for (int i = 0; i < S; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            if (n == 1) {
                boy(Integer.parseInt(st.nextToken()));
//                System.out.println("boy : " + Arrays.toString(bolb));

            } else {
                girl(Integer.parseInt(st.nextToken()));
//                System.out.println("girl : " + Arrays.toString(bolb));
            }
        }

        for (int i = 1; i < bolb.length; i++) {
            if (bolb[i] == false) {
                sb.append("0 ");
            } else {
                sb.append("1 ");
            }
            if(i%20==0){
                sb.append("\n");
            }
        }
        System.out.println(sb);

    }

    public static void boy(int n) {
        for (int i = 1; i <= N; i++) {
            if (i % n == 0) {
                switchBolb(i);
            }
        }
    }

    public static void girl(int n) {
        int how = 0;
        int index = 1;
        while (index < n && n+index<=N) {
            if (bolb[n - index] == bolb[n + index]) {
                how++;
                index++;
            } else {
                break;
            }
        }
//        System.out.println("how : " + how);

        for (int i = n - how; i <= n + how; i++) {
//            System.out.println("i : "+i);
            switchBolb(i);
        }
    }

    public static void switchBolb(int i) {
        if (bolb[i] == true) bolb[i] = false;
        else if (bolb[i] == false) bolb[i] = true;
    }

}

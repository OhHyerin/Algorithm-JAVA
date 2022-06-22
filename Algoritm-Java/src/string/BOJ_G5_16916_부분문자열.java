package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_16916_부분문자열 {
    //문자열, KMP

    static String ful;
    static String sub;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ful = br.readLine();
        sub = br.readLine();

        int fulLen = ful.length();
        int subLen = sub.length();
        boolean isPossible = false;

        outer:
        for (int i = fulLen - 1; i >= subLen - 1; i--) {
            int cnt = 0;
            int index = i;
            inner:
            for (int j = subLen - 1; j >= 0; j--) {
                if (ful.charAt(index) == sub.charAt(j)) {
                    cnt++;
                    index--;

                } else {
                    continue outer;

                }
            }
            if (cnt == subLen) {
                isPossible = true;
                break;
            }
        }
        if (isPossible) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }

    }
}

package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_10808_¾ËÆÄºª°³¼ö {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        char[] chars = new char[26];
        int[] index = new int[27];

        chars = str.toCharArray();
        Arrays.fill(index, 0);

        for(int i=0;i<chars.length;i++){
            index[chars[i]-96]++;
        }
        for(int i=1;i<index.length;i++){
            System.out.print(index[i]+" ");
        }

    }
}

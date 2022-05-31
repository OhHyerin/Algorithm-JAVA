package data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class BOJ_S4_25192_¿ŒªÁº∫π‡¿∫∞ı∞ı¿Ã {

    static int N;
    static HashSet<String> hashSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        hashSet = new HashSet<>();
        int count = 0;

        for(int i=0;i<N;i++){
            String str = br.readLine();

            if(str.equals("ENTER")){
                count += hashSet.size();
                hashSet.clear();
            }else{
                hashSet.add(str);
            }
        }
        count += hashSet.size();

        System.out.println(count);

    }
}

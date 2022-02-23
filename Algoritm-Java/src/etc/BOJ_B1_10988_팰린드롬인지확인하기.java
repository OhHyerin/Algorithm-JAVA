package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_B1_10988_팰린드롬인지확인하기 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int size = str.length();

        boolean check = true;

        for(int i=0;i<size/2;i++){
            if(str.charAt(i)!=str.charAt(size-i-1)){
                check = false;
                break;
            }
        }
        if(check==false){
            System.out.println(0);
        }else{
            System.out.println(1);
        }
    }
}

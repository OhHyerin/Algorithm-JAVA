package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_B1_10988_팰린드롬인지확인하기 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        char[] chars = str.toCharArray();

        int size = str.length();

        boolean check = true;

        if(size%2!=0){
            int mid = size/2+1;
            for(int i=1;i<mid-1;i++){
                if(chars[mid-i]!=chars[mid+i]){
                    check = false;
                    break;
                }
            }
        } else{
            int mid = size/2;
            for(int i=1;i<mid;i++){
                if(chars[mid-i]+1!=chars[mid+i]){
                    check = false;
                    break;
                }
            }
        }

        if(check==false){
            System.out.println(0);
        }else{
            System.out.println(1);
        }
    }
}

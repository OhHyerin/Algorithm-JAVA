package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15787 {
    //백준
    //기차가 어둠을 헤치고 은하수를(실버2)

    static int n, m;
    static Array mission;
    static int[][] train;
    static int[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String[] str = br.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);

        List<Integer> mission = new ArrayList<Integer>();
        train = new int[n][20];

        for(int i=0;i<n;i++){
            str = br.readLine().split(" ");
            for(int j=0;j<m;j++){
                int c = Integer.parseInt(str[j]);
//                mission.add(Integer.parseInt(str[j]));
//                mission(c, i, )

                if(str[j]=="/n"){
                    break;
                }
            }
        }

        int count = 0;
        boolean able = true;

        for(int i=0;i<n;i++){
            for(int j=0;j<20;j++){
                if(check[j]==train[i][j]){
                    able = false;
                    break;
                }
            }
            if(able){
                count++;
            }
        }

        System.out.println(count);

    }

    static void mission(int kind, int i, int x){
        if(kind==1) {
            if (train[i][x] == 0) {
                train[i][x] = 1;
            }
        } else if(kind==2){
            if(train[i][x]==1){
                train[i][x] = 0;
            }
        }else if(kind==3) {
            for (int index = 18; i >=0 ; i--) {
                if (x != 19) {
                    train[i][x + 1] = train[i][x];
                    train[i][0] = 0;
                }
            }
        }else if(kind==4){
            for(int index=0;i<20;i++){
                if(x!=0){
                    train[i][x] = train[i][x+1];
                    train[i][19] = 0;
                }
            }
        }
    }



}

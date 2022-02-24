package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20546_기적의매매법 {
    //백준 브론즈1
    //그리디?

    static int[] stock = new int[14];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int asset = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0;i<14;i++){
            stock[i] = Integer.parseInt(st.nextToken());
        }

        int jun = 0, sung = 0;
        jun = bnp(asset);
        sung = timing(asset);
        if(jun>sung){
            System.out.println("BNP");
        }else if(jun==sung){
            System.out.println("SAMESAME");
        }else{
            System.out.println("TIMING");
        }

    }

    static int bnp(int asset){
        int count = 0;
        int charge = 0;
        for(int i=0;i<14;i++) {
            if (asset >= stock[i]) {
                while(asset>0){
                    if(asset-stock[i]<0){
//                        charge = asset;
                        break;
                    }else {
                        asset -= stock[i];
                        count++;
                    }
                }
            }
        }
        return count*stock[13]+asset;
    }

    static int timing(int asset){
        int charge = 0, count = 0, curStock=0;
        for(int i=3;i<14;i++){
            if(stock[i]-stock[i-1]<0 && stock[i-1]-stock[i-2]<0 && stock[i-2]-stock[i-3]<0){
                //3일째 가격이 하락하고
                if(asset>=stock[i]){
                    //갖고잇는 자산이 주식가격보다 많으면
                    //주식 사!
                    int t = asset/stock[i];
                    count += t;
                    asset = asset-(t*stock[i]);
                }
            }else if(stock[i]-stock[i-1]>0 && stock[i-1]-stock[i-2]>0 && stock[i-2]-stock[i-3]>0){
                //3일째 가격이 상승하면 //주식 팔아!
                if(count != 0) {
                    asset = asset + (count * stock[i]);
                     count = 0;
                }
            }
        }
        return asset+(count*stock[13]);
//            return count*stock[13]+charge;

    }
}

package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JO_1828_냉장고 {
    //정올
    //그리디

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Obj[] objs = new Obj[n];

        for(int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int min = Integer.parseInt(st.nextToken());
            int max = Integer.parseInt(st.nextToken());
            objs[i] = new Obj(min, max);
        }
        Arrays.sort(objs);
        int max = 0;
        int cnt = 1;
        for(int i=1;i<objs.length;i++){
            if(max<objs[i].min){
                cnt++;
                max = objs[i].max;
            }
        }
        System.out.println(cnt);
    }

    static class Obj implements Comparable<Obj>{
        int min;
        int max;

        Obj(int min, int max){
            this.min = min;
            this.max = max;
        }

        @Override
        public int compareTo(Obj o) {
            int val = this.max-o.max;
            if(val != 0) return val; //max 정렬

            return this.min - o.min; //max같다면 min정렬
        }
    }

}

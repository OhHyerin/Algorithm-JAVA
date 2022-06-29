package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G2_1781_컵라면 {
    //자료구조, 그리디, 우선순위큐


    static int N;
    static int maxDay = Integer.MIN_VALUE;
    static Queue<Quest> queue;
    static int[] days;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
//        list = new ArrayList<>();
        queue = new PriorityQueue<>();

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            int n = i;
            int d = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            queue.add(new Quest(n, d, r));
            maxDay = Math.max(maxDay, d);
        }

        days = new int[maxDay+1];  //데드라인의 가장 큰 day까지 days 배열 생성

        int sum = 0;

        while(!queue.isEmpty()){
            Quest cur = queue.poll();

            if(days[cur.dead]!=0){
                //이미 해당 날에 문제가 차있으면
                for(int i= cur.dead;i>0;i--){
                    if(days[i]==0){
                        days[i] = cur.num;
                        sum += cur.ramen;
                    }
                }
            }else{
                days[cur.dead] = cur.num;
                sum += cur.ramen;
            }


        }


        System.out.println(sum);



    }

    private static class Quest implements Comparable<Quest>{
        int num;
        int dead;
        int ramen;

        public Quest(int num, int dead, int ramen) {
            this.num = num;
            this.dead = dead;
            this.ramen = ramen;
        }

        @Override
        public int compareTo(Quest o) {
            return (ramen-o.ramen)*-1; //컵라면 많은 순으로 내림차순
        }
    }

}

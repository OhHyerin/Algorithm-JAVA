package data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G2_1781_컵라면 {
    //자료구조, 그리디, 우선순위큐


    static int N;
//    static int maxDay = Integer.MIN_VALUE;
    static Queue<Quest> queue;
//    static int[] days;

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
//            maxDay = Math.max(maxDay, d);
        }

        Queue<Integer> pq = new PriorityQueue<>();

        while(!queue.isEmpty()){
            Quest cur = queue.poll();
            pq.add(cur.ramen);
            while(pq.size()>cur.dead){
                pq.poll();
            }
        }

        int sum = 0;

        while(!pq.isEmpty()){
            sum += pq.poll();
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
            if(dead==o.dead){
                return (ramen-o.ramen)*-1; //컵라면 많은 순으로 내림차순
            }else{
                return dead-o.dead;  //데드라인 짧은 순으로
            }
        }


        @Override
        public String toString() {
            return "Quest{" +
                    "num=" + num +
                    ", dead=" + dead +
                    ", ramen=" + ramen +
                    '}';
        }
    }

}

package minimum_spanning_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G3_13418_학교탐방하기 {
    //최소스패닝트리, MST

    static int N; //건물의 개수
    static int M; //도로의 개수
    static List<Pos> list;
    static int[] root;
    static int min, max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //0부터 시작해야되니까 +1씩
        N = Integer.parseInt(st.nextToken())+1;
        M = Integer.parseInt(st.nextToken())+1;

        list = new ArrayList<>();
        root = new  int[N+2];

        for(int i=1;i<=N;i++){
            root[i] = i;
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int  to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list.add(new Pos(from, to, weight));
            list.add(new Pos(to, from, weight));
        }


        Collections.sort(list);

        //최소 비용 (오름차순)
        for(int i=0;i<list.size();i++){
            Pos pos = list.get(i);

            if(find(pos.from) != find(pos.to)){
                union(pos.from, pos.to);

                if(pos.weight==0){
                    min++;
                }
            }
        }

        for(int i=1;i<=N;i++){
            root[i] = i;
        }

        //최대 비용 (내림차순)
        for(int i=list.size()-1;i>=0;i--){
            Pos pos = list.get(i);

            if(find(pos.from) != find(pos.to)){
                union(pos.from, pos.to);

                if(pos.weight==0){
                    max++;
                }
            }
        }

        max *= max;
        min *= min;

//        System.out.println("max : "+max);
//        System.out.println("min : "+min);


        System.out.println(Math.abs(max - min));


    }

    private static int find(int x){
        if(root[x]==x) return x;
        return root[x] = find(root[x]);
    }

    private static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a<b) root[b] = a;
        else root[a] = b;
    }

    static class Pos implements Comparable<Pos>{
        int from;
        int to;
        int weight;

        public Pos(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Pos o) {
            return weight-o.weight;
        }
    }
}

package minimum_spanning_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ_1647_도시분할계획 {
    //백준 골드4
    //MST - 크루스칼

    static int n, m;
    static ArrayList<Node> list;
    static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        root = new int[n+1];
        for(int i=1; i<=n;i++){
            root[i] = i;
        }

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int dotA = Integer.parseInt(st.nextToken());
            int dotB = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            list.add(new Node(dotA, dotB, dist));
        }


        //-----------입력완료------------------
        int answer = 0;
        int fin = 0;
        Collections.sort(list);
        for(int i=0;i<list.size();i++){
            int dotA = list.get(i).nodeA;
            int dotB = list.get(i).nodeB;
            int dist = list.get(i).distance;

            if(findCycle(dotA) != findCycle(dotB)){
                unionRoot(dotA, dotB);
                fin = dist;
                answer += dist;
            }

        }
        answer -=fin;

        System.out.println(answer);
    }

    static int findCycle(int x){
        if(root[x]==x) return x;
        return root[x] = findCycle(root[x]);

    }

    static void unionRoot(int a, int b){
        a = findCycle(a);
        b = findCycle(b);
        if(a<b) root[b] = a;
        else root[a] = b;
    }

    static class Node implements Comparable<Node>{

        private int nodeA;
        private int nodeB;
        private int distance;

        public Node(int nodeA, int nodeB, int distance) {
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.distance = distance;
        }

        public int getNodeA() {
            return nodeA;
        }

        public int getNodeB() {
            return nodeB;
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public int compareTo(Node o) {
            return distance-o.distance;
        }
    }
}

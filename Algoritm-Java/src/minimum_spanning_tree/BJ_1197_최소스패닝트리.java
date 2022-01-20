package minimum_spanning_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ_1197_최소스패닝트리 {
    //백준 골드4
    //MST - Kruskal
    //1. 그래프의 간선들을 가중치의 오름차순으로 정렬한다.
    //2. 간선을 하나씩 확인하며 현재의 간선이 사이클을 발생시키는지 확인한다.
    //   즉, 가장 낮은 가중치를 먼저 선택한다.
    //   사이클을 형성하는 간선을 제외한다.
    //3. 해당 간선을 현재의 MST집합에 추가한다.


    static int v, e;  // v : 정점, e : 간선
    static ArrayList<Node> list; // 연결정보 list
    static int [] root;  // 사이클 확인 베열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        root = new int[10001]; // 정점 갯수만큼
        for (int i=1;i<=v;i++){
            root[i] = i;
        }
        list = new ArrayList<>();

        for(int i=0;i<e;i++){
            st = new StringTokenizer(br.readLine());

            int dotA = Integer.parseInt(st.nextToken());
            int dotB = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            list.add(new Node(dotA, dotB, dist));

        }
        //----------------입력 완료--------------------
        int answer=0;
        //간선들을 가중치의 오름차순으로 정렬
        Collections.sort(list);

        for(int i=0;i<list.size();i++){
            //리스트를 순회하며
            int dist = list.get(i).distance;
            int dotA = list.get(i).nodeA;
            int dotB = list.get(i).nodeB;
            //사이클이 발생하는 경우 검출
            if(findCycle(dotA) != findCycle(dotB)){
                //점A와 점B의 부모가 다르다면
                union(dotA, dotB);
                answer += dist;
            }
        }
        System.out.println(answer);
    }

    public static int findCycle(int x){
        //루트노드가 아니라면, 루트노드를 찾을 때까지 재귀적으로 호출
        if(x==root[x]) return x;

        return root[x] = findCycle(root[x]);
    }

    public static void union(int a, int b){
        //매개변수 점 a,b의 루트노드를 찾아서
        a = findCycle(a);
        b = findCycle(b);
        //더 작은 숫자의 root노드로 두 점의 루트노드 동일하게 설정(합침)
        if(a<b) root[b] = a;
        else root[a] = b;

    }


    static class Node implements Comparable<Node>{

        private int distance;
        private int nodeA;
        private int nodeB;

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

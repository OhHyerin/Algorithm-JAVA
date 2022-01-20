package minimum_spanning_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BJ_1922_네트워크연결 {
    //백준 (골드4)
    //MST
    //1. 그래프의 간선들을 가중치의 오름차순으로 정렬한다.
    //2. 간선을 하나씩 확인하며 현재의 간선이 사이클을 발생시키는지 확인한다.
    //   즉, 가장 낮은 가중치를 먼저 선택한다.
    //   사이클을 형성하는 간선을 제외한다.
    //3. 해당 간선을 현재의 MST집합에 추가한다.

    static int n, m; //n:정점수, m:간선수
    static ArrayList<Edge> edges; //모든 간선을 담을 리스트
    static int result = 0; //최종 비용을 담을 변수
    static int[] parent; //부모 테이블 초기화

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//        n = Integer.parseInt(st.nextToken());
//        m = Integer.parseInt(st.nextToken());
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        parent = new int[100001];
        //부모 테이블상에서, 부모를 자기 자신으로 초기화
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        //간선 담을 리스트
        edges  = new ArrayList<>();

        //모든 간선에 대한 정보를 입력 받기
        for (int i = 0; i < m; i++) {
//            st = new StringTokenizer(br.readLine(), " ");
//            int a = Integer.parseInt(st.nextToken());
//            int b = Integer.parseInt(st.nextToken());
//            int cost = Integer.parseInt(st.nextToken());
            int a = sc.nextInt();
            int b = sc.nextInt();
            int cost = sc.nextInt();
            edges.add(new Edge(cost, a, b));
        }

        //간선을 비용순으로 정렬
        Collections.sort(edges);

        //간선을 하나씩 확인하며
        for (int i = 0; i < edges.size(); i++) {
            int cost = edges.get(i).getDistance();
            int a = edges.get(i).getNodeA();
            int b = edges.get(i).getNodeB();
            //사이클이 발생하지 않는 경우에만 집합에 포함
            if (findParent(a) != findParent(b)) {
                unionParent(a, b);
                result += cost;
            }
        }
        System.out.println(result);
    }


    //특정 원소가 속한 집합을 찾기
    public static int findParent(int x){
        //루트노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
        if(x==parent[x]) return x;
        return parent[x] = findParent(parent[x]);
    }

    //두 원소가 속한 집합을 합치기
    public static void unionParent(int a, int b){
        a = findParent(a);
        b = findParent(b);
        if(a<b) parent[b] = a;
        else parent[a] = b;
    }


    static class Edge implements Comparable<Edge>{
        private int distance;
        private int nodeA;
        private int nodeB;

        public Edge(int distance, int nodeA, int nodeB){
            this.distance = distance;
            this.nodeA = nodeA;
            this.nodeB = nodeB;
        }

        public int getDistance() {
            return this.distance;
        }

        public int getNodeA() {
            return this.nodeA;
        }

        public int getNodeB() {
            return this.nodeB;
        }

        @Override
        public int compareTo(Edge o) {
            //거리(비용)가 짧은 것이 높은 우선순위를 가지도록 설정
            if(this.distance<o.distance){
                return -1;
            }
            return 1;
        }
    }

}

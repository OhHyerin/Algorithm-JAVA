package minimum_spanning_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_G3_1774_우주신과의교감 {
    //백준 골드3
    //스터디 MST

    //사이클이 없어야 하니까 MST
    //크루스칼
    /*
    1. 최초, 모든 간선을 가중치에 따라 오름차순으로 정렬
    2. 가중치가 가장 낮은 간선부터 선택하면서 트리를 증갓킴
        2.1) 사이클이 존재한다면 다음으로 가중치가 낮은 간선을 선택
    3. n-1개의 간선이 선택될때까지 2를 반복
     */

    //이미 연결되어있는 노드먼저 연결하고 -> 우주신들의 좌표대로 크루스칼 알고리즘으로 MST구현

    //좌표를 처리할 클래스를 따로 만들어야해서 처음 구상이 어려웠음

    static int N, M;  //N:정점개수, M:간선개수
    static int[] root;  //노드의 root노드 배열
    static ArrayList<Node> list;  //간선 관리
    static Pos[] spaces;  //우주신들의 위치 좌표값 관리하는 Pos배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 우주신의 위치 개수
        M = Integer.parseInt(st.nextToken());  // 연결되어있는 우주신의 개수
        root = new int[N+1];
        list = new ArrayList<>();
        spaces = new Pos[N+1];

        for(int i=1;i<=N;i++){
            root[i] = i;  //root노드를 저장하는 배열을 자기자신으로 초기화
        }

        //연결 되어야 하는 우주신들의 좌표
        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            spaces[i] = new Pos(i, x, y);   //우주신들의 좌표를 spaces배열에 저장
        }

        //이미 연결된 통로
        for(int i=1;i<=M;i++){
            st = new StringTokenizer(br.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());

            union(nodeA, nodeB);  //이미 연결되어있는 노드는 간선 하나로 합쳐줌(싸이클아닐때)
        }

        //모든 정점과 모든 정점들을 연결 (간선이 양방향이기때문에 j는 i+1부터 시작)
        for(int i=1;i<=N;i++){
            for(int j=i+1;j<=N;j++){
                double distance = calDistance(spaces[i], spaces[j]); //모든 노드간 거리 측정
                list.add(new Node(spaces[i].index, spaces[j].index, distance));  //모든 경우 list에 저장
            }
        }
        Collections.sort(list);  //distance작은 순서대로 오름차순

        double answer = 0.0;  //구해야 할 값 : 최소의 통로 길이 (double형)

        //크루스칼
        for(int i=0;i<list.size();i++){
            Node cur = list.get(i);

            // 간선 전체를 탐색할 수 있는 이유는
            // distance가 작은순서로 오름차순했기때문에
            // distance가 작은것부터 이미 연결되고
            // 나중엔 distance가 큰 것은 루트노드 비교로 알아서 연산되지 않음
            if(find(cur.nodeA) != find(cur.nodeB)){  //현재 노드가 연결되어있는 nodeA와 nodeB와 루트노드가 다를때만
                // 사이클이 존재한다면 다음으로 가중치가 낮은 간선을 선택
                answer += cur.distance;
                union(cur.nodeA, cur.nodeB);
            }

        }

        // 소수점 2번째까지 출력
        System.out.printf("%.2f", answer);


    }

    private static double calDistance(Pos A, Pos B){
        // 좌표상의 두 점 사이의 거리를 구하는 함수
        // 출력 방식이 double형이기 때문에 distance또한 double형으로 반환
        return Math.sqrt(Math.pow(A.x-B.x, 2)+Math.pow(A.y-B.y, 2));
    }

    private static void union(int a, int b){
        a = find(a);
        b = find(b);

        if(a<b) root[b] = a;
        else root[a] = b;
    }

    private static int find(int x){
        if(root[x]==x) return x;
        else return root[x] = find(root[x]);
    }

    private static class Pos{
        //우주신들의 번호와 좌표를 관리하는 클래스
        int index;
        int x;
        int y;

        public Pos(int index, int x, int y) {
            this.index = index;
            this.x = x;
            this.y = y;
        }
    }

    private static class Node implements Comparable<Node>{
        // 기존 MST처럼 Node를 관리
        int nodeA;
        int nodeB;
        double distance;

        public Node(int nodeA, int nodeB, double distance) {
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            // double형이라 기존 distance-o.distance는 연산안됨
            if(distance>o.distance) return 1;
            else return -1;
        }
    }
}

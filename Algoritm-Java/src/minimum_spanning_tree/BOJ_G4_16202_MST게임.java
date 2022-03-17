package minimum_spanning_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_G4_16202_MST게임 {
    //백준 골드4
    //스터디 MST
    
    //간선 하나 지울 때마다 노드가 지워지는지 확인하고
    //노드가 지워지면 root노드를 초기화해야함
    
    //간선을 지울 때 노드가 지워진다면 
    //지워지는 노드를 root노드로 갖고있는 노드만 root노드를 초기화하려했더니 힘들었음
    //크루스칼 진행하기전에 전체 root노드를 초기화해주면 간단해짐
    
    //기존 MST문제들은 무조건 MST를 만들어야해서 편하게 크루스칼을 사용하면 되지만
    //이 문제는 MST가 만들어지지 않을 수도 있어서 MST인지 판단해야하는 로직이 어려웠음

    static int N;  //N : 정점의 개수
    static int M;  //M : 간선의 개수
    static int K;  //K : 턴의 수
    static ArrayList<Node> nodes;  //간선 관리하는 리스트
    static int[] root; //각 노드의 루트노드 저장
    static int nCnt;  //간선 지울 때마다 정점의 개수를 체크하는 변수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        nodes = new ArrayList<>(); //노드를 담을 리스트 선언
        root = new int[N+1];  //노드의 루트노드를 담을 배열

        for(int i=1;i<=N;i++){
            root[i] = i;  //각 노드의 루트노드를 자기 자신으로 초기화
        }

        for(int i=1;i<=M;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            nodes.add(new Node(x, y, i));
        }
        //---------------입력완료----------------------

        //---------------첫 턴은 간선 지우지않고 출발------
        Collections.sort(nodes);  //distance기준으로 오름차순 정렬
        nCnt = N;
        sb.append(kruskal(nodes)).append(" ");
        //---------------첫 턴-------------------------

        //---------------두 번째 턴부터------------------
        for(int k=1;k<K;k++){
//            System.out.println("turn : "+k);
//            Collections.sort(nodes);
            boolean isNodeRemove = true; //간선을 지울 때 정점이 삭제되는지 확인하는 변수
            int nA = nodes.get(0).nodeA;  // 지워질 간선과 연결되어있는 노드A
            int nB = nodes.get(0).nodeB;  // 지워질 간선과 연결되어있는 노드B

            //간선의 길이만큼 체크
            for(int i=1;i<nodes.size();i++) {
                if (nA == nodes.get(i).nodeA || nA == nodes.get(i).nodeB||nB== nodes.get(i).nodeA || nB== nodes.get(i).nodeB) {
                    //만약 지워질 간선과 연결되어있는 노드가 다른 리스트에도 존재하면
                        isNodeRemove = false;  //지우면안됨
                        break;
                }
            }

            if(isNodeRemove){
                //지워질 간선과 연결되어있는 노드가 다른 리스트에 없으면
                //정점 하나가 삭제되는 것
                nCnt--;
            }
            nodes.remove(0);  //비용이 가장 작은 간선 지움
//            System.out.println(nodes);

            sb.append(kruskal(nodes)).append(" ");
        }

        System.out.println(sb);

    }

    private static int kruskal(ArrayList<Node> list){
        for(int i=1;i<=N;i++){
            root[i] = i;  //각 노드의 루트노드를 자기 자신으로 초기화
        }
        int answer = 0;  //MST 비용
        int nodeCnt = 0;  //MST를 구축하면서 세워지는 간선의 개수
        for(int i=0;i<list.size();i++){
            Node cur = list.get(i);

            if(find(cur.nodeA) != find(cur.nodeB)){
                answer += cur.distance;
                nodeCnt++;  //노드끼리 이어질 때 마다 nodeCnt를 하나씩 증가
                union(cur.nodeA, cur.nodeB);
            }
        }

//        System.out.println("nodeCnt : "+nodeCnt);
//        System.out.println("nCnt : "+nCnt);

        if(nodeCnt==nCnt-1) return answer;  //만약 MST의 간선 개수가 현재 정점의 개수보다 하나 작다면 MST비용 return
        else return 0;  // MST가 구축되지 않았다면 0 return

//        System.out.println("answer : "+answer);
//        return answer;
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


    private static class Node implements Comparable<Node>{
        int nodeA;
        int nodeB;

        int distance;

        public Node(int nodeA, int nodeB, int distance) {
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return distance-o.distance;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "nodeA=" + nodeA +
                    ", nodeB=" + nodeB +
                    ", distance=" + distance +
                    '}';
        }

    }
}

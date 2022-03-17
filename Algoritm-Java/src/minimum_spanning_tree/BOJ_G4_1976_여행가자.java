package minimum_spanning_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_1976_여행가자 {
    //백준 골드4
    //스터디 MST

    //경로의 최단경로나 최소개수를 물어보는 것이 아니기때문에(크루스칼까지 할 필요 X)
    //다른 과정 없이 union-find를 통해 같은 경로에 있는지만 확인 후 true, false 반환
    
    //원래 크루스칼은 최단거리를 구하는것이므로 경로를 다시 돌아온다면 크루스칼 문제인지 다시 생각해보아야 함

    //루트노드가 같은 루트노드라면 같은 경로에 있다고 판단할 수 있음

    static int N, M;
    static int[] root; //루트노드를 저장하는 배열
    static int[] travel; //여행경로

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());  //도시의 개수
        M = Integer.parseInt(br.readLine());  //여행 계획중인 도시의 개수
        root = new int[N+1];
        travel = new int[M];

        for(int i=1;i<=N;i++) {
            root[i] = i; //각 노드의 root를 자기자신으로 초기화
        }

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                int isConnection = Integer.parseInt(st.nextToken());

                if(isConnection==1){
                    //i번째와 j번째와 연결되어있으면 합함
                    union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++){
            travel[i] = Integer.parseInt(st.nextToken());
        }

        int startRoot = find(travel[0]); //여행 경로의 첫 번째 여행지 루트노드를 찾음
        boolean check = true;
        for(int i=1;i<M;i++){
            if(startRoot != find(travel[i])){
                check = false;
                break;
            }
        }

        if(check){
            sb.append("YES");
        }else{
            sb.append("NO");
        }

        System.out.println(sb);

    }

    private static void union(int a, int b){

        //노드 a와 b의 루트노드를 먼저 찾음
        a = find(a);
        b = find(b);

        //현재 a와 b는 각 경로의 루트노드임
        //두 노드를 합치기위해 각 루트노드 중 작은 노드로 갱신 (연결되도록)
        if(a<b) root[b] = a;
        else root[a] = b;

    }

    private static int find(int x){
        if(x==root[x]) return x;  //자기 자신이 루트노드라면 자신 반환
        return root[x] = find(root[x]);  //root노드 찾을 때 까지
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

        @Override
        public int compareTo(Node o) {
            return distance-o.distance;
        }
    }
}

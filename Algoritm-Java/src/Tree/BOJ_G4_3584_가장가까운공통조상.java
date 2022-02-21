package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_G4_3584_가장가까운공통조상 {
    //백준 골드4
    //완전이진트리 X

    //루트노드가 정해져 있지 않기 때문에 아래에서 위로 올라가면서 루트노드를 찾아야 함
    //두 정점의 높이를 같게 하고 하나씩 올라가면서 같은 정점이 나올때까지 반복복

   static int n; //n:노드의 수
    static LinkedList<Integer>[] list;
    static int[] parent;
    static int[] depth;
    static boolean[] vertex;
    static int findA, findB;  //조상을 찾아야 하는 두 노드

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            n = Integer.parseInt(br.readLine());

            list = new LinkedList[n+1]; //노드들이 연결되어있는 인접리스트
            parent = new int[n+1];
            depth = new int[n+1];
            vertex = new boolean[n+1];  //부모가 있으면 true, 없으면 false

            //인접 리스트 선언
            for(int i=1;i<=n;i++){
                list[i] = new LinkedList<Integer>();
            }

            for(int i=1;i<n;i++){
                st = new StringTokenizer(br.readLine());
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());

                vertex[child] = true;

                list[parent].add(child);
                list[child].add(parent);  //양방향으로 인접리스트 연결
            }

            //루트 노드 찾기
            int root = 0;
            for(int i=1;i<=n;i++){
                //인접리스트 모두 돌면서
                if(vertex[i]==false){
                    root = i;
                }
            }

            //공통부모를 찾을 두 노드
            st = new StringTokenizer(br.readLine());
            findA = Integer.parseInt(st.nextToken());
            findB = Integer.parseInt(st.nextToken());

            //dfs
            //각 노드 깊이와 부모노드 배열에 저장
            dfs(root, 0, -1);
            find();
            sb.append(findA).append("\n");

        }//t
        System.out.println(sb);
    }

    static void dfs(int cur, int d, int p){
        depth[cur] = d;
        parent[cur] = p;

        for(int next:list[cur]){
            if(next != p){
                dfs(next, d+1, cur);
            }
        }
    }

    static void find(){
        int depthA = depth[findA];
        int depthB = depth[findB];

        //두 노드의 깊이를 같게 맞추기
        while(depthA>depthB){
            findA = parent[findA];
            depthA--;
        }

        while(depthB>depthA){
            findB = parent[findB];
            depthB--;
        }

        //같은 depth에서 위로 올라가며 공통 부모 노드 찾기
        while(findA != findB){
            findA = parent[findA];
            findB = parent[findB];
        }
    }

}

package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S2_1260_DFS와BFS_List {
    //백준 골드5
    //배열로 풀었지만 list로도 구현해보기

    static int n, m;
    static Node[] edge;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());


        edge = new Node[n+1];  //연결된 간선 리스트

        for(int i=1;i<=m;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            edge[x] = new Node(y, edge[x]);
            edge[y] = new Node(x, edge[y]);
        }

//        for(Node l : edge){
//            System.out.println(l);
//        }

        dfs(new boolean[n+1], v);
        System.out.println();
        bfs(v);


    }

    static void dfs(boolean[]visited, int current){
        visited[current] = true;
        System.out.print(current+" ");

        for(Node temp = edge[current];temp != null; temp = temp.link){
            if(!visited[temp.val]){
                dfs(visited, temp.val);
            }
        }
    }

    static void bfs(int start){
        Queue<Integer> queue = new LinkedList<Integer>();
        boolean[] visited = new boolean[n+1];

        queue.offer(start);
        visited[start] = true;

        while(!queue.isEmpty()){
            int current = queue.poll();
            System.out.print(current+" ");

            for(Node temp = edge[current];temp !=null;temp = temp.link){
                if(!visited[temp.val]){
                    queue.offer(temp.val);
                    visited[temp.val] = true;
                }
            }
        }
    }

    static class Node{
        int val;
        Node link;

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", link=" + link +
                    '}';
        }

        Node(int val, Node link){
            this.val = val;
            this.link = link;
        }
    }
}

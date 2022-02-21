package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_S1_6118_숨바꼭질 {
    //백준 실버1
    //숨바꼭질 시리즈
    //그래프 탐색?

    static int n, m; //n:정점, m:간선
    static ArrayList<Integer> list[];
    static boolean[] visited;
    static int index = 2;
    static int max = Integer.MIN_VALUE;
    static int count = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+1];
        visited = new boolean[n+1];

        for(int i=0;i<n+1;i++){
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            list[from].add(to);
            list[to].add(from);  //양방향이니까 양쪽 방향 다 넣어주기
        }

        bfs();

        System.out.println(index+" "+max+" "+count);
    }

    static void bfs(){
       Queue<int[]> queue = new LinkedList<>();
       visited[1] = true;
       queue.offer(new int[]{1, 0});

       while(!queue.isEmpty()){
           int arr[] = queue.poll();
           int from = arr[0];
           int to = arr[1];

           if(to>max){
               //현재 max보다 크면
               max = to; //max 초기화
               index = from; //인덱스를 from으로 변경
               count = 1;  //최댓값보다 더 큰 값을 만나면 count를 1로 초기화
           } else if(to==max){
               //현재 max값과 같으면 갯수세야함
               if(index>from) index = from;
               count++;  //같은 max값 가진 개수 증가
           }
           for(int i=0;i<list[from].size();i++){
               int to_to = list[from].get(i);
               if(!visited[to_to]){
                   visited[to_to] = true;
                   queue.offer(new int[]{to_to, to+1});
               }
           }
       }


    }


}

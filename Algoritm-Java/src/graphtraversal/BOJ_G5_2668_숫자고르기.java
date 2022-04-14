package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_G5_2668_숫자고르기 {
    //그래프탐색, dfs

    static int N;
    static int[] arr;
    static List<Integer> list;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        list = new ArrayList<>();

        for(int i=1;i<=N;i++){
            visited = new boolean[N+1];
            visited[i] = true;
            /*
            dfs(1,1)일 땐 list에 3이 추가
            dfs(3,3)일 땐 list에 1이 추가
             */
            dfs(i,i);
        }

        Collections.sort(list);  //작은 순으로 출력하도록 list 오름차순 정렬

        sb.append(list.size()).append("\n");
        for(int i=0;i<list.size();i++){
            sb.append(list.get(i)).append("\n");
        }

        System.out.println(sb);


    }

    private static void dfs(int index, int start){
        if(arr[index]==start){
            //cycle 발생!
            //현재 들어온 값이 처음 시작한 index와 같다면 list에 추가
            //자신과 연결되어있는 바로 직전의 index만 추가함
            //(for문을 전체적으로 돌기때문에 순차적으로 다 들어갈 것임!)
            list.add(index);
        }
        if(!visited[arr[index]]){
            visited[arr[index]] = true;
            dfs(arr[index], start);
        }

    }
}

package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_G5_13023_ABCDE {
    //dfs

    static int N;  //사람의 수
    static int M;  //친구 관계의 수
    static ArrayList<Integer>[] list;  //인접리스트
    static boolean isPossible;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N];
        for(int i=0;i<N;i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);  //쌍방향으로 연결
            list[b].add(a);
        }

//        for(int i=0;i<N;i++){
//            System.out.print(i+" : ");
//            System.out.println(list[i]);
//        }

        outer : for(int i=0;i<N;i++){
            if(list[i].size()==0) continue ;
            for(int j=0;j<list[i].size();j++){
                dfs(i, new boolean[N], 1);  //리스트의 모든 점 탐색
                if(isPossible){  //중간에 가능한 경우 나오면
                    break outer;  //탈출
                }
            }
        }

        System.out.println(isPossible? 1:0);


    }

    private static void dfs(int cur, boolean[] visited, int depth){
        if(isPossible) return;

        if(depth==5){  //5명이 연결되어있으면
            isPossible = true;  
            return;  //탈출
        }

        visited[cur] = true;  //자기 자신 방문 처리

        for(int i=0;i<list[cur].size();i++){  //인접한 리스트 모두 돌면서
            int next = list[cur].get(i);

            if(!visited[next]){  //아직 방문하지 않은 지점 있으면
                dfs(next, visited, depth+1);  //depth+1 후 dfs재귀
            }
        }
        visited[cur] = false;  //다음 dfs에 영향 안가도록 초기화
    }
}

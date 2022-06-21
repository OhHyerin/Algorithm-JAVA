package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G4_19538_루머 {
    //그래프탐색

    /*
    문제 정리
    - 최초 유포자는 여러명일 수 있다.
    - 최초 유포자를 제외하고 스스로 루머를 만들어 믿는 사람은 없다
    - 매분 루머를 믿는 사람은 모든 주변인에게 루머를 동시에 퍼트린다.
    - 군중 속 사람은 주변인의 절반 이상이 루머를 믿을 때 본인도 루머를 믿는다.
    - 한번 믿은 루머는 계속 믿는다.
     */

    static int N;  //사람의 수
    static int M;  //최초 유포자의 수
    static ArrayList<Integer>[] list;
    static Queue<Integer> queue;
    static int[] neighbours;
    static int[] depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        list = new ArrayList[N+1];
        queue = new LinkedList<>();
        depth = new int[N+1];  //answer 저장
        Arrays.fill(depth, -1); //처음 감염자는 0, 끝까지 감염 안되면 -1
        neighbours = new int[N+1];

        for(int i=1;i<=N;i++){
            list[i] = new ArrayList<>();
        }

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            while(true){
                int n = Integer.parseInt(st.nextToken());
                if(n==0) break;
                list[i].add(n);  //입력에서 알아서 양방향으로 주어지기 때문에 여기선 단방향으로 저장해도 됨
            }
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++){
            int a = Integer.parseInt(st.nextToken());
            queue.add(a);
            depth[a] = 0;
        }

        //---------------입력완료--------------------

        bfs();

        for(int i=1;i<=N;i++){
            System.out.print(depth[i]+" ");
        }

    }

    private static void bfs(){
//        boolean[] visited = new boolean[N+1];
        while(!queue.isEmpty()){
            int cur = queue.poll();

            for(int i=0;i<list[cur].size();i++){
                //cur에 연결되어있는 모든 지인들에게 전파
                int next = list[cur].get(i);  //cur과 연결되어있는 지인

                neighbours[next] ++;  //다음 사람에게 루머 전파

                if(depth[next]==-1 && neighbours[next] >= ((list[next].size()+1)/2)) {  //새로 감염되는거고, 주변사람의 반 이상이 루머를 믿을 때
//                    visited[next] = true;  //depth로 대신 가능
                    queue.add(next);
                    depth[next] = depth[cur]+1;
                }
            }
        }
    }

}

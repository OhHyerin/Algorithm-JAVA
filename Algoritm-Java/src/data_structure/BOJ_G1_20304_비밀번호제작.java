package data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G1_20304_비밀번호제작 {
    // 백준 골드1
    // 비트연산자 + BFS
    // 비트연산자로 안풀면 시간초과

    static int N;
    static int M;
    static int[] attacks;
    static boolean[] visited;
    static Queue<Integer> queue;
    static int depth = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        attacks = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++){
            attacks[i] = Integer.parseInt(st.nextToken());
        }

        queue = new LinkedList<>();
        //해당 숫자들이 점검 되었는지 확인, 최대 거리만 구하면 되므로 boolean으로
        visited = new boolean[N+1];

        //초기 큐 생성 및 방문 처리 - 최종적으로 찾아야 하는 비밀번호와 o차이가 나는 것
        //점점 1씩 멀어지는 것 찾기
        for(int m=0;m<M;m++){
            queue.offer(attacks[m]);
            visited[attacks[m]] = true;
        }

        while(!queue.isEmpty()){
            bfs();
        }
        depth--;
        System.out.println(depth);

    }

    private static void bfs(){
        int size = queue.size();
        while(size --> 0){
            int front = queue.poll();
            //front와 1비트 차이 나는 곳을 찾는다
            //0000000001을 옆으로 한칸씩 shift하면서 기존 숫자와 매칭
            for(int i=1;i<=N;i<<=1){
                //front와 1비트가 다른 숫자 만들기
                //front에 i가 담겨있다면 빼주고 없다면 넣기
                int next;

                /*
                if((front&i)>0){
                next = frint & ~i;
                //next = front-i;
                } else{
                next = front | i;
                //next = front+i;
                }
                 */

                next = front^i;

                //새로운 수가 범위 안에 있고 아직 미방문인 지점이라면 방문처리
                if(next <= N && !visited[next]){
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
        depth++;
    }

}

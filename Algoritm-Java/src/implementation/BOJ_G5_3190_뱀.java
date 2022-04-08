package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G5_3190_뱀 {
    //구현

    static int N; //보드의 크기
    static int K; //사과의 개수
    static int[][] map;  //1 : 뱀 위치, 2 : 사과 위치
    static int L; //뱀의 방향 변환 횟수
    static int[] dr = {0, 1, 0, -1};  //동>남>서>북
    static int[] dc = {1, 0, -1, 0};
    static int time;
    static HashMap<Integer, String> directs = new HashMap<>();  //배열대신 hashmap

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); //보드의 크기
        K = Integer.parseInt(br.readLine()); //사과의 개수

        map = new int[N+2][N+2];  //r,c==0이거나 r,c==N+1이면 벽에 도착
        for(int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = 2; //사과자리
        }

         L = Integer.parseInt(br.readLine());

        for(int i=0;i<L;i++){
            st = new StringTokenizer(br.readLine());
            int turn = Integer.parseInt(st.nextToken());
            String d = st.nextToken();

            directs.put(turn, d);

        }
        move();
        System.out.println(time);
    }

    private static void move(){
        Queue<Pos> queue = new LinkedList<>();

        queue.add(new Pos(1, 1));
        int d = 0; //오른쪽먼저
        int cr = 1;
        int cc = 1;
        map[1][1] = 1;  //뱀

        while(!queue.isEmpty()){
            time++;

            int nr = cr+dr[d];
            int nc = cc+dc[d];

            if(!isIn(nr, nc)) return;  //범위 벗어나면(벽에 부딪힘) 탈출
//            if(queue.contains(new Pos(nr, nc))) {  //equals메소드를 재정의해서 사용하지만 시간이 더 오래걸림
            if(map[nr][nc]==1){ // 뱀이 자기 몸에 부딪히면 탈출
//                System.out.println("nr : " + nr+"  nc : "+nc);
                return;
            }

            if(map[nr][nc]==2){  //다음 위치가 사과면 머리만 길어짐
                queue.add(new Pos(nr, nc));
                map[nr][nc] = 1;
            }else{  //사과가 아니면
                queue.add(new Pos(nr, nc));  //머리 이동하고
                Pos remove = queue.poll();  //꼬리 자름
                map[nr][nc] = 1;  //뱀 머리위치 1로
                map[remove.r][remove.c] = 0;  //자른위치는 다시 0으로
            }

            if(directs.containsKey(time)){ //해시맵에 현재 시간이 포함되어있다면 방향 전환
                String dir = directs.get(time);
                if(dir.equals("L")){
                    d -= 1;
                    if(d<0){
                        d = 4+d;
                    }
                }else{
                    d = (d+1)%4;
                }
            }
//            System.out.println(queue);

            cr = nr;
            cc = nc;
        }
    }

    private static boolean isIn(int r, int c){
        return r>0 && c>0 && r<=N && c<=N;
    }



    private static class Pos{
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pos pos = (Pos) o;
            return r == pos.r && c == pos.c;
        }

    }
}

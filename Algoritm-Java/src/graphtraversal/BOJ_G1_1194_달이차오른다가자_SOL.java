package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G1_1194_달이차오른다가자_SOL {
    //솔루션
    //그래프탐색 - bfs
    //비트마스킹 (또는 8차원 배열)

    /*
    빈칸 : 언제나 이동할 수 있다 (.)
    벽 : 절대 이동할 수 없다(#)
    열쇠 : 언제나 이동할 수 있다. 이 곳에 처음 들어가면 열쇠를 잡는다. (a, b, c, d, e, f)
    문 : 대응하는 열쇠가 있을 때만 이동할 수 있다.(A, B, C, D, E, F)
    민식이의 현재 위치 : 빈 곳이고, 민식이가 현재 서 있는 곳이다.(0)
    출구 : 달이 차오르기 때문에, 민식이가 가야하는 곳이다. 이 곳에 오면 미로를 탈출한다.(1)
     */

    //관리할 상태가 여러개의 T/F 이므로 비트마스킹을 구상하였다.


    static int R, C;
    static char[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Pos ms;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for(int i=0;i<R;i++){
            String str = br.readLine();
            for(int j=0;j<C;j++){
                map[i][j] = str.charAt(j);
                if(map[i][j]=='0'){
                    ms = new Pos(i, j, 0);  //최초 민식이가 서있는 곳은 공백
                }
            }
        }

        System.out.println(bfs());

    }

    private static int bfs(){
        boolean[][][] visited = new boolean[R][C][1<<6];
        Queue<Pos> queue = new LinkedList<>();

        //초기화
        queue.offer(ms);
        visited[ms.r][ms.c][ms.key] = true;

        int depth = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size-- >0){
                //1. 맨 처음 노드 데려오기
                Pos head = queue.poll();

                //2. 사용한다. - 정담을 찾거나 부가적인 행동
                if(map[head.r][head.c]=='1'){
                    return depth;
                }
                //키의 위에 있다면 -> update
                if(map[head.r][head.c] >= 'a' && map[head.r][head.c]<='f'){
                    head.updateKey(map[head.r][head.c]);
                }

                //3. 자식 노드 탐색
                for(int d=0;d<4;d++) {
                    int nr = head.r + dr[d];
                    int nc = head.c + dc[d];

                    //해당 지점을 현재 키의 상태로 가본적이 없다면 go
                    if (isIn(nr, nc) && !visited[nr][nc][head.key]) {
                        if (map[nr][nc] == '#') continue;


                        //문을 못가는 경우는 - 문에 대한 키가 없을 때
                        if (map[nr][nc] >= 'A' && map[nr][nc] <= 'F' && !head.hasKey(map[nr][nc])) continue;

                        //아니면 진행
                        visited[nr][nc][head.key] = true;
                        queue.offer(new Pos(nr, nc, head.key));
                    }
                }
            }//while
            depth++;
        }

        return -1;
    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }

    private static class Pos{
        int r;
        int c;
        int key;

        public Pos(int r, int c, int key) {
            this.r = r;
            this.c = c;
            this.key = key;
        }

        public boolean hasKey(char gate){
            return (key & ( 1<< (gate-'A') ) ) > 0;
        }

        public void updateKey(char key){
            key |= ( 1 << (key-'a') );
        }
    }

}

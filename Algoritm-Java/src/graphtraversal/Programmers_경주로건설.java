package graphtraversal;

import java.util.LinkedList;
import java.util.Queue;

public class Programmers_경주로건설 {
    //bfs
    // https://programmers.co.kr/learn/courses/30/lessons/67259

    /*
    직선 : 100원
    코너 : 500원 (방향돌릴 때 한번에 600원 추가)

    0:상, 1:우, 2:하, 3:좌
     */

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int minCost = Integer.MAX_VALUE;
    static int n;
    static boolean[][] visited;

    public static void main(String[] args) {
        int[][] board = {{0,0,0},{0,0,0},{0,0,0}};
//        int[][] board = {{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}};

        System.out.println(solution(board));
    }

    static public int solution(int[][] board){
        int answer = 0;
        n = board.length;

        bfs(board);
        answer = minCost;

        return answer;
    }

    static void bfs(int[][]map){
        Queue<Pos> queue = new LinkedList<>();
        visited = new boolean[n][n];

        queue.add(new Pos(0, 0, -1, 0));  //첫 방향은 어딘지 모르니까 -1로
        visited[0][0] = true;

        while(!queue.isEmpty()){
            Pos cur = queue.poll();
//            System.out.println("몇번째");

            if(cur.r == n-1 && cur.c == n-1){  //도착 위치 도달했으면
                minCost = Math.min(minCost, cur.cost);
//                System.out.println("여기 도착");
//                return minCost;
            }

            for(int d=0;d<4;d++){
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];

                if(!isIn(nr, nc)) continue; //범위 벗어나면 continue;
                if(map[nr][nc]==1) continue;  //다음 위치가 벽이면 continue;

                int ncost;
                if(cur.dir==-1){  //맨 처음 시작할 때 방향 상관없이 +100
                    ncost  = cur.cost+100;
                }else if(cur.dir==d){  //직선 도로면
                    ncost  = cur.cost+100;
                }else{  //방향 전환하면 한번에 500+100
                    ncost = cur.cost+600;
                }

                //방문한 적 없거나 현재 ncost가 전에 방문한 값보다 더 작으면
                //미리 visited먼저 체크하고 continue하면 방문했을때보다 작은값을 못찾음
//                if(visited[nr][nc]) continue; //방문 했었으면 continue;
                if(!visited[nr][nc] ||map[nr][nc]>=ncost){
                    visited[nr][nc] = true;
//                    map[nr][nc] = Math.min(map[nr][nc],ncost);
                    map[nr][nc] = ncost;
                    queue.add(new Pos(nr, nc, d, ncost));
                }

            }

        }
//        return minCost;
    }

    static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<n && c<n;
    }

    static class Pos{
        int r;
        int c;
        int dir;
        int cost;

        public Pos(int r, int c, int dir, int cost) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cost = cost;
        }
    }

}

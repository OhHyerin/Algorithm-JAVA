package graphtraversal;

import java.util.LinkedList;
import java.util.Queue;

public class Programmers_���ַΰǼ� {
    //bfs
    // https://programmers.co.kr/learn/courses/30/lessons/67259

    /*
    ���� : 100��
    �ڳ� : 500�� (���⵹�� �� �ѹ��� 600�� �߰�)

    0:��, 1:��, 2:��, 3:��
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

        queue.add(new Pos(0, 0, -1, 0));  //ù ������ ����� �𸣴ϱ� -1��
        visited[0][0] = true;

        while(!queue.isEmpty()){
            Pos cur = queue.poll();
//            System.out.println("���°");

            if(cur.r == n-1 && cur.c == n-1){  //���� ��ġ ����������
                minCost = Math.min(minCost, cur.cost);
//                System.out.println("���� ����");
//                return minCost;
            }

            for(int d=0;d<4;d++){
                int nr = cur.r+dr[d];
                int nc = cur.c+dc[d];

                if(!isIn(nr, nc)) continue; //���� ����� continue;
                if(map[nr][nc]==1) continue;  //���� ��ġ�� ���̸� continue;

                int ncost;
                if(cur.dir==-1){  //�� ó�� ������ �� ���� ������� +100
                    ncost  = cur.cost+100;
                }else if(cur.dir==d){  //���� ���θ�
                    ncost  = cur.cost+100;
                }else{  //���� ��ȯ�ϸ� �ѹ��� 500+100
                    ncost = cur.cost+600;
                }

                //�湮�� �� ���ų� ���� ncost�� ���� �湮�� ������ �� ������
                //�̸� visited���� üũ�ϰ� continue�ϸ� �湮���������� �������� ��ã��
//                if(visited[nr][nc]) continue; //�湮 �߾����� continue;
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

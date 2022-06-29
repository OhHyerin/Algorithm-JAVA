package etc;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Programmers_카드짝맞추기 {
    //2021 카카오 블라인드 채용

    public static void main(String[] args) {
        int[][] board = {{1, 0, 0, 3}, {2, 0, 0, 0}, {0, 0, 0, 2}, {3, 0, 1, 0}};
        int r = 1;
        int c = 0;

        System.out.println(solution(board, r, c));
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int result;

    static public int solution(int[][] board, int r, int c){
        int answer = 0;

        //카드 6장을 미리 순열로 순서를 뽑음 (종류는 1, 2, 3)
        permutation(board, r, c, 0, new boolean[4], new int[3]);

        return answer;
    }

    static public void permutation(int [][] board, int r, int c, int cnt, boolean[] isSelected, int[] selected){
        if(cnt==3){
            System.out.println(Arrays.toString(selected));
            Queue<Pos> queue = new LinkedList<>();
            for(int k=0;k<3;k++){
                for(int i=0;i<4;i++){
                    for(int j=0;j<4;j++){
                        if(selected[k]==board[i][j]){
                            queue.add(new Pos(i, j));
                        }
                    }
                }
            }

            bfs(board, r, c, queue);  //bfs로 이동
            return;
        }

        for(int i=1;i<=3;i++){
            if(isSelected[i]) continue;  //기존 자리의 수들과 중복되는지 체크

            //중복되지 않았다면 사용함
            selected[cnt] = i;
            isSelected[i] = true;

            permutation(board, r, c, cnt+1, isSelected, selected);
            isSelected[i] = false;
        }

    }

    static public void bfs(int[][] board, int r, int c, Queue<Pos> order){
        Queue<Pos> queue = new LinkedList<>();
        boolean[][] visited = new boolean[4][4];

        Pos cur = order.poll();
        queue.add(new Pos(cur.r, cur.c));
    }

    static public boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<4 && c<4;
    }

    static public class Pos{
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}

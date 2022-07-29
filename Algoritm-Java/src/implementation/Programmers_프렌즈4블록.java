package implementation;

import java.util.ArrayList;
import java.util.List;

public class Programmers_프렌즈4블록 {
    //2018 카카오 블라인드
    //구현

    public static void main(String[] args) {
        String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
        System.out.println(solution(4, 5, board));
    }

    static int R, C;
    static int[][] map;
    static boolean[][] bomb;
    static int result;
    static public int solution(int m, int n, String[] board){
        int answer  = 0;

        R = m;
        C = n;

        map = new int[R][C];

        for(int i=0;i<R;i++){
            String str = board[i];
            for(int j=0;j<C;j++){
                map[i][j] = str.charAt(j)-'0';
            }
        }

//        for(int i=0;i<R;i++){
//            for(int j=0;j<C;j++){
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }

        while(true){
            int b = bomb();

//            System.out.println();
//            for(int i=0;i<R;i++){
//                for(int j=0;j<C;j++){
//                    System.out.print(bomb[i][j]);
//                }
//                System.out.println();
//            }

//            System.out.println("b : "+b);

            if(b==0) break;
            result += b;
            moveDown();

//            for(int i=0;i<R;i++){
//                for(int j=0;j<C;j++){
//                    System.out.print(map[i][j]+" ");
//                }
//                System.out.println();
//            }
//            break;
        }

        answer = result;
        return answer;
    }

    static public int bomb(){
        bomb = new boolean[R][C];

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                int cur = map[i][j];
                if(cur==0) continue;

                if(!isIn(i+1, j) || !isIn(i, j+1) || !isIn(i+1, j+1)) continue;

                if(cur==map[i+1][j] && cur==map[i][j+1] && cur==map[i+1][j+1]){
                    bomb[i][j] = true;
                    bomb[i+1][j] = true;
                    bomb[i][j+1] = true;
                    bomb[i+1][j+1] = true;
                }
            }
        }

        return count();
    }

    static public int count(){
        int count = 0;
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(bomb[i][j] && map[i][j]!=0){
                    count++;
                }
            }
        }
        return count;
    }

    static public void moveDown(){
        int[][] tempMap = new int[R][C];
        for(int c=0;c<C;c++){
            List<Integer> list = new ArrayList<>();
            for(int r=R-1;r>=0;r--){
                if(!bomb[r][c]) list.add(map[r][c]);
            }
            int idx = R-1;
            for(int r=0;r<list.size();r++){
                tempMap[idx][c] = list.get(r);
                idx--;
            }
        }

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                map[i][j] = tempMap[i][j];
            }
        }
    }

    static public boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }
}

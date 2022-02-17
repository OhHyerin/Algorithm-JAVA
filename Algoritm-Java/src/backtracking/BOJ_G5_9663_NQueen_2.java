package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_9663_NQueen_2 {
    //백준 골드5
    //백트래킹
    //2번째 풀이

    //row 한 줄에 하나의 퀸만 들어갈 수 있음.


    static int n;
    static int[] chess;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        //index가 row, 값이 col
        chess = new int[n]; //col 배열
        //chess[0] = 0열
        //chess[1] = 1열
        //chess[2] = 2열
        //...

        nQueen(0);
        System.out.println(cnt);

    }

    private static void nQueen(int row){
        if(row==n){ //row가 n까지 다 돌았으면
            cnt++; //count추가 뒤 return(모든 queen이 다 들어감)
            return;
        }

        for(int i=0;i<n;i++){
            chess[row] = i;  //일단 col에 i를 넣어보고
            if(isPossible(row)){ //직선이나 대각선상에 없으면
                nQueen(row+1); //다음 퀸 진행행
           }
        }
    }

    private static boolean isPossible(int row){
        for(int i=0;i<row;i++){
            //직선상에 있거나, 대각선에 있으면 false return
            //i행과 row행의 열 값이 같으면 직선상에 존재
            //대각선은 기울기 이용해서 찾기
            if((chess[i]==chess[row]) || (Math.abs(chess[i]-chess[row])==row-i)){
                return false;
            }
        }
        return true;
    }

}

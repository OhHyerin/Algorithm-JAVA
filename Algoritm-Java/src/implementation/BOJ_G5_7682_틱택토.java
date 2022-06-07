package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_7682_틱택토 {
    //구현

    /*
    * 빙고가 없는데 .이 있는 경우 -> invalid
    * x의 개수 - o의 개수 = 1
    2. x가 더 크면 x가 이겨야한다.
    3. x의 갯수가 o과 같으면 o가 이겨야한다.
    4. 같은 라인 빙고가 여러개면 안됨
    */

    static char[][] map;
    static int cntO, cntX;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true){
            String str = br.readLine();

            if(str.equals("end")) break;  //end입력받으면 끝

            if(!checkCount(str)){  //개수가 충족되지 않으면 invalid
                sb.append("invalid\n");
                continue;
            }

//            if(cntO+cntX==9){
//                sb.append("valid\n");
//                continue;
//            }

            if(checkBingo(str)){
                sb.append("valid\n");
            }else{
                sb.append("invalid\n");
            }


        }
        System.out.println(sb);

    }

    private static boolean checkCount(String str){
        cntO = 0;  //o의 개수
        cntX = 0;  //x의 개수

        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='O') cntO++;
            else if(str.charAt(i)=='X') cntX++;
        }

        if(cntX-cntO != 1 && cntX-cntO!=0) return false;
        else return true;
    }

    private static boolean checkBingo(String str){

        int bingoO = 0; //o의 빙고 개수
        int bingoX = 0; //x의 빙고 개수

        map = new char[3][3];

        int index = 0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                map[i][j] = str.charAt(index);
                index++;
            }
        }

//        for(int i=0;i<3;i++){
//            for(int j=0;j<3;j++){
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }

        //가로줄 검사
        if(map[0][0] == map[0][1] && map[0][1] == map[0][2]){
            if(map[0][0]=='O') bingoO++;
            else if(map[0][0]=='X') bingoX++;
        }
        if(map[1][0] == map[1][1] && map[1][1] == map[1][2]){
            if(map[1][0]=='O') bingoO++;
            else if(map[1][0]=='X') bingoX++;
        }
        if(map[2][0] == map[2][1] && map[2][1] == map[2][2]){
            if(map[2][0]=='O') bingoO++;
            else if(map[2][0]=='X') bingoX++;
        }

        //세로줄 검사
        if(map[0][0] == map[1][0] && map[1][0] == map[2][0]){
            if(map[0][0]=='O') bingoO++;
            else if(map[0][0]=='X') bingoX++;
        }
        if(map[0][1] == map[1][1] && map[1][1] == map[2][1]){
            if(map[0][1]=='O') bingoO++;
            else if(map[0][1]=='X') bingoX++;
        }
        if(map[0][2] == map[1][2] && map[1][2] == map[2][2]){
            if(map[0][2]=='O') bingoO++;
            else if(map[0][2]=='X') bingoX++;
        }

        //대각선 검사
        if(map[0][0] == map[1][1] && map[1][1] == map[2][2]){
            if(map[0][0]=='O') bingoO++;
            else if(map[0][0]=='X') bingoX++;
        }
        if(map[0][2] == map[1][1] && map[1][1] == map[2][0]){
            if(map[0][2]=='O') bingoO++;
            else if(map[0][2]=='X') bingoX++;
        }


        if(bingoX > 0) {
            //x빙고가 있으면
            if (bingoO > 0) return false; //bingoO가 있으면 안됨
            if(cntX-cntO==1) return true;
            else return false;
        }

        if(bingoO>0){
            if(cntX-cntO==0) return true;
            else return false;
        }
        if(cntX + cntO == 9) return true;
        return false;
    }
}

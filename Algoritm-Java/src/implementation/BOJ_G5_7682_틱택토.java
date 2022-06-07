package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_7682_ƽ���� {
    //����

    /*
    * ���� ���µ� .�� �ִ� ��� -> invalid
    * x�� ���� - o�� ���� = 1
    2. x�� �� ũ�� x�� �̰ܾ��Ѵ�.
    3. x�� ������ o�� ������ o�� �̰ܾ��Ѵ�.
    4. ���� ���� ���� �������� �ȵ�
    */

    static char[][] map;
    static int cntO, cntX;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true){
            String str = br.readLine();

            if(str.equals("end")) break;  //end�Է¹����� ��

            if(!checkCount(str)){  //������ �������� ������ invalid
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
        cntO = 0;  //o�� ����
        cntX = 0;  //x�� ����

        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='O') cntO++;
            else if(str.charAt(i)=='X') cntX++;
        }

        if(cntX-cntO != 1 && cntX-cntO!=0) return false;
        else return true;
    }

    private static boolean checkBingo(String str){

        int bingoO = 0; //o�� ���� ����
        int bingoX = 0; //x�� ���� ����

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

        //������ �˻�
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

        //������ �˻�
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

        //�밢�� �˻�
        if(map[0][0] == map[1][1] && map[1][1] == map[2][2]){
            if(map[0][0]=='O') bingoO++;
            else if(map[0][0]=='X') bingoX++;
        }
        if(map[0][2] == map[1][1] && map[1][1] == map[2][0]){
            if(map[0][2]=='O') bingoO++;
            else if(map[0][2]=='X') bingoX++;
        }


        if(bingoX > 0) {
            //x���� ������
            if (bingoO > 0) return false; //bingoO�� ������ �ȵ�
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

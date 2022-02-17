package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_9663_NQueen_2 {
    //���� ���5
    //��Ʈ��ŷ
    //2��° Ǯ��

    //row �� �ٿ� �ϳ��� ���� �� �� ����.


    static int n;
    static int[] chess;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        //index�� row, ���� col
        chess = new int[n]; //col �迭
        //chess[0] = 0��
        //chess[1] = 1��
        //chess[2] = 2��
        //...

        nQueen(0);
        System.out.println(cnt);

    }

    private static void nQueen(int row){
        if(row==n){ //row�� n���� �� ��������
            cnt++; //count�߰� �� return(��� queen�� �� ��)
            return;
        }

        for(int i=0;i<n;i++){
            chess[row] = i;  //�ϴ� col�� i�� �־��
            if(isPossible(row)){ //�����̳� �밢���� ������
                nQueen(row+1); //���� �� ������
           }
        }
    }

    private static boolean isPossible(int row){
        for(int i=0;i<row;i++){
            //������ �ְų�, �밢���� ������ false return
            //i��� row���� �� ���� ������ ������ ����
            //�밢���� ���� �̿��ؼ� ã��
            if((chess[i]==chess[row]) || (Math.abs(chess[i]-chess[row])==row-i)){
                return false;
            }
        }
        return true;
    }

}

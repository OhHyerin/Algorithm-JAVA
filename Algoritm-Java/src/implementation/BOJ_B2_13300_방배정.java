package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_B2_13300_����� {
    //IM���

    static int N, K;
    static int[][] room;
    static int roomCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //�л���
        K = Integer.parseInt(st.nextToken()); //�� �濡 ������ �� �ִ� �ִ� �ο� ��
        room = new int[7][2];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());  //����(��:0, ��:1)
            int y = Integer.parseInt(st.nextToken());  //�г��

            room[y][s]++;  //�� ���� �ο� ����
       }

//        for(int i=1;i<7;i++){
//            for(int j=0;j<2;j++){
//                System.out.print(room[i][j]+" ");
//            }
//            System.out.println();
//        }
        int count = 0;
        for(int i=1;i<7;i++){
            for(int j=0;j<2;j++){
                if(room[i][j]==0){
                    continue;
                } else {
                    count = count+(room[i][j]/K);
                    if(room[i][j]%K!=0){
                        count++;
                    }
                }
            }
        }

        System.out.println(count);




    }
}

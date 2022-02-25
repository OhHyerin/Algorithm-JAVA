package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_B2_13300_방배정 {
    //IM대비

    static int N, K;
    static int[][] room;
    static int roomCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //학생수
        K = Integer.parseInt(st.nextToken()); //한 방에 배정할 수 있는 최대 인원 수
        room = new int[7][2];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());  //성별(여:0, 남:1)
            int y = Integer.parseInt(st.nextToken());  //학년년

            room[y][s]++;  //방 마다 인원 배정
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

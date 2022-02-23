package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_B1_2669_���簢���װ����������Ǹ������ϱ� {
    //���� �����1
    //IM���
    //2���� �迭

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        boolean [][] map = new boolean[100][100];

        for(int i=0;i<4;i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for(int y=y1;y<y2;y++){
                for(int x=x1;x<x2;x++){
                    map[y][x]=true;
                }
            }
        }
        int count = 0;
        for(int i=0;i<100;i++){
            for(int j=0;j<100;j++){
                if(map[i][j]) count++;
            }
        }

        System.out.println(count);

    }

}

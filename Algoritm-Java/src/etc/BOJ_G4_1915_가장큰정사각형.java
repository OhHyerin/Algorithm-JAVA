package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G4_1915_����ū���簢�� {
    //dp

    static int R, C;
    static int[][] map;
    static int n = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R+1][C+1];

        for(int i=1;i<=R;i++){
            String str = br.readLine();
            for(int j=1;j<=C;j++){
                map[i][j] = str.charAt(j-1)-'0';
            }
        }

//        print();
        int max = Integer.MIN_VALUE;

        for(int i=1;i<=R;i++){
            for(int j=1;j<=C;j++){
                if(map[i][j]==0) continue;

                int min = Integer.MAX_VALUE;
                //���� ���Ե��� �ʾ��� ��, �� �� �밢 ������� ������� ���簢���� ũ���� �ּҰ� ����
                min = Math.min(map[i-1][j-1], Math.min(map[i-1][j], map[i][j-1]));
                //���� ���� 1�� �簢���̴ϱ� ������ ���ϸ� ���� ũ�Ⱑ 1 ������
                map[i][j] = min+1;

                //���ݱ��� ������� ���� ũ�� �� ���� ū �� ����
                max = Math.max(max, map[i][j]);
//                System.out.println("i : "+(i-1)+"  j : "+(j-1));
//                print();
            }
        }

        System.out.println(max*max);


    }

    private static void print(){
        for(int i=1;i<=R;i++){
            for(int j=1;j<=C;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void bfs(int n){
        Queue<Pos> queue = new LinkedList<>();

    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }

    private static class Pos{
        int r;
        int c;
        int n;

        public Pos(int r, int c, int n) {
            this.r = r;
            this.c = c;
            this.n = n;
        }
    }
}

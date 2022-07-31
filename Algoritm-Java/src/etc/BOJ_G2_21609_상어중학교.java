package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G2_21609_������б� {
    //����

    /*
    ������ ��� : -1
    ������ ��� : 0
    �Ϲ� ��� : ���� M����

     */

    static int R, C;
    static int M; //������ ����
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = R;
        M = Integer.parseInt(st.nextToken());

        map = new int[R+1][C+1];

        for(int i=1;i<=R;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=C;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        print();
        rotate();  //90�� �ݽð���� ȸ��
        print();

//        while(true){
//            findBlock();
//            rotate();  //90
//        }

    }

    private static void findBlock(){

    }

    private static void rotate(){
        for(int t=1;t<R;t++){  //�ٵ� �װ� R-1��ŭ �ݺ�,, (3�� for�� ��ġ��ʹ�,,)

            for(int depth=(R+1)/2  ; depth>0 ; depth--){  //�� ĭ�� �ݽð���� ȸ��,,
                int bound = (R+1)-depth;
                int temp = map[depth][depth];
                //��
                for(int c=depth;c<bound;c++){
                    map[depth][c] = map[depth][c+1];
                }
                //������
                for(int r=depth;r<bound;r++){
                    map[r][bound] = map[r+1][bound];
                }
                //�Ʒ���
                for(int c=bound;c>depth;c--){
                    map[bound][c] = map[bound][c-1];
                }
                //����
                for(int r=bound;r>depth;r--){
                    map[r][depth] = map[r-1][depth];
                }

                map[depth+1][depth] = temp;

            }
        }
    }

    private static void print(){
        for(int i=1;i<=R;i++){
            for(int j=1;j<=C;j++){
                System.out.print(map[i][j]+"  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static class Pos implements Comparable<Pos>{
        int r;
        int c;
        int size;

        public Pos(int r, int c, int size) {
            this.r = r;
            this.c = c;
            this.size = size;
        }

        @Override
        public int compareTo(Pos o) {
            return 0;
        }
    }
}

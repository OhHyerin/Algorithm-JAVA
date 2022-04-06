package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_G4_2239_������_��Ʈ����ŷ {
    //�޸� 13708
    //�ð� 336

    //�� ���� Ǯ�̺��� �ð��� 1/4,,,,,
    //��Ʈ����ŷ ����!

    static int N = 9;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();
    static List<Point> blanks = new ArrayList<>();
    //�� ���ڸ� �����ϰ� �ִ��� ���θ� ������ �迭
    static int [][] useMap = new int[3][9]; // 0:r, 1:c, 2:grid(0~8�� �簢��)
                                            // [0][0] -> 0�� ���� ���� ����
                                            // [1][3] -> 3�� ���� ���� ����
                                            // [2][4] -> grid�� 4���� grid�� ���� ����

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[N + 1][N + 1];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
                if (map[i][j] == 0) {
                    blanks.add(new Point(i, j));
                }
                //������ ���¸� ǥ���غ���
                else{
                     int shift = 1<<map[i][j];
                     useMap[0][i]|=shift;
                     useMap[1][j]|=shift;
                     useMap[2][i/3*3+j/3] |=shift;
                }
            }
        }

        makePerDup(0);
    }

    private static boolean makePerDup(int idx) {
        // ���� ����
        if (idx == blanks.size()) {
            // ���� ���
            for(int r=0;r<9;r++){
                for(int c=0;c<9;c++){
                    sb.append(map[r][c]);
                }
                sb.append("\n");
            }
            System.out.println(sb);
            return true;
        }

        // ��� ���̽�
        Point p = blanks.get(idx);
        for (int i = 1; i < 10; i++) {
            //p�� i�� �Ҵ��� �� �ֳ�?
            if(canUse(p, i)){
                //�����ϸ� ���� ���� ���
                map[p.r][p.c]=i;
                int shift = 1<<i;
                useMap[0][p.r] |= shift;
                useMap[1][p.c] |= shift;
                useMap[2][p.g] |= shift;

                if(makePerDup(idx+1)){
                    return true;
                };
                //�������� ���� ô
                map[p.r][p.c] = 0;

                useMap[0][p.r] &= ~shift;
                useMap[1][p.c] &= ~shift;
                useMap[2][p.g] &= ~shift;
            }

        }

        return false;
    }

    private static boolean canUse(Point p, int v){
        int shift = 1<<v;
        if( (useMap[0][p.r] & shift) > 0){
            return false;
        }else if((useMap[1][p.c] & shift) > 0){
            return false;
        }else if((useMap[2][p.g] & shift) > 0){
            return false;
        }
        return true;
    }

    static class Point {
        int r, c;
        int g;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
            this.g = r/3*3 + c/3;
        }
    }

}

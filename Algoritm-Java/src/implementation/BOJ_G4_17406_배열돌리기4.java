package implementation;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_17406_�迭������4 {
    //����, ��Ž, ��Ʈ��ŷ

    //������ ���� ���� ���� ���� -> �ش� r, c, s�� �迭 ������ -> �� �迭�� �ּڰ� ���ϱ��

   static int R, C;
    static int K;  //ȸ�� ������ ����
    static int[][] map;  //(R+1)*(C+1)�迭
    static int[][] rotate;  //K*3 �迭
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[R + 1][C + 1];
        rotate = new int[K][3];

        for (int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            rotate[i][0] = Integer.parseInt(st.nextToken());
            rotate[i][1] = Integer.parseInt(st.nextToken());
            rotate[i][2] = Integer.parseInt(st.nextToken());
        }

        //-----------------�Է¿Ϸ�---------------------

        //����
        permutation(0, new int[K], new boolean[K]);

        System.out.println(result);
    }

    /**
     * ���� ���ϴ� ����
     * @param cnt : ���� ���� ��ġ
     * @param selected : ���� int �迭
     * @param isSelected : ������ Ȯ���ϴ� boolean �迭
     */
    private static void permutation(int cnt, int[] selected, boolean[] isSelected) {
        if (cnt == K) {
//            System.out.println(Arrays.toString(selected));

            int[][] tempMap = new int[R + 1][C + 1];  //���� ���� �� ���� tempMap�迭 ���� ����
            for (int i = 1; i <= R; i++) {
                for (int j = 1; j <= C; j++) {
                    tempMap[i][j] = map[i][j];
                }
            }

            for (int i = 0; i < selected.length; i++) {
                int r = rotate[selected[i]][0];
                int c = rotate[selected[i]][1];
                int s = rotate[selected[i]][2];

                tempMap = rotateMap(r, c, s, tempMap);
            }
//            System.out.println(countMap(tempMap));
            result = Math.min(result, countMap(tempMap));  //���ؾ� �ϴ� result

            return;
        }

        for (int i = 0; i < K; i++) {
            if (isSelected[i]) continue;

            selected[cnt] = i;
            isSelected[i] = true;
            permutation(cnt + 1, selected, isSelected);
            isSelected[i] = false;

        }
    }

    /**
     * tempMap�迭���� (r-s, c-s) ~ (r+s, c+s) ���� �迭 ������
     * @param r : �߽��� r
     * @param c : �߽��� c
     * @param s : depth
     * @param tempMap : �Է� �� ��ȯ int�迭
     * @return
     */
    private static int[][] rotateMap(int r, int c, int s, int[][] tempMap) {
        for (int depth = 1; depth <= s; depth++) {
            int temp = tempMap[r - depth][c + depth];  //������ ��� �� temp�� ����
            //����
            for (int j = c + depth; j > c - depth; j--) {
                tempMap[r - depth][j] = tempMap[r - depth][j - 1];
            }
            //����
            for (int i = r - depth; i < r + depth; i++) {
                tempMap[i][c - depth] = tempMap[i + 1][c - depth];
            }
            //�Ʒ���
            for (int j = c - depth; j < c + depth; j++) {
                tempMap[r + depth][j] = tempMap[r + depth][j + 1];
            }
            //������
            for (int i = r + depth; i > r - depth; i--) {
                tempMap[i][c + depth] = tempMap[i - 1][c + depth];
            }

            tempMap[r-depth+1][c+depth] = temp;  //�� ���� �� ������ ��� �ؿ� temp ����
        }


//        for(int i=1;i<=R;i++){
//            for(int j=1;j<=C;j++){
//                System.out.print(tempMap[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();


        return tempMap;
    }

    /**
     * tempMap�迭�� �� ���� �ּڰ� ��ȯ
     * @param tempMap
     * @return
     */
    private static int countMap(int[][] tempMap){
        int min = Integer.MAX_VALUE;
        for(int i=1;i<=R;i++){
            int count = 0;  //�� ������ count�� �ʱ�ȭ
            for(int j=1;j<=C;j++){
                count += tempMap[i][j];
            }
            min = Math.min(min, count);  //�� ���� �ּڰ� ����
        }
        return min;
    }

}

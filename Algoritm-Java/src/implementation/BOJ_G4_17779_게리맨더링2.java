package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G4_17779_�Ը��Ǵ���2 {
    //����

    /*
    ���� ����
    - ������ 5���� ���ű��� ������ �ϰ�, �� ������ �ټ� ���ű� �� �ϳ��� ���ԵǾ�� �Ѵ�.
    - ���ű��� ������ ��� �ϳ� �����ؾ� �ϰ�, �� ���ű��� ���ԵǾ� �ִ� ������ ��� ����Ǿ� �־�� �Ѵ�.

     */

    static int N;
    static int[][] pop;  //�α���
    static int[][] map;  //����
    static int[] pops;  //�� ������ �α� ��
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        pop = new int[N][N];
        map = new int[N][N];
        pops = new int[6];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                pop[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        find();

        System.out.println(result);

    }

    private static void find() {
        for (int r = 1; r < N - 1; r++) {  //������� ���� �ּ� 1ĭ Ȯ��
            for (int c = 0; c < N - 2; c++) {  //�������� �����ʿ� �ּ� 2ĭ Ȯ��
                //��� d1, d2�� ���ؼ� ã��
                for (int d1 = 1; d1 < N; d1++) {  //d1 : �����
                    for (int d2 = 1; d2 < N; d2++) {  //d2 : ������

                        //��谪 ã��
                        //c+d1+d2 : c+�ö󰬴� �����͵� N�ȿ� �־�� ��
                        //r-d1 : r���� ���� �ö󰡵� 0���� Ŀ����
                        //r+d2 : r���� �Ʒ��� �������� N���� �۾ƾ� ��
                        if (c + d1 + d2 < N && r - d1 >= 0 && r + d2 < N) {
                            map = new int[N][N];  //map�迭 �ʱ�ȭ

                            //1~4�� ���� �����ֱ�
                            makeSection(0, r - 1, 0, c + d1, 1);
                            makeSection(0, r - d1 + d2, c + d1 + 1, N - 1, 2);
                            makeSection(r, N - 1, 0, c + d2 - 1, 3);
                            makeSection(r - d1 + d2 + 1, N - 1, c + d2, N - 1, 4);

                            //5�� ���� �����ֱ�
                            makeSection5(r, c, d1, d2);

//                            print();

                            //���� �� �������� �� ������ �α��� pops �迭 ä���
                            pops = new int[6];
                            for(int i=0;i<N;i++){
                                for(int j=0;j<N;j++){
                                    pops[map[i][j]] += pop[i][j];
                                }
                            }

                            Arrays.sort(pops); //pops�迭 ����

                            result = Math.min(result, pops[5]-pops[1]);

                        }

                    }

                }
            }
        }


    }

    private static void makeSection(int startR, int endR, int startC, int endC, int section) {
        //�������� �־��� ���� �״�� ���� ���ϱ�
        for (int r = startR; r <= endR; r++) {
            for (int c = startC; c <= endC; c++) {
                map[r][c] = section;
            }
        }
    }

    private static void makeSection5(int r, int c, int d1, int d2) {
        //�������� ������ ���� 5�� �������ֱ�

        //������ ����⿡�� ������ ����
        for (int i = 0; i <= d2; i++) {
            map[r - d1 + i][c + d1 + i] = 5;
        }

        //������ �Ʒ����� ����� ����
        for (int i = 0; i <= d1; i++) {
            map[r + d2 - i][c + d2 + i] = 5;
        }

        //�������� ���ʺ��� �����ʹ������� �����ϸ鼭 5�� ������ ������ 5�� ä���

        //�����
        for (int i = 0; i <= d1; i++) {
            map[r - i][c + i] = 5;  //��谪 5�� ä��
            int cur = c + i + 1;  //��谪 ������
            if (i < d1) {  //d1���� ���������� �� �ʿ� ���� (�������̱⶧����)
                while (true) {
                    if (map[r - i][cur] != 5) {  //5�� �ƴ϶��
                        map[r - i][cur] = 5;  //5�� ä����
                        cur++;
                    } else break;  //5������ �� (break)
                }
            }
        }

        //������
        for (int i = 0; i <= d2; i++) {
            map[r + i][c + i] = 5;
            int cur = c + i + 1;
            if (i < d2) {
                while (true) {
                    if (map[r + i][cur] != 5) {
                        map[r + i][cur] = 5;
                        cur++;
                    } else break;
                }
            }

        }
    }

    private static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}

package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_G4_15685_�巡��Ŀ�� {
    //�Ｚ SW ���� �׽�Ʈ ����

    static int N;
    static boolean[][] map;
    /*
    0 : ��, 1 : ��, 2 : ��, 3 : �Ʒ�
     */
    static int[] dc = {1, 0, -1, 0};
    static int[] dr = {0, -1, 0, 1};
    static ArrayList<Integer> direct;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new boolean[102][102];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());  //���� ��ǥ
            int y = Integer.parseInt(st.nextToken());  //���� ��ǥ
            int dir = Integer.parseInt(st.nextToken());  //����
            int gnr = Integer.parseInt(st.nextToken());  //����

            direct = new ArrayList<>();  //������ �� direction���� list
            direct.add(dir);  //�� ó�� ���� ���� add
            direct = makeGnr(direct, gnr);
//            System.out.println(direct);

            //���� list�� �ϼ��Ǿ����� ���� ��ǥ���� �湮�ϴ� ��� ĭ�� true�� ������ֱ�
            //boolean�迭�� ����� �� �ִ� ������ �湮 �ߴ��� ���ߴ����� Ȯ���ϹǷ�
            map[y][x] = true;
            for(int go : direct){
                y += dr[go];
                x += dc[go];
                map[y][x] = true;
            }
        }

        int answer = 0;
        for(int i=0;i<=100;i++){
            for(int j=0;j<=100;j++){
                if(map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1]){
                    if(map[i][j]) answer++;
                }
            }
        }

        System.out.println(answer);

    }

    private static ArrayList<Integer> makeGnr(ArrayList<Integer> dir, int gnr) {
        /*
        ���� ó�� ������ 0���� �־����ٸ�
        0���� : 0
        1���� : 0 1
        2���� : 0 1 2 1
        3���� : 0 1 2 1 2 3 2 1
        ...
         */

        for (int g = 0; g < gnr; g++) {
            for (int i = dir.size() - 1; i >= 0; i--) {
                dir.add((dir.get(i) + 1)%4);
            }
        }
        return dir;
    }


}

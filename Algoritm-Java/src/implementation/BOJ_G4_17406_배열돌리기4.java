package implementation;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_17406_배열돌리기4 {
    //구현, 완탐, 백트래킹

    //순열로 돌릴 순서 정한 다음 -> 해당 r, c, s로 배열 돌리기 -> 각 배열의 최솟값 구하기기

   static int R, C;
    static int K;  //회전 연산의 개수
    static int[][] map;  //(R+1)*(C+1)배열
    static int[][] rotate;  //K*3 배열
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

        //-----------------입력완료---------------------

        //순열
        permutation(0, new int[K], new boolean[K]);

        System.out.println(result);
    }

    /**
     * 순서 정하는 순열
     * @param cnt : 현재 뽑은 위치
     * @param selected : 뽑힌 int 배열
     * @param isSelected : 뽑혔나 확인하는 boolean 배열
     */
    private static void permutation(int cnt, int[] selected, boolean[] isSelected) {
        if (cnt == K) {
//            System.out.println(Arrays.toString(selected));

            int[][] tempMap = new int[R + 1][C + 1];  //순열 돌릴 때 마다 tempMap배열 새로 생성
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
            result = Math.min(result, countMap(tempMap));  //구해야 하는 result

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
     * tempMap배열에서 (r-s, c-s) ~ (r+s, c+s) 까지 배열 돌리기
     * @param r : 중심의 r
     * @param c : 중심의 c
     * @param s : depth
     * @param tempMap : 입력 및 반환 int배열
     * @return
     */
    private static int[][] rotateMap(int r, int c, int s, int[][] tempMap) {
        for (int depth = 1; depth <= s; depth++) {
            int temp = tempMap[r - depth][c + depth];  //오른쪽 상단 값 temp에 저장
            //위쪽
            for (int j = c + depth; j > c - depth; j--) {
                tempMap[r - depth][j] = tempMap[r - depth][j - 1];
            }
            //왼쪽
            for (int i = r - depth; i < r + depth; i++) {
                tempMap[i][c - depth] = tempMap[i + 1][c - depth];
            }
            //아래쪽
            for (int j = c - depth; j < c + depth; j++) {
                tempMap[r + depth][j] = tempMap[r + depth][j + 1];
            }
            //오른쪽
            for (int i = r + depth; i > r - depth; i--) {
                tempMap[i][c + depth] = tempMap[i - 1][c + depth];
            }

            tempMap[r-depth+1][c+depth] = temp;  //다 돌린 후 오른쪽 상단 밑에 temp 저장
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
     * tempMap배열의 각 행의 최솟값 반환
     * @param tempMap
     * @return
     */
    private static int countMap(int[][] tempMap){
        int min = Integer.MAX_VALUE;
        for(int i=1;i<=R;i++){
            int count = 0;  //각 열마다 count값 초기화
            for(int j=1;j<=C;j++){
                count += tempMap[i][j];
            }
            min = Math.min(min, count);  //각 열의 최솟값 저장
        }
        return min;
    }

}

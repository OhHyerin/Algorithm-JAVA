package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G4_17779_게리맨더링2 {
    //구현

    /*
    문제 설명
    - 구역을 5개의 선거구로 나눠야 하고, 각 구역은 다섯 선거구 중 하나에 포함되어야 한다.
    - 선거구는 구역을 적어도 하나 포함해야 하고, 한 선거구에 포함되어 있는 구역은 모두 연결되어 있어야 한다.

     */

    static int N;
    static int[][] pop;  //인구수
    static int[][] map;  //영역
    static int[] pops;  //각 영역별 인구 합
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
        for (int r = 1; r < N - 1; r++) {  //우상향은 위에 최소 1칸 확보
            for (int c = 0; c < N - 2; c++) {  //우하향은 오른쪽에 최소 2칸 확보
                //모든 d1, d2에 대해서 찾음
                for (int d1 = 1; d1 < N; d1++) {  //d1 : 우상향
                    for (int d2 = 1; d2 < N; d2++) {  //d2 : 우하향

                        //경계값 찾기
                        //c+d1+d2 : c+올라갔다 내려와도 N안에 있어야 함
                        //r-d1 : r에서 위로 올라가도 0보다 커야함
                        //r+d2 : r에서 아래로 내려가도 N보다 작아야 함
                        if (c + d1 + d2 < N && r - d1 >= 0 && r + d2 < N) {
                            map = new int[N][N];  //map배열 초기화

                            //1~4번 섹션 나눠주기
                            makeSection(0, r - 1, 0, c + d1, 1);
                            makeSection(0, r - d1 + d2, c + d1 + 1, N - 1, 2);
                            makeSection(r, N - 1, 0, c + d2 - 1, 3);
                            makeSection(r - d1 + d2 + 1, N - 1, c + d2, N - 1, 4);

                            //5번 섹션 나눠주기
                            makeSection5(r, c, d1, d2);

//                            print();

                            //섹션 다 나눴으면 각 구역별 인구수 pops 배열 채우기
                            pops = new int[6];
                            for(int i=0;i<N;i++){
                                for(int j=0;j<N;j++){
                                    pops[map[i][j]] += pop[i][j];
                                }
                            }

                            Arrays.sort(pops); //pops배열 정렬

                            result = Math.min(result, pops[5]-pops[1]);

                        }

                    }

                }
            }
        }


    }

    private static void makeSection(int startR, int endR, int startC, int endC, int section) {
        //문제에서 주어진 범위 그대로 구역 정하기
        for (int r = startR; r <= endR; r++) {
            for (int c = startC; c <= endC; c++) {
                map[r][c] = section;
            }
        }
    }

    private static void makeSection5(int r, int c, int d1, int d2) {
        //마름모의 오른쪽 먼저 5로 설정해주기

        //마름모 꼭대기에서 우하향 방향
        for (int i = 0; i <= d2; i++) {
            map[r - d1 + i][c + d1 + i] = 5;
        }

        //마름모 아래에서 우상향 방향
        for (int i = 0; i <= d1; i++) {
            map[r + d2 - i][c + d2 + i] = 5;
        }

        //마름모의 왼쪽부터 오른쪽방향으로 진행하면서 5를 만나기 전까지 5로 채우기

        //우상향
        for (int i = 0; i <= d1; i++) {
            map[r - i][c + i] = 5;  //경계값 5로 채움
            int cur = c + i + 1;  //경계값 다음값
            if (i < d1) {  //d1에선 오른쪽으로 갈 필요 없음 (꼭지점이기때문에)
                while (true) {
                    if (map[r - i][cur] != 5) {  //5가 아니라면
                        map[r - i][cur] = 5;  //5로 채워줌
                        cur++;
                    } else break;  //5만나면 끝 (break)
                }
            }
        }

        //우하향
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

package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_17471_게리맨더링_sol {
    //문제집 - 삼성A형 기출문제
    //완전탐색, dfs

    /*
    1. 그래프(배열) 만들기 - 조합
    2. 선거구 연결 - 그래프 탐색
    3. 인구수 파악
    */

    static int N; //지역의 개수(from 1)
    static int[] pops;
    static boolean[][] graph;
    static int diff = Integer.MAX_VALUE;
    static int findCnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()) + 1;  //0번 인덱스 버림

        pops = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N; i++) {
            pops[i] = Integer.parseInt(st.nextToken());
        }

        //graph 구성
        graph = new boolean[N][N];
        for (int n = 1; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int c = 0; c < cnt; c++) {
                int to = Integer.parseInt(st.nextToken());
                graph[n][to] = true;
            }
        }

        //그래프 확인
//        for(boolean[] row : graph){
//            System.out.println(Arrays.toString(row));
//        }

        //그룹 두 개를 나누는데 한 그룹은 여집합이므로
        //부분조합 대신 조합을 반만 돌리면 됨.
        for (int i = 1; i <= N / 2; i++) {
            combination(i, new boolean[N], 1, i);
        }

        System.out.println(diff==Integer.MAX_VALUE?-1 : diff);
    }

    /**
     * 크기에 따라 요소를 선택해보자!!
     *
     * @param toChoose 선택해야 할 개수
     * @param choosed  선택 결과
     * @param start    출발점
     * @param size     선택 해야하는 개수
     */

    private static void combination(int toChoose, boolean[] choosed, int start, int size) {
        if (toChoose == 0) {
//            System.out.println(size + " : " + Arrays.toString(choosed));
            //-------true인 그룹 탐색----------
            //시작점 찾기
            int si = getStart(choosed, true);
            findCnt = 0;
            int pop1 = dfs(si, choosed, new boolean[N], true);
            if(findCnt!=size) {
                //findCnt랑 size랑 다르면 완탐 실패
                 return;
            }

            //-------false인 그룹 탐색----------
            si = getStart(choosed, false);
            findCnt = 0;
            int pop2 = dfs(si, choosed, new boolean[N], false);
            if(findCnt!=(N-size-1)){
                //findCnt가 (전체갯수 - size)랑 다르다면 완탐 실패
                return;
            }

            diff = Math.min(diff, Math.abs(pop1-pop2));

            return;
        }

        for (int i = start; i < N; i++) {
            choosed[i] = true;
            combination(toChoose - 1, choosed, i + 1, size);
            choosed[i] = false;
        }
    }

    /**
     * @param i       방문하는 index
     * @param choosed 구성 정보
     * @param visited 방문여부
     * @param check   탐색할 값
     * @return 탐색 결과 인구 수의 합
     */
    private static int dfs(int i, boolean[] choosed, boolean[] visited, boolean check) {
        //1. 방문표시
        visited[i] = true;

        //2. 인구수 가져오기
        int pop = pops[i];
        findCnt++;  //방문했을 때 마다 1씩 증가

        //3. 자식 탐색 - 미방문, 연결되어있고, check일 것(true 또는 false)
        for (int c = 1; c < N; c++) {
            if (!visited[c] && graph[i][c] && choosed[c] == check) {
                pop += dfs(c, choosed, visited, check);
            }
        }
        return pop;
    }


    /**
     * 배열에서 check가 처음 나온 시점을 반환
     *
     * @param choosed
     * @param check
     * @return
     */

    private static int getStart(boolean[] choosed, boolean check) {
        for (int i = 1; i < choosed.length; i++) {
            if (choosed[i] == check) {
                return i;
            }
        }
        return -1;
    }

}

package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_G4_17471_게리맨더링 {
    //문제집 - 삼성A형 기출문제

    /*
    반례
8
17 42 46 81 71 8 37 12
4 2 4 5 7
5 1 3 4 5 8
2 2 4
5 1 2 3 7 8
5 1 2 6 7 8
2 5 8
4 1 4 5 8
5 2 4 5 6 7
     */

    static int N;
    static int[] popul;
    static ArrayList<Integer>[] list;
    static int diff = Integer.MAX_VALUE;
    static int findCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        popul = new int[N + 1];
        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            popul[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num; j++) {
                int temp = Integer.parseInt(st.nextToken());
                list[i].add(temp);
            }
        }

//        for(int i=1;i<=N;i++){
//            System.out.println(list[i]);
//        }

        for (int i = 1; i <= N / 2; i++) {
            combination(0, 1, new boolean[N + 1], i);
        }


        if (diff == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(diff);
        }
    }

    private static void combination(int cnt, int start, boolean[] isSelected, int size) {
        if (cnt == size) {
//            System.out.println(Arrays.toString(isSelected));
            //-----true인 그룹 탐색-------
            int startIndex = getStart(isSelected, true);
            findCnt = 0;
            int pop1 = dfs(startIndex, isSelected, new boolean[N+1], true);
            if(findCnt!=size){
                return;
            }

            //-----false인 그룹 탐색(여집합)-----------
            startIndex = getStart(isSelected, false);
            findCnt = 0;
            int pop2 = dfs(startIndex, isSelected, new boolean[N+1], false);
            if(findCnt!=(N-size)){
                return;
            }

            diff = Math.min(diff, Math.abs(pop1-pop2));

            return;
        }

        for (int i = start; i <= N; i++) {
            isSelected[i] = true;
            combination(cnt + 1, i + 1, isSelected, size);
            isSelected[i] = false;
        }

    }

    private static int dfs(int index, boolean[] selected, boolean[] visited, boolean type){
        visited[index] = true;

        int pop = popul[index];
        findCnt++;

        for(int i = 1;i<=N;i++){
            if(!visited[i] && list[index].contains(i) && selected[i]==type){
                pop += dfs(i, selected, visited, type);
            }
        }
        return pop;
    }

    private static int getStart(boolean[] selected, boolean type) {
        for (int i = 1; i < selected.length; i++) {
            if (selected[i] == type) {
                return i;
            }
        }
        return -1;
    }


}

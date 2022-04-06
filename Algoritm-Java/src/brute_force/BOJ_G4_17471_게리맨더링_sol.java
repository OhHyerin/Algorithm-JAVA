package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_17471_�Ը��Ǵ���_sol {
    //������ - �ＺA�� ���⹮��
    //����Ž��, dfs

    /*
    1. �׷���(�迭) ����� - ����
    2. ���ű� ���� - �׷��� Ž��
    3. �α��� �ľ�
    */

    static int N; //������ ����(from 1)
    static int[] pops;
    static boolean[][] graph;
    static int diff = Integer.MAX_VALUE;
    static int findCnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()) + 1;  //0�� �ε��� ����

        pops = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N; i++) {
            pops[i] = Integer.parseInt(st.nextToken());
        }

        //graph ����
        graph = new boolean[N][N];
        for (int n = 1; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int c = 0; c < cnt; c++) {
                int to = Integer.parseInt(st.nextToken());
                graph[n][to] = true;
            }
        }

        //�׷��� Ȯ��
//        for(boolean[] row : graph){
//            System.out.println(Arrays.toString(row));
//        }

        //�׷� �� ���� �����µ� �� �׷��� �������̹Ƿ�
        //�κ����� ��� ������ �ݸ� ������ ��.
        for (int i = 1; i <= N / 2; i++) {
            combination(i, new boolean[N], 1, i);
        }

        System.out.println(diff==Integer.MAX_VALUE?-1 : diff);
    }

    /**
     * ũ�⿡ ���� ��Ҹ� �����غ���!!
     *
     * @param toChoose �����ؾ� �� ����
     * @param choosed  ���� ���
     * @param start    �����
     * @param size     ���� �ؾ��ϴ� ����
     */

    private static void combination(int toChoose, boolean[] choosed, int start, int size) {
        if (toChoose == 0) {
//            System.out.println(size + " : " + Arrays.toString(choosed));
            //-------true�� �׷� Ž��----------
            //������ ã��
            int si = getStart(choosed, true);
            findCnt = 0;
            int pop1 = dfs(si, choosed, new boolean[N], true);
            if(findCnt!=size) {
                //findCnt�� size�� �ٸ��� ��Ž ����
                 return;
            }

            //-------false�� �׷� Ž��----------
            si = getStart(choosed, false);
            findCnt = 0;
            int pop2 = dfs(si, choosed, new boolean[N], false);
            if(findCnt!=(N-size-1)){
                //findCnt�� (��ü���� - size)�� �ٸ��ٸ� ��Ž ����
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
     * @param i       �湮�ϴ� index
     * @param choosed ���� ����
     * @param visited �湮����
     * @param check   Ž���� ��
     * @return Ž�� ��� �α� ���� ��
     */
    private static int dfs(int i, boolean[] choosed, boolean[] visited, boolean check) {
        //1. �湮ǥ��
        visited[i] = true;

        //2. �α��� ��������
        int pop = pops[i];
        findCnt++;  //�湮���� �� ���� 1�� ����

        //3. �ڽ� Ž�� - �̹湮, ����Ǿ��ְ�, check�� ��(true �Ǵ� false)
        for (int c = 1; c < N; c++) {
            if (!visited[c] && graph[i][c] && choosed[c] == check) {
                pop += dfs(c, choosed, visited, check);
            }
        }
        return pop;
    }


    /**
     * �迭���� check�� ó�� ���� ������ ��ȯ
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

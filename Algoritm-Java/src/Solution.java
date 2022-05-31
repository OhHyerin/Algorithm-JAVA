import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int N, M, S, E, min = Integer.MAX_VALUE;
    static int[][] matrix;
    static int[] weight, isSelected, answer;
    static boolean[] isVisited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            matrix = new int[N][N];
            weight = new int[N];
            isSelected = new int[N];
            isVisited = new boolean[N];

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                weight[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            S = st.nextToken().charAt(0) - 'A';
            E = st.nextToken().charAt(0) - 'A';

            isSelected[0] = S;
            isSelected[N - 1] = E;
            isVisited[S] = true;
            isVisited[E] = true;

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = st.nextToken().charAt(0) - 'A';
                int to = st.nextToken().charAt(0) - 'A';
                int w = Integer.parseInt(st.nextToken());
                matrix[from][to] = w;
                matrix[to][from] = w;
            }

            DFS(1, 0, weight[S], S);

            for (int n : answer) {
                System.out.printf("%c", (char) n + 'A');
            }

            System.out.println();
            System.out.println(min);
            min = Integer.MAX_VALUE;
        }

    }

    static void DFS(int cnt, int sum, int cart, int from) {

        if (cnt == N - 1) {

            int to = isSelected[cnt];
            sum += cart * matrix[from][to];

            if (min > sum) {
                min = sum;
                answer = Arrays.copyOf(isSelected, N);
            }

            return;
        }

        for (int i = 0; i < N; i++) {

            if (!isVisited[i]) {

                isVisited[i] = true;
                isSelected[cnt] = i;

                int to = isSelected[cnt];
                int next = sum + cart * matrix[from][to];

                if (next < min) {
                    DFS(cnt + 1, next, cart + weight[i], to);
                }

                isVisited[i] = false;
            }

        }
    }

}
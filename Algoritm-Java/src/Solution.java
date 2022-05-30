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

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = st.nextToken().charAt(0) - 'A';
                int to = st.nextToken().charAt(0) - 'A';
                int w = Integer.parseInt(st.nextToken());
                matrix[from][to] = w;
                matrix[to][from] = w;
            }

            Permu(1);

            for (int n : answer) {
                System.out.printf("%c", (char) n + 'A');
            }
            System.out.println();
            System.out.println(min);
            min = Integer.MAX_VALUE;
        }

    }

    static void Permu(int cnt) {
        if (cnt == N - 1) {
            int sum = Calc();
//         System.out.println(Arrays.toString(isSelected)+" "+sum);
            if (sum < min) {
                min = sum;
                answer = Arrays.copyOf(isSelected, N);
            }

            return;
        }

        for (int i = 0; i < N; i++) {
            if (!isVisited[i] && i != S && i != E) {
                isVisited[i] = true;

                isSelected[cnt] = i;
                Permu(cnt + 1);

                isVisited[i] = false;
            }
        }

    }

    static int Calc() {
        int sum = 0;
        int W = weight[S];

        for (int i = 1; i < N; i++) {

            int from = isSelected[i - 1];
            int to = isSelected[i];

            sum += matrix[from][to] * W;
            W += weight[to];

        }

        return sum;
    }

}
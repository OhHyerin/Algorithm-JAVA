import java.util.Random;

public class Generator {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {

        sb.append(50 + "\n");
        int N = 0;
        for (int i = 0; i < 50; i++) {
            if (i < 10) {
                N = 5;

            } else if (i < 20) {
                N = 6;
            } else if (i < 25) {
                N = 7;
            } else if (i < 30) {
                N = 8;
            } else if (i < 35) {
                N = 9;
            } else if (i < 40) {
                N = 10;
            } else if (i < 45) {
                N = 11;
            } else {
                N = 12;
            }
            sb.append(N + " " + N * (N - 1) / 2 + "\n");
            int[] weight = genWeight(N);
            for (int w : weight) {
                sb.append(w + " ");
            }
            sb.setLength(sb.length() - 1);
            sb.append("\n");
            int[] temp = genSE(N);
            for (int t : temp) {
                sb.append((char) t + " ");
            }
            sb.setLength(sb.length() - 1);
            sb.append("\n");
            // 간선 생성 함수

            char[] arr = new char[N];
            for (int j = 0; j < N; j++) {
                arr[j] = (char) (j + 'A');
            }
            boolean[] visited = new boolean[N];
            combination(arr, visited, 0, N, 2); // r==2로 고정
        }
        System.out.println(sb.toString());
    }

    static void gen(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int dis = (int) Math.random() * 1000 + 1;
                if (dis == 0) {
                    dis = 1;
                }
            }
        }
    }

    static int[] genWeight(int N) {
        int[] temp = new int[N];
        Random rand = new Random();
        for (int i = 0; i < N; i++) {
            int weight = rand.nextInt(1001);
            temp[i] = weight;
        }
        return temp;
    }

    static int[] genSE(int N) {
        int[] temp = new int[2];

        Random rand = new Random();
        int S = rand.nextInt(N);
        temp[0] = S + 'A';
        int E = rand.nextInt(N);
        temp[1] = E + 'A';

        return temp;
    }

    static void combination(char[] arr, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            print(arr, visited, n); // 조합 출력하는 코드
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }

    static void print(char[] arr, boolean[] visited, int n) {
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                sb.append(arr[i] + " "); // + 가중치(gen()출력값) 더해서 출력하면 됨
            }
        }
        int dis = rand.nextInt(1000) + 1;
        sb.append(dis + "\n");
    }

}
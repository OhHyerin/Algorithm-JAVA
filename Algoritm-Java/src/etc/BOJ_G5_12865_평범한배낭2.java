package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_12865_평범한배낭2 {

    static int N;  //물품의 수
    static int K;  //버틸수있는 무게

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for(int i=0;i<N;i++){

        }

    }

    private static class Node implements Comparable<Node>{
        int weight;
        int value;

        public Node(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return weight-o.weight;
        }
    }


}

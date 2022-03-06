package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G1_2213_트리의독립집합 {
    //백준 골드1
    //오늘의문제

    /*
    독립집합
    - 독립집합이란, 그래프 이론에서 어떤 두 꼭짓점도 서로 인접하지 않는 그래프에 있는 꼭짓점들의 집합을 이르는 말이다.
    - 독립집합에 속해있는 임의의 노드는 자신과 연결된 모든 노드와 인접해있지 않다.
     */

    static int N;
    static int[] weight; //w1~wn까지 정점 i의 가중치

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        weight = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            weight[i] = Integer.parseInt(st.nextToken());
        }



    }
}

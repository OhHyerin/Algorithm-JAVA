package data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G1_20304_비밀번호제작 {
    // 백준 골드1
    // 비트연산자 + BFS

    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
    }

    static void solution(){
        Queue<Integer> queue = new LinkedList<>();
        //해당 숫자들이 점검 되었는지 확인, 최대 거리만 구하면 되므로 boolean으로
        boolean[] visited = new boolean[N+1];

        //초기 큐 생성 및 방문 처리 - 최종적으로 찾아야 하는 비밀번호와 o차이가 나는 것

    }
}

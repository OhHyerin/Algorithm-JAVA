package data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G1_20304_��й�ȣ���� {
    // ���� ���1
    // ��Ʈ������ + BFS

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
        //�ش� ���ڵ��� ���� �Ǿ����� Ȯ��, �ִ� �Ÿ��� ���ϸ� �ǹǷ� boolean����
        boolean[] visited = new boolean[N+1];

        //�ʱ� ť ���� �� �湮 ó�� - ���������� ã�ƾ� �ϴ� ��й�ȣ�� o���̰� ���� ��

    }
}

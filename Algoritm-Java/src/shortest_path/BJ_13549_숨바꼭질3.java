package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_13549_숨바꼭질3 {
    //백준
    //숨바꼭질3(골드5)

    //큐에 새로운 노드를 넣을 때 마다
    //노드들을 비용기준 오름차순으로 정렬하고,
    //가장 비용이 적은 노드를 꺼내며 최단거리를 갱신한다.
    static int n, k;
    static int[] load;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        k = Integer.parseInt(str[1]);

        load = new int[100001];

    }

}

package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_17951_흩날리는시험지속에서내평점이느껴진거야 {
    //이분탐색

    /*
    문제 이해
    시험지를 현재 순서 그대로 K개의 그룹으로 나눈 뒤
    각 그룹에서 맞은 문제 개수의 합을 구하여
    그 중 최솟값을 시험 점수로 하기로함
     */

    static int N;  //시험지의 개수
    static int K;  //시험지를 나눌 그룹의 수
    static int[] scores; //각 시험지마다 맞은 문제의 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        scores = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            scores[i] = Integer.parseInt(st.nextToken());
        }



    }
}

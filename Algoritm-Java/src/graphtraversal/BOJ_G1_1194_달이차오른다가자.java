package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G1_1194_달이차오른다가자 {
    //그래프탐색

    /*
    빈칸 : 언제나 이동할 수 있다 (.)
    벽 : 절대 이동할 수 없다(#)
    열쇠 : 언제나 이동할 수 있다. 이 곳에 처음 들어가면 열쇠를 잡는다. (a, b, c, d, e, f)
    문 : 대응하는 열쇠가 있을 때만 이동할 수 있다.(A, B, C, D, E, F)
    민식이의 현재 위치 : 빈 곳이고, 민식이가 현재 서 있는 곳이다.(0)
    출구 : 달이 차오르기 때문에, 민식이가 가야하는 곳이다. 이 곳에 오면 미로를 탈출한다.(1)
     */


    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

    }
}

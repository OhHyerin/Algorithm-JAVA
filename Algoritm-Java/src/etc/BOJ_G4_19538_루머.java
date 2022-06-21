package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_19538_루머 {
    //그래프탐색

    /*
    문제 정리
    - 최초 유포자는 여러명일 수 있다.
    - 최초 유포자를 제외하고 스스로 루머를 만들어 믿는 사람은 없다
    - 매분 루머를 믿는 사람은 모든 주변인에게 루머를 동시에 퍼트린다.
    - 군중 속 사람은 주변인의 절반 이상이 루머를 믿을 때 본인도 루머를 믿는다.
    - 한번 믿은 루머는 계속 믿는다.
     */

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());



    }
}

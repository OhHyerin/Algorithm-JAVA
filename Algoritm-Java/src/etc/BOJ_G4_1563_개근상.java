package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_1563_개근상 {
    //dp

    /*
    O : 출석
    L : 지각
    A : 결석

    개근상을 받을 수 없는 사람 : 지각 2번이상 or 결석 3번 연속
    -> 개근상 받을 수 있는 사람 : 지각0번, 지각1번, 결석은 2일+출석일 때 또는 결석2일 + 지각

    정답을 1,000,000으로 나눈 나머지 출력
     */

    static int N;  //N일이 한 학기

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

    }

    private static long attended(int total, int late, int unattended){
        long sum = 0;
        if(total==N) return 1;  //N일 다 정해졌으면 한 가지 경우 더함




        return sum;
    }
}

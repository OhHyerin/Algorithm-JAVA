package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G4_2262_토너먼트만들기 {
    //그리디

    /*
    1. 랭킹이 가장 낮은 선수를 찾는다 (숫자가 가장 높은 선수)
    2. 1에서 찾은 선수에서 좌, 우 선수와의 차이값 중 최솟값을 찾는다

     */

    static int N;  //선수의 수
    static int[] rank;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        rank = new int[N];
        list = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
//            rank[i] = Integer.parseInt(st.nextToken());
            list.add(Integer.parseInt(st.nextToken()));
        }

        int min = 0;  //1번
        int max = N;  //N번  //가장 랭킹이 낮은 (숫자가 높은) 선수

        for(int i=0;i<N-1;i++){
            int idx = list.indexOf(max);  //랭킹이 낮은 선수의 인덱스

            //랭킹이 가장 낮은 선수가 가장 앞에 있을 경우
            // => 그 뒤 선수와의 차이
            if(idx == 0){
                min += list.get(idx) - list.get(idx+1);
            }

            //랭킹이 가장 낮은 선수가 가장 끝에 있을 경우
            //  => 그 앞 선수와의 차이
            else if(idx==list.size()-1){
                min += list.get(idx) - list.get(idx-1);
            }

            //랭킹이 가장 낮은 선수가 중앙 어딘가에 있을 경우
            // => 앞, 뒤 선수중 차이가 작은 선수와 매칭
            else{
                min += Math.min(list.get(idx)-list.get(idx-1), list.get(idx)-list.get(idx+1));
            }

            list.remove(idx); //랭킹이 가장 높은 선수는 매칭 끝났으므로 제거
            max--;
        }

        System.out.println(min);


    }

}

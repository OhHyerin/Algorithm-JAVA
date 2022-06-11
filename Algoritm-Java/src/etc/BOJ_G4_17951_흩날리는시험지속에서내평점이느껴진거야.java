package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_17951_흩날리는시험지속에서내평점이느껴진거야 {
    //이분탐색
    //스터디 new 1주차

    /*
    문제 이해
    시험지를 현재 순서 그대로 K개의 그룹으로 나눈 뒤
    각 그룹에서 맞은 문제 개수의 합을 구하여
    그 중 최솟값을 시험 점수로 하기로함
     */

    //점수 합 mid를 구하고, mid가 그룹 중 최소값일경우 그룹의 개수가 k개 이상인지 검사한다.

    static int N;  //시험지의 개수
    static int K;  //시험지를 나눌 그룹의 수
    static int[] scores; //각 시험지마다 맞은 문제의 개수
    static int sum;  //모든 점수의 합
    static int min = Integer.MAX_VALUE; //점수 중 최솟값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        scores = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            scores[i] = Integer.parseInt(st.nextToken());  //점수 입력받음
            sum += scores[i];  //sum은 모든 점수의 합 (최댓값)
            min = Math.min(min, scores[i]);
        }
        
        //이분탐색
        /*
        현재
        sum : 점수 합의 최댓값
        min : 점수 합의 최솟값
         */
        int left = min;
        int right = sum;

        //최솟값~최댓값 사이 검사 (left가 right를 넘어갈때까지 검사)
        while(left<=right){

            int mid = (left+right)/2;  //점수 합의 중간값

            //점수 합의 범위에 k개의 그룹으로 나눌 수 있나 확인
            int groupSum = 0;
            int groupCount = 0;

            for(int i=0;i<N;i++){
                groupSum += scores[i];
                if(groupSum>=mid){
                    //합이 mid보다 크면 그룹 자르기
                    groupSum = 0;
                    groupCount++;
                }
            }

            if(groupCount>=K){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }

        //탈출할 때 left가 right를 초과해야 끝나므로 원하는 값은 right
        System.out.println(right);

    }
}

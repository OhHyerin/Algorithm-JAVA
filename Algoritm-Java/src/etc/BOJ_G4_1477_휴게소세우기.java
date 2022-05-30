package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G4_1477_휴게소세우기 {
    //이분탐색

    static int N;  //휴게소 개수
    static int M;  //더 지으려고 하는 휴게소의 개수
    static int L;  //고속도로의 길이
//    static int[] stores;
    static List<Integer> stores;
    static int left, right;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

//        stores = new int[N];
        stores = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
//            stores[i] = Integer.parseInt(st.nextToken());
            stores.add(Integer.parseInt(st.nextToken()));
        }
        stores.add(0);  //고속도로 시작 위치
        stores.add(L);  //고속도로 끝 위치

        Collections.sort(stores);  //휴게소 위치 기반으로 오름차순 정렬

        pivot();  //left, right를 사용하여 이분탐색 시작

        System.out.println(left);
    }

    private static void pivot(){
        left = 0;  //왼쪽 첫 시작 0
        right = L;  //오른쪽 첫 시작 고속도로 끝

        while(left<=right){
            int mid = (left+right)/2;  //왼쪽, 오른쪽 지점의 중점(휴게소 간격)
            if(makeRest(mid)) left = mid+1; //휴게소 간격이 mid일 때 M개보다 많이지을 수 있으면 확장해서 탐색
            else right = mid-1;  //M개보다 적게지을 수 있으면 범위 좁혀서 탐색
        }
    }

    private static boolean makeRest(int mid){
        int cnt = 0;  //몇 개의 휴게소를 설치할 수 있는지
        for(int i=1;i<stores.size();i++){
            cnt += (stores.get(i)-stores.get(i-1)-1)/mid;
        }
        return (cnt>M);
//        return true;  //큰 범위면 오른쪽으로 더 탐색
//        return false;  //M개보다 이하밖에 못지으면 범위를 더 좁혀서 탐색
    }
}

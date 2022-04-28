package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_3079_입국심사 {
    //이진탐색

    static int N; //입국심사대 개수
    static int M; //사람 수
    static int[] times; //입국심사대
    static long maxTime = 0;
    static long minTime = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        times = new int[N];

        for(int i=0;i<N;i++){
            times[i] = Integer.parseInt(br.readLine());
            maxTime = Math.max(maxTime, times[i]);
        }

//        Arrays.sort(times);

        binarySearch(N, M);
        System.out.println(minTime);

    }

    private static void binarySearch(int n, int m){
        long low = 0;  //가장 짧을 시간
        long high = maxTime*m;  //가장 긴 시간

        //가능한 시간 범위를 구하기 위해 이진탐색
        while(low<=high){ //low가 high를 넘어가지않으면 반복
            long mid = (low+high)/2;  //시간의 중간값
            long sum = 0;

            for(int i=0;i<times.length;i++){
                long ableCount = mid/times[i];  //mid초 안에 몇 명이 통과할 수 있을지?
                sum += ableCount;
                if(sum>=m) break;
            }

            if(sum>=m){
                //break돼서 나온 경우(m명 모두 통과)
                minTime = Math.min(minTime, mid); //최소시간 갱신
                high = mid-1;  //최대 시간을 현재시간보다 작게(mid-1)
            }else{
                //m명이 다 통과하는데 mid보다 오래걸린다면
                low = mid+1;  //최소 시간을 현재 시간보다 크게(mid+1)
            }

        }
    }
}

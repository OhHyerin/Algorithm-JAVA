package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_S1_1446_지름길 {
    //dp, 다익스트라
    //단뱡향

    static int N;  //지름길의 개수
    static int D;  //고속도로의 길이
    static List<Pos> list;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        dp = new int[D+1];


        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            if(end>D) continue;  //역주행은 안되니까 end가 도착점 넘어가면 안됨
            if(end-start<=dist) continue; //그냥 가는것보다 지름길 거리가 더 크면 지름길이 아님

            list.add(new Pos(start, end, dist));
        }

        Collections.sort(list);  //출발지점 순으로 정렬

        dijkstra();

        System.out.println(dp[D]);
    }

    static void dijkstra(){
        int idx = 0;
        int location = 0;

        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
        while(location<D){  //위치가 D일때까지 반복
            if(idx<list.size()){  //지름길이 있으면
                Pos cur = list.get(idx);  //현재 지름길
                if(location==cur.start){  //현재 위치부터 시작하는 지름길이 있으면
                    dp[cur.end] = Math.min(dp[location]+cur.dist, dp[cur.end]); //end지점 dp값 갱신
                    idx++;  //다음 지름길 찾기
                }else{  //현재 위치부터 시작하는 지름길이 없으면
                    dp[location+1] = Math.min(dp[location]+1, dp[location+1]); //다음 위치로 이동
                    location++;  //현재 위치 한 칸 전진
                }
            }else{  //이제 지름길 없으면
                dp[location+1] = Math.min(dp[location+1], dp[location]+1);  //앞으로 전진
                location++;  //현재 위치 한 칸 전진
            }
        }


    }


    static class Pos implements Comparable<Pos>{
        int start;
        int end;
        int dist;

        public Pos(int start, int end, int dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pos o) {
            if(start==o.start) return end-o.end;  //출발지점이 똑같으면 도착지점이 더 가까운 순으로
            return start-o.start;
        }
    }

}

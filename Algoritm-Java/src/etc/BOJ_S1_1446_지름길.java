package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_S1_1446_지름길 {
    //dp, 다익스트라
    //단뱡향

    static int N;  //지름길의 개수
    static int D;  //고속도로의 길이
    static List<Pos> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            if(end>D) continue;  //역주행은 안되니까 end가 도착점 넘어가면 안됨
            if(end-start<=dist) continue; //그냥 가는것보다 지름길 거리가 더 크면 지름길이 아님

            list.add(new Pos(start, end, dist));
        }

    }

    static class Pos{
        int start;
        int end;
        int dist;

        public Pos(int start, int end, int dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }
    }

}

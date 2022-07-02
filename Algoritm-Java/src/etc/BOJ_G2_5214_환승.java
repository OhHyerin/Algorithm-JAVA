package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G2_5214_환승 {
    //그래프 탐색, bfs

    /*
    문제 설명 :
    하이퍼튜브 하나는 역 K개를 서로 연결한다.
    1번역에서 N번역으로 가는데 방문하는 최소 역의 수는 몇 개일까?
     */

    static int N;  //역의 수
    static int K;  //한 하이퍼튜브가 서로 연결하는 역의 개수
    static int M;  //하이퍼튜브의 개수
    static List<Integer>[] list;
    static Tube[] tube;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new List[N+1];
        tube = new Tube[M];

        for(int i=1;i<=N;i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            //하이퍼튜브의 정보

            tube[i] = new Tube(i);  //tube 생성

            st = new StringTokenizer(br.readLine());
            for(int j=0;j<K;j++){
                int station = Integer.parseInt(st.nextToken());
                tube[i].stations.add(station);  //tube클래스 HashSet에 추가
                list[station].add(i);  //역에 하이퍼튜브 추가
            }
        }

        //-----------입력 완료--------------------------

        bfs();



    }

    private static void bfs(){
        Queue<Tube> queue = new LinkedList<>();


    }

    private static class Tube{
        int idx;
        HashSet<Integer> stations;

        public Tube(int idx) {
            this.idx = idx;
            this.stations = new HashSet<>();
        }
    }

}

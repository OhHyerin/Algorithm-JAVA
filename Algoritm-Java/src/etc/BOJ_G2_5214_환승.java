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
    static int answer = -1;

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

        System.out.println(answer);



    }

    private static void bfs(){
        Queue<Tube> queue = new LinkedList<>();
        int[] visitTube = new int[tube.length];  //tube 방문체크
        boolean[] visitStation = new boolean[N+1]; //역 방문 체크

        //1번 station이 포함된 튜브들을 큐에 담기
        for(int i=0;i<tube.length;i++) {
            if (tube[i].stations.contains(1)) {
                //tube가 연결되어있는 역 중 i가 있다면
                queue.add(tube[i]); //해당 튜브로 연결
                visitTube[i] = 1;  //튜브 방문처리
            }
        }
        visitStation[1] = true;  //역 방문처리

        while(!queue.isEmpty()){
            Tube cur = queue.poll();

            for(int station : cur.stations){  //현재 튜브가 연결되어있는 모든 station을 탐색
                if(visitStation[station]){  //이미 방문했던 역이면 continue
                    continue;
                }

                visitStation[station] = true;

                if(station == N){
                    //목적지와 연결되어있으면
                    queue.clear();
                    answer = visitTube[cur.idx]+1;  //현재 방문횟수 + 1 반환
                }

                //해당 역과 연결된 하이퍼튜브 확인하여 방문되지 않았으면 큐에 넣기
                for(int i=0;i<list[station].size();i++){
                    int next = list[station].get(i);
                    if(visitTube[next]==0){
                        queue.add(tube[next]);
                        visitTube[next] = visitTube[cur.idx]+1;
                    }
                }

            }
        }




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

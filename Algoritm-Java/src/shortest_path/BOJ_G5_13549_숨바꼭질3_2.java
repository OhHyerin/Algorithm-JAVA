package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_13549_숨바꼭질3_2 {
    //백준 골드5
    //최단경로 - 다익스트라
    //다시풀기


    //dp역할을 하는 time배열을 생성해서 이동 case마다 time배열 갱신
    //queue에 add해서 최소비용 위치로 이동

    static int N, K;
    static int[] time;  //해당 위치에서 최소시간을 저장하는 dp배열
    static int MAX_POS = 100001; //넘어가니까 오류 //10만으로 넣으면 인덱스오류남

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        time = new int[MAX_POS];  //일직선상에 MAX_POS만큼 위치가 있다고 생각

        dijkstra();
        System.out.println(time[K]);  //K위치에서의 최소시간 반환

    }

    private static void dijkstra() {
        Queue<Pos> queue = new LinkedList<>();
        Arrays.fill(time, Integer.MAX_VALUE);  //time을 최단거리로 갱신하기위해 초기값 셋팅

        time[N] = 0; //수빈이가 원래 있던 위치 : time:0
        queue.add(new Pos(N, 0));  //queue에 추가

        while (!queue.isEmpty()) {
            Pos cur = queue.poll();
            int curPos = cur.pos;  //현재 위치
            int curSec = cur.sec;  //현재 위치가 갖는 시간

            if (time[cur.pos] < cur.sec) continue; //이미 time배열에 저장되어있는 값이 현재 위치가 갖는 시간보다 더 작으면 continue

            //+1이동
            int nextPos = curPos + 1;  //위치 1 이동
            int nextSec = curSec + 1;  //시간 1초 추가
            if (isIn(nextPos, nextSec) && time[nextPos] > nextSec) {
                //time배열보다 nextSec이 더 작으면 time배열을 갱신
                time[nextPos] = nextSec;
                queue.add(new Pos(nextPos, nextSec)); //다음 위치로 이동
            }

            //-1이동
            nextPos = curPos - 1;  //위치 -1 이동
            nextSec = curSec + 1;  //시간 1초 추가
            if (isIn(nextPos, nextSec) && time[nextPos] > nextSec) {
                time[nextPos] = nextSec;
                queue.add(new Pos(nextPos, nextSec));
            }

            //*2로 순간이동
            nextPos = curPos * 2;  //위치 *2 이동
            nextSec = curSec;      //시간 추가 없음
            if (isIn(nextPos, nextSec) && time[nextPos] > nextSec) {
                time[nextPos] = nextSec;
                queue.add(new Pos(nextPos, nextSec));
            }

        }
    }

    private static boolean isIn(int pos, int sec){
        return pos>=0 && sec>=0 && pos<MAX_POS;
    }

    private static class Pos {
        int pos;
        int sec;

        public Pos(int pos, int sec) {
            this.pos = pos;
            this.sec = sec;
        }
    }
}

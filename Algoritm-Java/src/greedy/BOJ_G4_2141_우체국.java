package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G4_2141_우체국 {
    //그리디, 정렬

    /*
    반례
    2
    1 1
    2 2
     */

    static int N;
    static List<Pos> list;
    static long sumPeople;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        list = new ArrayList<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            sumPeople += p;
            list.add(new Pos(l, p));

        }

        Collections.sort(list);  //위치순으로 정렬

        long sum = 0;
        int cur = 0;
        for(int i=0;i<list.size();i++){
            sum += list.get(i).people;
            if(sum>=((sumPeople/2)+(sumPeople%2))){  //인구의 중간지점이 되었을 때 위치 출력
                //그냥 sumPeople/2로하면 중간지점을 찾지못함
                cur = list.get(i).location;  //여기서 i+1로 해서 틀렸습니다 나옴
                break;
            }
        }

        System.out.println(cur);


    }

    private static class Pos implements Comparable<Pos>{
        int location;
        int people;

        public Pos(int location, int people) {
            this.location = location;
            this.people = people;
        }


        @Override
        public int compareTo(Pos o) {
            return (int)(location-o.location);
        }
    }
}

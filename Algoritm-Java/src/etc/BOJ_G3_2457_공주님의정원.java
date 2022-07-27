package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G3_2457_공주님의정원 {
    //그리디

    static int N;  //꽃들의 총 개수
    static List<Flower> flowers;
    static int S = 301;
    static int E = 1201;  //지는 날을 하루 더

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        flowers = new ArrayList<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int sm = Integer.parseInt(st.nextToken());
            int sd = Integer.parseInt(st.nextToken());
            int em = Integer.parseInt(st.nextToken());
            int ed = Integer.parseInt(st.nextToken());

            flowers.add(new Flower(sm*100+sd, em*100+ed));
        }

        Collections.sort(flowers);

//        System.out.println(flowers);

        int cnt = 0;  //꽃 몇 개 인지
        int startDate = S;  //꽃이 지는 기준
        int max = 0;  //최대 끝나는 날
        int idx = 0;

        while(startDate<E) {
            max = 0;
            boolean flag = false;  //갱신이 되었나 확인

            for(int i=idx; i<N;i++){
                Flower cur = flowers.get(i);

                if(cur.start<=startDate && cur.end>max){  //현재 시작하는 날이 전 꽃이 지는 날보다 전이고, 현재 끝나는 날이 max보다 늦게라면
                    max = cur.end;  //max를 갱신
                    idx = i+1;  //다음은 i다음부터 확인
                    flag = true;  //갱신 되었음을 의미
                }
            }

            if (flag) {  //갱신 됐으면
                startDate = max;  //startDate를 현재 끝나는 날로 갱신하고
                cnt++;  //꽃의 개수 ++
            }else break;
        }

//        List<Flower> temp = new ArrayList<>();
//        for(int i=0;i<flowers.size();i++){
//            if(flowers.get(i).end<S) continue;  //종료 날짜가 S보다 작으면 continue
//            if(flowers.get(i).start>E) continue;
//
//            if(flowers.get(i).start<=endDate && flowers.get(i).end>endDate){
//
//                for(int j=0;j<temp.size();j++){
//                    if(temp.get(j).start<curDate && temp.get(j).end<flowers.get(i).end){
//                        System.out.println(temp.get(j));
//                        temp.remove(j);
//                    }
//                }
//
//                temp.add(new Flower(flowers.get(i).start, flowers.get(i).end));
//                System.out.println("temp리스트 : "+temp);
//
//                curDate = Math.max(endDate, flowers.get(i).start);
//                endDate = flowers.get(i).end;
//
//                System.out.println("curDate : "+curDate+"   endDate : "+endDate);
//            }
//        }

//        System.out.println(temp.size());

        if(max<1201){
            System.out.println(0);
        }else{
            System.out.println(cnt);
        }

    }

    private static class Flower implements Comparable<Flower>{
        int start;
        int end;

        public Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Flower o) {
            if(start==o.start){  //시작일이 같으면 종료일이 더 나중인 순서
                return (end-o.end)*-1;
            }
            return start-o.start;  //시작일 빠른 순서
        }

        @Override
        public String toString() {
            return "Flower{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}

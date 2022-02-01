package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_19598_최소회의실개수 {
    //백준 골드5

    static int n;
    static ArrayList<int[]> meeting;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        meeting = new ArrayList<int[]>();
        StringTokenizer st = null;

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meeting.add(new int[]{start, end});
        }

        meeting.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0]){
                    //두 수업의 시작시간이 같으면 끝나는 시작을 기준으로 정렬
                    return o1[1]-o2[1];
                }
                return o1[0]-o2[0];
            }
        });

//        int cnt = 0;
//        for(int i=0;i<lecture.size();i++){
//            cnt++;
//            for(int j=i+1;j< lecture.size();j++){
//                if(lecture.get(i)[1]<= lecture.get(j)[0]){
//                    //끝나는시간보다 다음 회의의 시작시간이 더 크면 이어짐
//                    lecture.get(i)[1] = lecture.get(j)[1];
//                    lecture.remove(j);
//                }
//            }
//        }
//        System.out.println(cnt);
        //ArrayList의 get 메소드는 O(1)이지만, add, remove에 대해서는 O(n)
        //ArrayList의 add, remove연산대신 PriorityQueue로 사용용

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(meeting.get(0)[1]);
        for(int i=1;i<meeting.size();i++){
            if(pq.peek()<=meeting.get(i)[0]){ //peek : 값만 확인 (pollX)
                //pq에 저장되어있는 회의가 끝나는 시간이
                //lecture에 저장돼어있는 회의의 시작시간보다 작으면
                pq.poll(); //큐에 저장되어있던 값 미리 삭제
            }
            //if문 안거쳐왔으면 새로운 회의 끝 시간을 큐에 추가
            //if문 거쳐왔으면 큐에 저장되어있던 값을 삭제하고 끝 시간 갱신
            pq.offer(meeting.get(i)[1]);
        }
        System.out.println(pq.size()); //pq의 크기 = 회의실 수


    }
}

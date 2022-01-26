package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1931_회의실배정 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int n = Integer.parseInt(br.readLine());

        ArrayList<int[]> meeting = new ArrayList<int[]>();
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
                    return o1[1]-o2[1];
                }
                return o1[0]-o2[0];
            }
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(meeting.get(0)[1]);
        int count = 1;
        for(int i=1;i< meeting.size();i++){
            if(pq.peek()<=meeting.get(i)[0]){
                pq.poll();
                count++;
            }
            pq.offer(meeting.get(i)[1]);
        }

        System.out.println(count);

    }
}

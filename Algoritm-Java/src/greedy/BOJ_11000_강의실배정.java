package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_11000_���ǽǹ��� {
    //���� ���5

    static int n;
    static ArrayList<int[]> lecture;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        lecture = new ArrayList<int[]>();

        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lecture.add(new int[]{start, end});
        }

        lecture.sort(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                //�迭���� �񱳴ϱ� �������� start��
                if(o1[0]==o2[0]){
                    //���࿡ ���� �ð��� �Ȱ�����
                    return o1[1]-o2[1];
                }
                return o1[0]-o2[0];
            }
        });

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        pq.offer(lecture.get(0)[1]);
        for(int i=1;i<lecture.size();i++){
            int cur = pq.peek();
            if(cur<=lecture.get(i)[0]){
                pq.poll();
            }
            pq.offer(lecture.get(i)[1]);
        }
        System.out.println(pq.size());



    }
}

package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_19598_�ּ�ȸ�ǽǰ��� {
    //���� ���5

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
                    //�� ������ ���۽ð��� ������ ������ ������ �������� ����
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
//                    //�����½ð����� ���� ȸ���� ���۽ð��� �� ũ�� �̾���
//                    lecture.get(i)[1] = lecture.get(j)[1];
//                    lecture.remove(j);
//                }
//            }
//        }
//        System.out.println(cnt);
        //ArrayList�� get �޼ҵ�� O(1)������, add, remove�� ���ؼ��� O(n)
        //ArrayList�� add, remove������ PriorityQueue�� ����

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(meeting.get(0)[1]);
        for(int i=1;i<meeting.size();i++){
            if(pq.peek()<=meeting.get(i)[0]){ //peek : ���� Ȯ�� (pollX)
                //pq�� ����Ǿ��ִ� ȸ�ǰ� ������ �ð���
                //lecture�� ����ž��ִ� ȸ���� ���۽ð����� ������
                pq.poll(); //ť�� ����Ǿ��ִ� �� �̸� ����
            }
            //if�� �Ȱ��Ŀ����� ���ο� ȸ�� �� �ð��� ť�� �߰�
            //if�� ���Ŀ����� ť�� ����Ǿ��ִ� ���� �����ϰ� �� �ð� ����
            pq.offer(meeting.get(i)[1]);
        }
        System.out.println(pq.size()); //pq�� ũ�� = ȸ�ǽ� ��


    }
}

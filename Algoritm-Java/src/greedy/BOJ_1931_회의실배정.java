package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_1931_ȸ�ǽǹ��� {
    //���� �ǹ�2
    //���� ��ġ�� �ʴ� Ȱ���� ���� ����ð��� ������ �� ���� Ȱ���� ������ �� �ִ� �ð��� ��������.

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
                if(o1[1]==o2[1]){
                    return o1[0]-o2[0];
                }
                return o1[1]-o2[1];
            }
        });

        int count = 0;
        int prev_end = 0;
        for(int i=0;i<n;i++){
            if(prev_end<=meeting.get(i)[0]){
                prev_end = meeting.get(i)[1];
                count++;
            }
        }
        System.out.println(count);

    }
}

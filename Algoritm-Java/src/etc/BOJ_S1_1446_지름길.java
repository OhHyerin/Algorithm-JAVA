package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_S1_1446_������ {
    //dp, ���ͽ�Ʈ��
    //�ܓ���

    static int N;  //�������� ����
    static int D;  //��ӵ����� ����
    static List<Pos> list;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        dp = new int[D+1];


        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            if(end>D) continue;  //�������� �ȵǴϱ� end�� ������ �Ѿ�� �ȵ�
            if(end-start<=dist) continue; //�׳� ���°ͺ��� ������ �Ÿ��� �� ũ�� �������� �ƴ�

            list.add(new Pos(start, end, dist));
        }

        Collections.sort(list);  //������� ������ ����

        dijkstra();

        System.out.println(dp[D]);
    }

    static void dijkstra(){
        int idx = 0;
        int location = 0;

        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
        while(location<D){  //��ġ�� D�϶����� �ݺ�
            if(idx<list.size()){  //�������� ������
                Pos cur = list.get(idx);  //���� ������
                if(location==cur.start){  //���� ��ġ���� �����ϴ� �������� ������
                    dp[cur.end] = Math.min(dp[location]+cur.dist, dp[cur.end]); //end���� dp�� ����
                    idx++;  //���� ������ ã��
                }else{  //���� ��ġ���� �����ϴ� �������� ������
                    dp[location+1] = Math.min(dp[location]+1, dp[location+1]); //���� ��ġ�� �̵�
                    location++;  //���� ��ġ �� ĭ ����
                }
            }else{  //���� ������ ������
                dp[location+1] = Math.min(dp[location+1], dp[location]+1);  //������ ����
                location++;  //���� ��ġ �� ĭ ����
            }
        }


    }


    static class Pos implements Comparable<Pos>{
        int start;
        int end;
        int dist;

        public Pos(int start, int end, int dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pos o) {
            if(start==o.start) return end-o.end;  //��������� �Ȱ����� ���������� �� ����� ������
            return start-o.start;
        }
    }

}

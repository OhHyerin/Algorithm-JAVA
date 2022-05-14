package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_S1_1446_������ {
    //dp, ���ͽ�Ʈ��
    //�ܓ���

    static int N;  //�������� ����
    static int D;  //��ӵ����� ����
    static List<Pos> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            if(end>D) continue;  //�������� �ȵǴϱ� end�� ������ �Ѿ�� �ȵ�
            if(end-start<=dist) continue; //�׳� ���°ͺ��� ������ �Ÿ��� �� ũ�� �������� �ƴ�

            list.add(new Pos(start, end, dist));
        }

    }

    static class Pos{
        int start;
        int end;
        int dist;

        public Pos(int start, int end, int dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }
    }

}

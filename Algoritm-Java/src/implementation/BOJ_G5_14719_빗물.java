package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G5_14719_���� {
    //�ùķ��̼�, ����
    
    //���� ���� �� �������� ����, ������ �˻�

    static int R, C;
    static List<Node> list;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<C;i++){
            int h = Integer.parseInt(st.nextToken());
            list.add(new Node(i, h));
        }

        for(int i=1;i<list.size()-1;i++){
            //�׳� �� ĭ�� �������� �� ã�ƺ��� (���� �ѹ��� ä�����ʰ�, ���� �� �پ� ä��)
            int left = 0;
            int right = 0;
            for(int c=i-1;c>=0;c--){
                //���� ū ��
                left = Math.max(left, list.get(c).height);
            }
            for(int c=i+1;c<list.size();c++){
                //������ ū ��
                right = Math.max(right, list.get(c).height);
            }

            if(left>list.get(i).height && right>list.get(i).height){  //������ġ�� ����, �����ʺ��� �ؿ�������
                result += Math.min(left, right)-list.get(i).height;  //�ڱ���ġ �� ä���
            }

        }

        System.out.println(result);

    }

    private static class Node {
        int index;
        int height;

        public Node(int index, int height) {
            this.index = index;
            this.height = height;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "index=" + index +
                    ", height=" + height +
                    '}';
        }
    }
}

package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G4_2262_��ʸ�Ʈ����� {
    //�׸���

    /*
    1. ��ŷ�� ���� ���� ������ ã�´� (���ڰ� ���� ���� ����)
    2. 1���� ã�� �������� ��, �� �������� ���̰� �� �ּڰ��� ã�´�

     */

    static int N;  //������ ��
    static int[] rank;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        rank = new int[N];
        list = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
//            rank[i] = Integer.parseInt(st.nextToken());
            list.add(Integer.parseInt(st.nextToken()));
        }

        int min = 0;  //1��
        int max = N;  //N��  //���� ��ŷ�� ���� (���ڰ� ����) ����

        for(int i=0;i<N-1;i++){
            int idx = list.indexOf(max);  //��ŷ�� ���� ������ �ε���

            //��ŷ�� ���� ���� ������ ���� �տ� ���� ���
            // => �� �� �������� ����
            if(idx == 0){
                min += list.get(idx) - list.get(idx+1);
            }

            //��ŷ�� ���� ���� ������ ���� ���� ���� ���
            //  => �� �� �������� ����
            else if(idx==list.size()-1){
                min += list.get(idx) - list.get(idx-1);
            }

            //��ŷ�� ���� ���� ������ �߾� ��򰡿� ���� ���
            // => ��, �� ������ ���̰� ���� ������ ��Ī
            else{
                min += Math.min(list.get(idx)-list.get(idx-1), list.get(idx)-list.get(idx+1));
            }

            list.remove(idx); //��ŷ�� ���� ���� ������ ��Ī �������Ƿ� ����
            max--;
        }

        System.out.println(min);


    }

}

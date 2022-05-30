package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G4_1477_�ްԼҼ���� {
    //�̺�Ž��

    static int N;  //�ްԼ� ����
    static int M;  //�� �������� �ϴ� �ްԼ��� ����
    static int L;  //��ӵ����� ����
//    static int[] stores;
    static List<Integer> stores;
    static int left, right;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

//        stores = new int[N];
        stores = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
//            stores[i] = Integer.parseInt(st.nextToken());
            stores.add(Integer.parseInt(st.nextToken()));
        }
        stores.add(0);  //��ӵ��� ���� ��ġ
        stores.add(L);  //��ӵ��� �� ��ġ

        Collections.sort(stores);  //�ްԼ� ��ġ ������� �������� ����

        pivot();  //left, right�� ����Ͽ� �̺�Ž�� ����

        System.out.println(left);
    }

    private static void pivot(){
        left = 0;  //���� ù ���� 0
        right = L;  //������ ù ���� ��ӵ��� ��

        while(left<=right){
            int mid = (left+right)/2;  //����, ������ ������ ����(�ްԼ� ����)
            if(makeRest(mid)) left = mid+1; //�ްԼ� ������ mid�� �� M������ �������� �� ������ Ȯ���ؼ� Ž��
            else right = mid-1;  //M������ �������� �� ������ ���� ������ Ž��
        }
    }

    private static boolean makeRest(int mid){
        int cnt = 0;  //�� ���� �ްԼҸ� ��ġ�� �� �ִ���
        for(int i=1;i<stores.size();i++){
            cnt += (stores.get(i)-stores.get(i-1)-1)/mid;
        }
        return (cnt>M);
//        return true;  //ū ������ ���������� �� Ž��
//        return false;  //M������ ���Ϲۿ� �������� ������ �� ������ Ž��
    }
}

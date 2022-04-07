package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G5_1461_������ {
    //����, �׸��� �˰���

    static int N; // å�� ����
    static int M; // �� ���� �� �� �ִ� å�� ����
    static List<Integer> plist;
    static List<Integer> mlist;
    static int maxNum;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        plist = new ArrayList<>();  //����� ��ġ ����Ʈ
        mlist = new ArrayList<>();  //������ ��ġ ����Ʈ

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            int a = Integer.parseInt(st.nextToken());
            maxNum = Math.max(maxNum, Math.abs(a));
            if(a>=0) plist.add(a);
            else mlist.add(Math.abs(a));  //�Է� �����鼭
        }

        Collections.sort(plist, Collections.reverseOrder());  //�������� ����
        Collections.sort(mlist, Collections.reverseOrder());

//        System.out.println(plist);
//        System.out.println(mlist);

        int idx = 0;
        outer : while(!plist.isEmpty()){
            answer += plist.get(0);  //����Ʈ�� ù ��° ���ϱ�
//            System.out.println("plist : "+plist.get(0));
            plist.remove(0);  //�����
            for(int i=0;i<M-1;i++){  //å�� �� �� �ִ� ������ŭ ���鼭
                if(plist.isEmpty()) break outer;  //����Ʈ ������� ����
                plist.remove(0);  //���� (�� �� �Ÿ� ���鼭 ������ ���� �� �����ϱ�)
            }
        }

//        System.out.println(answer);
//        System.out.println(plist);

        outer : while(!mlist.isEmpty()){
            answer += mlist.get(0);
//            System.out.println("mlist : "+mlist.get(0));
            mlist.remove(0);
            for(int i=0;i<M-1;i++){
                if(mlist.isEmpty()) break outer;
                mlist.remove(0);
            }
        }

//        System.out.println(answer);
//        System.out.println(mlist);

        answer *= 2;  //�Դٰ��� �ϴ� �Ÿ��̹Ƿ� 2�� ���� ��,
        answer -= maxNum; //���� �� �Ÿ��� �ѹ� ���ֱ� (������ å �����ٳ��� �ٽ� �������� �ȿ͵� �ȴٰ� �����Ƿ�)

        System.out.println(answer);

    }

}

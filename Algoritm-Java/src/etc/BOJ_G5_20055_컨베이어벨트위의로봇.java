package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_G5_20055_�����̾Ʈ���Ƿκ� {
    //�ùķ��̼�, ����

    /*
    1. ��Ʈ�� �� ĭ ���� �ִ� �κ��� �Բ� �� ĭ ȸ���Ѵ�.
    2. ���� ���� ��Ʈ�� �ö� �κ�����, ��Ʈ�� ȸ���ϴ� �������� �� ĭ �̵��� �� �ִٸ� �̵��Ѵ�
        �̵��� �� ���ٸ� ������ �ִ´�
    3. �ø��� ��ġ�� �ִ� ĭ�� �������� 0�� �ƴϸ� �ø��� ��ġ�� �κ��� �ø���.
    4. �������� 0�� ĭ�� ������ K�� �̻��̶�� ������ �����Ѵ�. �׷��� �ʴٸ� 1������ ���ư���.
     */

    static int N, K;
    static ArrayList<Integer> belt;
    static boolean[] robot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

//        belt = new int[N*2];
        belt = new ArrayList<>();
        robot = new boolean[N*2];


        st = new StringTokenizer(br.readLine());
        for(int i=0;i<2*N;i++){
//            belt[i] = Integer.parseInt(st.nextToken());
            belt.add(Integer.parseInt(st.nextToken()));
        }

//        System.out.println(Arrays.toString(belt));
        //--------�Է¿Ϸ�-------------

        int depth = 0;
        while(depth<K){
            rotate();
        }

    }

    private static void rotate(){
        belt.add(0, belt.get(belt.size()-1));
        belt.remove(belt.size()-1);

        if(robot[N-1]){

        }

    }

}

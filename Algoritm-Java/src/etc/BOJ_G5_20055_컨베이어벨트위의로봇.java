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
    static ArrayList<Pos> belt;
    static boolean[] robot;
    static int count;

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
            belt.add(new Pos(Integer.parseInt(st.nextToken()), false));
        }

//        System.out.println(Arrays.toString(belt));
        //--------�Է¿Ϸ�-------------

        int depth = 0;
        count = K;
        while(true){
            depth++;
            rotate();
            //�������� 0�� ĭ�� ���� ����

            if(count<=0) break;
        }

        System.out.println(depth);

    }

    private static void rotate(){
        //�κ��� ��Ʈ�� �Բ� ȸ��
        belt.add(0, new Pos(belt.get(belt.size()-1).belt, false));
        belt.remove(belt.size()-1);
//        System.out.println(belt);
        belt.set(N, new Pos(belt.get(N).belt, false));
//        System.out.println("��Ʈȸ��: " + belt);

        //�κ� �̵�
        for(int i=N-1;i>0;i--){
            int curBelt = belt.get(i-1).belt;
            boolean curRobot = belt.get(i-1).robot;

            if(!curRobot){
                belt.set(i, new Pos(belt.get(i).belt, curRobot));
            }
            else{
                //���� ��ġ�� �κ��� �ְ�
                if(belt.get(i).belt>=1 && !belt.get(i).robot){
                    //���� �� ��ġ�� ��Ʈ�� 0���� ũ��, �κ��� ������
                    belt.set(i, new Pos(belt.get(i).belt-1, curRobot));
                    belt.set(i-1, new Pos(curBelt, false));
                    if(belt.get(i).belt==0) count--;
                }else continue;
            }
//            belt.set(N, new Pos(belt.get(N).belt, false));
        }
//        System.out.println("�κ� ȸ��: " + belt);

        //�ø��� ��ġ�� �ִ� ĭ�� �������� 0�� �ƴ϶�� �ø��� ��ġ�� �κ��� �ø���.
        if(belt.get(0).belt>0){
            belt.set(0, new Pos(belt.get(0).belt-1, true));
            if(belt.get(0).belt==0) count--;
        }

//        System.out.println("�κ� �ø���: "+belt);


    }

    private static class Pos{
        int belt;
        boolean robot;

        public Pos(int belt, boolean robot) {
            this.belt = belt;
            this.robot = robot;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "belt=" + belt +
                    ", robot=" + robot +
                    '}';
        }
    }

}

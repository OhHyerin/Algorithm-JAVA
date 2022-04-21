package implementation;

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
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        belt = new ArrayList<>();


        st = new StringTokenizer(br.readLine());
        for(int i=0;i<2*N;i++){
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
        belt.add(0, belt.remove(belt.size()-1));  //�� ������ ��Ʈ�� �����ϰ�, ������ ���� �� �տ� �߰�
        belt.get(N-1).robot = false;  //ȸ���ϰ� N-1��° ��Ʈ �����ִ� �κ��� ����
//        System.out.println("��Ʈȸ��: " + belt);

        //�κ� �̵�
        for(int i=N-2;i>=0;i--){
            Pos cur = belt.get(i);  //���� ��ġ
            Pos next = belt.get(i+1); //���� ��ġ

            if(cur.robot){
                //���� ��ġ�� �κ��� �ְ�
                if(!next.robot && next.belt>0){
                    //���� �̵��� ��ġ�� �κ��� ����, �������� 1�̻��̸�
                    cur.robot = false;  //���� ��ġ �κ� ����
                    next.robot = true;  //���� ��ġ �κ� ����
                    next.belt -= 1;  //��Ʈ ������ 1 ����
                    if(next.belt==0) count--;  //���� �������� 0�̵ȴٸ� count 1 ����
                }
            }
            if(i+1==N-1){  //������ ��ġ���
                next.robot =false; // �κ� ������(����)
                continue;
            }
        }
//        System.out.println("�κ� ȸ��: " + belt);

        //�ø��� ��ġ�� �ִ� ĭ�� �������� 0�� �ƴ϶�� �ø��� ��ġ�� �κ��� �ø���.
        if(belt.get(0).belt>0 && !belt.get(0).robot){
            belt.get(0).robot = true;
            belt.get(0).belt -= 1;
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

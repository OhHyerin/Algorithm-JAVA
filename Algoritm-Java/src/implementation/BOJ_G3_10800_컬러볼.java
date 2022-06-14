package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_G3_10800_�÷��� {
    //����, ����, ������
    /*
    ��������
    �ڱ� ������ ũ�Ⱑ �۰� ���� �ٸ� ���� ������ �� ���� ũ�⸸ŭ ������ ��´�.
    �ٸ� ���� ������� ���Ŀ��� ������ ���� ���� ũ��� ������ �ʴ´�.
     */
    
    /*
    color�迭�� ������� �ʰ� ���� ũ�⸦ ������������ ����
    ������������ ���� ��ȸ�ϸ� �ڽź��� ũ�Ⱑ �۰�, color�� ���� ���� ���� ��� ����
    �̷� ������� �������� ��, �ð��ʰ�
    
    ���� color�迭�� ���� ������ ��,
    ���� ������������ �����Ͽ� �� ũ�⸶�� ���� �� �ִ� ũ���� ���� color�迭�� ������
    ���� ���� color�迭�� �� - ���� ������ �� ũ�⸦ ����
    ���� ������� answer�迭�� ����
     */
    

    static int N;  // ���� ����
   static Ball[] balls;
   static int[] color;
   static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        balls = new Ball[N];
        color = new int[N+1];
        answer = new int[N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            balls[i] = new Ball(i, c, v);
        }

//        Arrays.sort(balls);
        Arrays.sort(balls, new Comparator<Ball>() {
            @Override
//            public int compare(Ball o1, Ball o2) {
//                    return (o1.value-o2.value) * -1; //ũ�Ⱑ ū ������� ��������
//            }
            public int compare(Ball o1, Ball o2) {
                    return (o1.value-o2.value); //ũ�Ⱑ ���� ������� ��������
            }
        });

        int sum = 0;

        for(int i=0, j=0;i<N;i++){
            Ball a = balls[i];
            Ball b = balls[j];

            while(b.value<a.value){
                //b�� ũ�Ⱑ a�� ũ�⺸�� ������
                sum += b.value;  //���� ���� ũ�� ����
                color[b.color] += b.value;  //���� ���� �� �ε����� ���� ���� ũ�⸦ �߰�

                b = balls[++j];  //������
            }
            //���� ���� ���� �� �ִ� ���� �� (ũ�Ⱑ ���� ��ü ���� �� ũ�� - ���� ���� ���� ���� �� ũ�� ��
            answer[a.index] = sum-color[a.color];

        }

        for(int a : answer){
            sb.append(a).append("\n");
        }


        System.out.println(sb);



    }

    static class Ball{
        int index;
        int color;
        int value;
//        int result;

        public Ball(int index, int color, int value) {
            this.index = index;
            this.color = color;
            this.value = value;
        }

    }
}

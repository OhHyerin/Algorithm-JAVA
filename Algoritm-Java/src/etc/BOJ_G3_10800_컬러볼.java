package etc;

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

    static int N;  // ���� ����
   static Ball[] balls;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        balls = new Ball[N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            balls[i] = new Ball(i, c, v, 0);
        }

//        Arrays.sort(balls);
        Arrays.sort(balls, new Comparator<Ball>() {
            @Override
            public int compare(Ball o1, Ball o2) {
                    return (o1.value-o2.value) * -1; //ũ�Ⱑ ū ������� ��������
            }
        });

        for(int i=0;i<balls.length;i++){
            Ball cur = balls[i];
            int count = 0;

            for(int j=i+1;j<balls.length;j++){
                Ball next = balls[j];
                if(cur.color!=next.color){
                    if(cur.value>next.value){
                        count += next.value;
                    }else{
                        break;
                    }
                }else{
                    continue;
                }
            }
            balls[i].result = count;
        }

        Arrays.sort(balls, new Comparator<Ball>() {
            @Override
            public int compare(Ball o1, Ball o2) {
                return o1.index-o2.index;
            }
        });

        for(int i=0;i<balls.length;i++){
            sb.append(balls[i].result).append("\n");
        }


        System.out.println(sb);



    }

    static class Ball{
        int index;
        int color;
        int value;
        int result;

        public Ball(int index, int color, int value, int result) {
            this.index = index;
            this.color = color;
            this.value = value;
            this.result = result;
        }

    }
}

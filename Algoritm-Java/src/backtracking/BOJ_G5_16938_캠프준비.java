package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G5_16938_ķ���غ� {
    //���� ���5 ��Ʈ��ŷ

    static int N;  //N : ������ ����
    static int L, R;  //L, R : ���� ���̵��� ���� L���� ũ�ų� ����, R���� �۰ų� ���ƾ���
    static int X;  //X : ���� ����� ������ ���� ���� ������ ���̵� ���̴� X���� ũ�ų� ���ƾ���
    static int[] level;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        level = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            level[i] = Integer.parseInt(st.nextToken());
        }
        //---------- �Է� �Ϸ�--------------------
        Arrays.sort(level);
//        combination(N, new int[N] , 0);
        com(0, 0, N);
        System.out.println(answer);

    }
    private static void combination(int toChoose, int[] choosed, int startIdx){
        //base part
        if(toChoose==0){
            System.out.println(Arrays.toString(choosed));
            System.out.println("toChoose==0");
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            int sum = 0;
            for(int i=0;i<choosed.length;i++){
                max = Math.max(max, choosed[i]);
                min = Math.min(min, choosed[i]);
                sum += choosed[i];
            }
            if(choosed.length>=2){ //2���� �̻�
                System.out.println("2�����̻�");
                if(max-min>=X) {//���� �������� ����� ���̵����̰� X���� ũ�ų�����
                    System.out.println("���̵�����");
                    if(sum>=L && sum<=R){ //���̵� ���� L~R����
                        System.out.println("���̵� ��");
                        answer++;
                    }
                }
            }

            return;
        }
        //inductive part
        for(int i=startIdx;i<level.length;i++){
            choosed[choosed.length-toChoose] = level[i];
            combination(toChoose-1, choosed, i+1);
        }
    }
    static int[] numbers = new int[N];

    private static void com(int cnt, int start, int num){
        if(cnt==num){
            System.out.println(Arrays.toString(numbers));
            return;
        }
        for(int i=start;i<level.length;i++){
            numbers[cnt] = level[i];
            com(cnt+1, start+1, num);
        }
    }
}

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
//        com(0, 0, N);
        subSet(0, new boolean[N]);
        System.out.println(answer);

    }

    private static void subSet(int cnt, boolean[] checked){
        //base part
        if(cnt==N){
            int count = 0;
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            int sum = 0;
            for(int i=0;i<checked.length;i++){
                if(checked[i]){
//                    System.out.print(level[i]+" ");
                    max = Math.max(max, level[i]);
                    min = Math.min(min, level[i]);
                    sum+=level[i];
                    count++;
                }
            }
            if(count>=2){ //2���� �̻�
                if(max-min>=X) {//���� �������� ����� ���̵����̰� X���� ũ�ų�����
                    if(sum>=L && sum<=R){ //���̵� ���� L~R����
//                        System.out.println("cnt : "+count+", max : "+max+", min : "+min);
                        answer++;
                    }
                }
            }
            return;
        }
        //inductive part
        checked[cnt] = true;
        subSet(cnt+1, checked);
        checked[cnt] = false;
        subSet(cnt+1, checked);
    }
}

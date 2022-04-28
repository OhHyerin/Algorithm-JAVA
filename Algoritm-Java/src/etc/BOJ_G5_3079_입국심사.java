package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_3079_�Ա��ɻ� {
    //����Ž��

    static int N; //�Ա��ɻ�� ����
    static int M; //��� ��
    static int[] times; //�Ա��ɻ��
    static long maxTime = 0;
    static long minTime = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        times = new int[N];

        for(int i=0;i<N;i++){
            times[i] = Integer.parseInt(br.readLine());
            maxTime = Math.max(maxTime, times[i]);
        }

//        Arrays.sort(times);

        binarySearch(N, M);
        System.out.println(minTime);

    }

    private static void binarySearch(int n, int m){
        long low = 0;  //���� ª�� �ð�
        long high = maxTime*m;  //���� �� �ð�

        //������ �ð� ������ ���ϱ� ���� ����Ž��
        while(low<=high){ //low�� high�� �Ѿ�������� �ݺ�
            long mid = (low+high)/2;  //�ð��� �߰���
            long sum = 0;

            for(int i=0;i<times.length;i++){
                long ableCount = mid/times[i];  //mid�� �ȿ� �� ���� ����� �� ������?
                sum += ableCount;
                if(sum>=m) break;
            }

            if(sum>=m){
                //break�ż� ���� ���(m�� ��� ���)
                minTime = Math.min(minTime, mid); //�ּҽð� ����
                high = mid-1;  //�ִ� �ð��� ����ð����� �۰�(mid-1)
            }else{
                //m���� �� ����ϴµ� mid���� �����ɸ��ٸ�
                low = mid+1;  //�ּ� �ð��� ���� �ð����� ũ��(mid+1)
            }

        }
    }
}

package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_17951_�𳯸��½������ӿ����������̴������ž� {
    //�̺�Ž��
    //���͵� new 1����

    /*
    ���� ����
    �������� ���� ���� �״�� K���� �׷����� ���� ��
    �� �׷쿡�� ���� ���� ������ ���� ���Ͽ�
    �� �� �ּڰ��� ���� ������ �ϱ����
     */

    //���� �� mid�� ���ϰ�, mid�� �׷� �� �ּҰ��ϰ�� �׷��� ������ k�� �̻����� �˻��Ѵ�.

    static int N;  //�������� ����
    static int K;  //�������� ���� �׷��� ��
    static int[] scores; //�� ���������� ���� ������ ����
    static int sum;  //��� ������ ��
    static int min = Integer.MAX_VALUE; //���� �� �ּڰ�

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        scores = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            scores[i] = Integer.parseInt(st.nextToken());  //���� �Է¹���
            sum += scores[i];  //sum�� ��� ������ �� (�ִ�)
            min = Math.min(min, scores[i]);
        }
        
        //�̺�Ž��
        /*
        ����
        sum : ���� ���� �ִ�
        min : ���� ���� �ּڰ�
         */
        int left = min;
        int right = sum;

        //�ּڰ�~�ִ� ���� �˻� (left�� right�� �Ѿ������ �˻�)
        while(left<=right){

            int mid = (left+right)/2;  //���� ���� �߰���

            //���� ���� ������ k���� �׷����� ���� �� �ֳ� Ȯ��
            int groupSum = 0;
            int groupCount = 0;

            for(int i=0;i<N;i++){
                groupSum += scores[i];
                if(groupSum>=mid){
                    //���� mid���� ũ�� �׷� �ڸ���
                    groupSum = 0;
                    groupCount++;
                }
            }

            if(groupCount>=K){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }

        //Ż���� �� left�� right�� �ʰ��ؾ� �����Ƿ� ���ϴ� ���� right
        System.out.println(right);

    }
}

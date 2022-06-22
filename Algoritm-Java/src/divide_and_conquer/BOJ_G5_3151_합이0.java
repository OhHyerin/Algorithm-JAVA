package divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G5_3151_����0 {
    //�̺�Ž��

    static int N;
    static int[] arr;
    static long count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);  //�������� ����

        for (int i = 0; i < N - 2; i++) {
            if (arr[i] > 0) break;  //�̹� �� ���� ���� ���� ���� 0�� �Ѿ��ٸ� �� �ڷ� �� �ʿ� ����
            find(i);  //i�� �Ǻ� (ù ��° ��)
        }


        System.out.println(count);

    }


    public static void find(int i) {
        int left = i + 1;
        int right = N - 1;

        while (left < right) {
            int leftCnt = 1;
            int rightCnt = 1;
            int sum = arr[i] + arr[left] + arr[right];  //�� ���� ��

            if (sum == 0) {
                //�� ���� ���� 0�� ��
                if (arr[left] == arr[right]) {
                        /*
                        left�� right�� ���� ���ٸ�
                        -1 1 1 1 3 �� �� left�� -1�����ʿ� �ִ� 1�̰�, right�� 3 ���ʿ� �ִ� 1�� ����
                        �׷� �߰��� ������ ������ ������ ���� nC2 ������ count�� ������
                        (������ ���� �� 2���� �������� ���ϴ� �����̹Ƿ�)
                         */
                    count += combCnt(right - left + 1);
                    break;
                }

                //left�� ���� ���� �����ѵ� right�� ��ġ�� �ʰ� left�� ���� ���� ���� ���� ���
                while (left + 1 < right && arr[left] == arr[left + 1]) {
                    leftCnt++;
                    left++;
                }

                //right�� ���� ���� �����ѵ� left�� ��ġ�� �ʰ� right�� ���� ���� ���� ���� ���
                while (left < right - 1 && arr[right] == arr[right - 1]) {
                    rightCnt++;
                    right--;
                }

                count += leftCnt * rightCnt;  //���� ������ ���� * ������ ������ ����
            }

            if (sum > 0) {
                //�� ���� ���� 0���� ũ�� right�� ����
                right--;
            } else {
                //�� ���� ���� 0���� ������ left�� ����
                left++;
            }
        }

    }

    private static int combCnt(int n) {
        //nC2�� ������ ���� ����
        return (n * (n - 1)) / 2;
    }


}


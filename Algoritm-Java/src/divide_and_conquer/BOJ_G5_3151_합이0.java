package divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G5_3151_합이0 {
    //이분탐색

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

        Arrays.sort(arr);  //오름차순 정렬

        for (int i = 0; i < N - 2; i++) {
            if (arr[i] > 0) break;  //이미 세 수중 가장 작은 값이 0을 넘었다면 그 뒤로 볼 필요 없음
            find(i);  //i가 피봇 (첫 번째 수)
        }


        System.out.println(count);

    }


    public static void find(int i) {
        int left = i + 1;
        int right = N - 1;

        while (left < right) {
            int leftCnt = 1;
            int rightCnt = 1;
            int sum = arr[i] + arr[left] + arr[right];  //세 수의 합

            if (sum == 0) {
                //세 수의 합이 0일 때
                if (arr[left] == arr[right]) {
                        /*
                        left와 right의 값이 같다면
                        -1 1 1 1 3 일 때 left는 -1오른쪽에 있는 1이고, right는 3 왼쪽에 있는 1일 것임
                        그럼 중간에 동일한 숫자의 개수를 세서 nC2 개수를 count에 더해줌
                        (동일한 숫자 중 2개를 조합으로 구하는 개수이므로)
                         */
                    count += combCnt(right - left + 1);
                    break;
                }

                //left가 다음 값을 가리켜도 right와 겹치지 않고 left의 값과 다음 값이 같은 경우
                while (left + 1 < right && arr[left] == arr[left + 1]) {
                    leftCnt++;
                    left++;
                }

                //right가 다음 값을 가리켜도 left와 겹치지 않고 right의 값과 다음 값이 같은 경우
                while (left < right - 1 && arr[right] == arr[right - 1]) {
                    rightCnt++;
                    right--;
                }

                count += leftCnt * rightCnt;  //왼쪽 동일한 개수 * 오른쪽 동일한 개수
            }

            if (sum > 0) {
                //세 수의 합이 0보다 크면 right를 감소
                right--;
            } else {
                //세 수의 합이 0보다 작으면 left를 증가
                left++;
            }
        }

    }

    private static int combCnt(int n) {
        //nC2의 개수를 세서 리턴
        return (n * (n - 1)) / 2;
    }


}


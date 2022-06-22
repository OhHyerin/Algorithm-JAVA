package divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G5_3151_합이0_fail {
    /*
    반례
    10
-6 -5 -4 -4 0 0 0 0 3 7

8
-1 0 0 1 1 1 1 1
answer : 10

10
-1 -1 0 0 0 1 1 1 1 1
answer : 31
     */

    static int N;
    static int[] arr;
    static long count;
//    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        boolean [] visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
//        combi(0, 0, new int[3]);
        Arrays.sort(arr);
//        System.out.println(Arrays.toString(arr));

        for (int i = 0; i < N - 2; i++) {
            visited = new boolean[N];
            if(arr[i]>0) break;  //첫 번째 값이 이미 양수로 넘어가면 검사할 필요 없음
            find(i, i + 1, N - 1, visited);
        }

        System.out.println(count);
//        System.out.println(hashSet.size());

    }


    public static void find(int pivot, int left, int right, boolean[] visited) {
        if (left >= right) {
            return;
        }
//        if (visited[left] && visited[right]) return;
        if (arr[pivot] + arr[left] + arr[right] == 0) {
            count++;
//            System.out.println(pivot + ", " + left + ", " + right);
            visited[right] = true;
            visited[left] = true;
//            visited[right] = true;
            if(left+1==right-1){
                find(pivot, left+1, right, visited);
            }else{
                visited[right] = true;
                visited[left] = false;
                find(pivot, left + 1, right, visited);
//                System.out.println("-----------");
                visited[right] = false;
                visited[left] = true;
                find(pivot, left, right - 1, visited);
            }
        } else if (arr[pivot] + arr[left] + arr[right] > 0) {
            find(pivot, left, right - 1, visited);
        } else if (arr[pivot] + arr[left] + arr[right] < 0) {
            find(pivot, left + 1, right, visited);
        }
    }



}


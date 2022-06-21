package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G5_3151_합이0 {

    static int N;
    static int[] arr;
    static long count;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
//        combi(0, 0, new int[3]);
        Arrays.sort(arr);
//        System.out.println(Arrays.toString(arr));

        for (int i = 0; i < N; i++) {
            find(i, i + 1, N - 1);
        }

        System.out.println(count);

    }

    public static void find(int pivot, int left, int right) {
        if (left >= right) {
            return;
        } else if (arr[pivot] + arr[left] + arr[right] == 0) {
            count++;
//            System.out.println("pivot : "+arr[pivot]+" left : "+arr[left]+" right : "+arr[right]);
            if(visited[left] && visited[right]) return;
            visited[left] = true;
            visited[right] = true;
            if(left+1 == right-1){//왼쪽이 다음 갈 위치와 오른쪽이 다음 갈 위치가 같은 경우 (한 숫자를 left, right가 한번에 가르킬 경우)
                find(pivot, left+1, right);
                return;
            }
//            else if(arr[left]==arr[left+1] && !visited[left+1]){  //왼쪽 값과 왼쪽 다음값이 똑같은 숫자일 경우
//                find(pivot, left+1, right);
//            }else if(arr[right]==arr[right-1] && !visited[right-1]){  //오른쪽 값과 오른쪽 전 값이 똑같은 경우
//                find(pivot, left, right-1);
//            }
            else {
                find(pivot, left+1, right);
                find(pivot, left, right-1);
            }
        } else if (arr[pivot] + arr[left] + arr[right] > 0) {
            find(pivot, left, right-1);
        } else if (arr[pivot] + arr[left] + arr[right] < 0) {
            find(pivot, left+1, right);
        }
    }



//    public static void find(int pivot, int left, int right) {
//        if (left >= right) {
//            return;
//        } else if (arr[pivot] + arr[left] + arr[right] == 0) {
//            count++;
//            System.out.println("pivot : "+arr[pivot]+" left : "+arr[left]+" right : "+arr[right]);
//            if(arr[left]==arr[left+1]){  //왼쪽 값과 왼쪽 다음값이 똑같은 숫자일 경우
//                find(pivot, left+1, right);
//            }else if(arr[right]==arr[right-1]){  //오른쪽 값과 오른쪽 전 값이 똑같은 경우
//                find(pivot, left, right-1);
//            }
//            else if(left+1 == right-1) {  //왼쪽이 다음 갈 위치와 오른쪽이 다음 갈 위치가 같은 경우 (한 숫자를 left, right가 한번에 가르킬 경우)
////                find(pivot, left + 1, right);
////                find(pivot, left, right - 1);
//                return;
//            }else{
//                find(pivot, left+1, right);
//                find(pivot, left, right-1);
//            }
//        } else if (arr[pivot] + arr[left] + arr[right] > 0) {
//            find(pivot, left, right-1);
//        } else if (arr[pivot] + arr[left] + arr[right] < 0) {
//            find(pivot, left+1, right);
//        }
//    }

}


//    public static void combi(int cnt, int start, int[] selected){
//        if(cnt==3){
////            System.out.println(Arrays.toString(selected));
//            int sum = 0;
//            for(int i=0;i<3;i++){
//                sum += selected[i];
//            }
//            if(sum==0){
//                count++;
//            }
//            return;
//        }
//
//        for(int i=start;i<N;i++){
//            selected[cnt] = arr[i];
//            combi(cnt+1, i+1, selected);
//        }
//    }
//}

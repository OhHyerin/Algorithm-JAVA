package divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_실2_18222_투에모스문자열 {
    //이분탐색
    //ArrayList<Integer>로 푸니까 메모리초과남
    //아직? 이해가? 잘 ? 안됨?????????

    //길이가 n이면 0~n-1까지 생각하기
    //반으로 나누면 0~(n-1)/2는 왼쪽에, (n-1)/2~(n-1)까지는 오른쪽에
    //오른쪽에 있는 수는 왼쪽에 있는 수를 반전시킨 것
    //왼쪽은 그대로 찾고, 오른쪽은 왼쪽에서 찾기

    //비트가 의미하는건 2의 i제곱을 의미
    //1인 비트의 수를 세는 것 : 큰 i부터 한 단계씩 내려오면서 수행한 것
    //즉, 비트가 1이면 오른쪽에 있다는 뜻이고, 0이면 왼쪽에 있다는 뜻

    //f(x)를 x번째에 해당하는 문자 라고 칭하면 f(1) = 0이 되고, x보다 작은 2의 거듭제곱을 y라고 한다
    //그럼 f(x) = 1-f(x-y)가 된다. 이걸 재귀로 구해주면 된다.

   static long K;
   static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Long.parseLong(br.readLine());
        arr = new long[64];
        arr[0] = 1;
        for(int i=1;i<64;i++){
            arr[i] = arr[i-1]*2; //크기?
        }
        System.out.println(sol(K));

    }

    private static int sol(long x){
        if(x==1) return 0;
        for(int i=0;i<64;i++){
            if(arr[i]>=x) return 1-sol(x-arr[i-1]);
        }
        return 0;
    }


}

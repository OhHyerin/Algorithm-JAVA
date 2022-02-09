package divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_실2_18222_투에모스문자열 {
    //이분탐색
    //ArrayList<Integer>로 푸니까 메모리초과남

    //길이가 n이면 0~n-1까지 생각하기
    //반으로 나누면 0~(n-1)/2는 왼쪽에, (n-1)/2~(n-1)까지는 오른쪽에
    //오른쪽에 있는 수는 왼쪽에 있는 수를 반전시킨 것
    //왼쪽은 그대로 찾고, 오른쪽은 왼쪽에서 찾기

    //비트가 의미하는건 2의 i제곱을 의미
    //1인 비트의 수를 세는 것 : 큰 i부터 한 단계씩 내려오면서 수행한 것
    //즉, 비트가 1이면 오른쪽에 있다는 뜻이고, 0이면 왼쪽에 있다는 뜻뜻

   static long K;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Long.parseLong(br.readLine());

        long cnt = 0;
        while(K>0){

        }

    }

    static void makeString(int cnt){
        //base
        if(cnt>=K) {
            return;
        }

        //inductive


        makeString(cnt*2);
    }


}

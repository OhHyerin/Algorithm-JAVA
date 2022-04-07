package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_G4_1253_좋다 {
    // 자료구조 (HashMap사용)
    /*
    0의 개수가 중요

    반례1
    2
    0 0
    answer : 0

    3
    0 0 0
    answer : 3
     */

    static int N;
    static int[] numbers;
    static HashMap<Integer, Integer> hashMap;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        hashMap = new HashMap<>();

        int zeroCount = 0;

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            numbers[i] = Integer.parseInt(st.nextToken());
            hashMap.put(numbers[i], i);
            if(numbers[i]==0) zeroCount++;
        }

        outer : for(int i=0;i<N;i++){
            int cur = numbers[i];  // 두 수의 합이 될 수

            for(int j=0;j<N;j++){  //numbers배열을 다시 돌며
                if(j==i) continue;  //자기 자신이라면 패스
                int num1 = numbers[j];  // 두 수중 하나
                if(num1==0 && zeroCount<=2) continue ;  //만약 뽑힌 수가 0인데 0의 개수가 2개 이하면 패스
                if(hashMap.containsKey(cur-num1)){  //cur에서 num1을 뺀 값이 hashMap에 포함되어있고
                    if(hashMap.get(cur-num1)==j) continue;  //포함되어있는 값이 num1이 아니라면
                    count++;  //개수 증가
                    continue outer;  //다음 수 뽑기
                }
            }
        }

        System.out.println(count);

    }
}

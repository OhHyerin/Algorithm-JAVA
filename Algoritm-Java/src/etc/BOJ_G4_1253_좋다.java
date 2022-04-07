package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_G4_1253_���� {
    // �ڷᱸ�� (HashMap���)
    /*
    0�� ������ �߿�

    �ݷ�1
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
            int cur = numbers[i];  // �� ���� ���� �� ��

            for(int j=0;j<N;j++){  //numbers�迭�� �ٽ� ����
                if(j==i) continue;  //�ڱ� �ڽ��̶�� �н�
                int num1 = numbers[j];  // �� ���� �ϳ�
                if(num1==0 && zeroCount<=2) continue ;  //���� ���� ���� 0�ε� 0�� ������ 2�� ���ϸ� �н�
                if(hashMap.containsKey(cur-num1)){  //cur���� num1�� �� ���� hashMap�� ���ԵǾ��ְ�
                    if(hashMap.get(cur-num1)==j) continue;  //���ԵǾ��ִ� ���� num1�� �ƴ϶��
                    count++;  //���� ����
                    continue outer;  //���� �� �̱�
                }
            }
        }

        System.out.println(count);

    }
}

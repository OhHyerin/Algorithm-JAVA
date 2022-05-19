package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G5_11509_ǳ�����߱�2 {

    static int N;
    static int[] balloons;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        balloons = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            balloons[i] = Integer.parseInt(st.nextToken());  //ǳ���� ���̸� ���� �迭
        }

        int result = 0;
        List<Integer> list = new ArrayList<>();

        for(int i=0;i<N;i++){  //��� ǳ���� �˻��ϸ鼭
            int nowHeight = balloons[i];  //���� ǳ���� ���̸� nowHeight�� ����
            if(list.contains(nowHeight)){  //
                for(int j=0;j<list.size();j++) {
                    if (list.get(j) == nowHeight) {
                        if (list.get(j) == 1) {
                            result++;
                            list.remove(j);
                        } else {
                            list.set(j, nowHeight - 1);
                        }
                        break;
                    }
                }
            }else{
                list.add(nowHeight-1);
            }
        }

        result += list.size();
        System.out.println(result);
    }
}

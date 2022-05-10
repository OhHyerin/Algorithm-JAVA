package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_G5_5430_AC {
    //���ڿ�, deque

    /*
    R : ������
    D : ������
     */

    /*
    1
    RD
    1
    [42]
     */

    static char[] orders;
    static Deque<Integer> deque;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){

            String str = br.readLine();
            orders = str.toCharArray();  //��ɻ��� �ޱ�

            int n = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine(), "[],");
            deque = new ArrayDeque<>();
            for(int i=0;i<n;i++){
                deque.add(Integer.parseInt(st.nextToken()));  //���� �� �־��ֱ�
            }

            make();

        }//t

        System.out.println(sb);

    }

    private static void make(){
        boolean start = true; //true : ��->��, false : ��->��

        for(int i=0;i<orders.length;i++){

            if(orders[i]=='R'){  //������
                start = !start;
                continue;
            }else if(orders[i]=='D'){
                if(start){  //������ �������ϰ��
                    if(!deque.isEmpty()){
                        deque.pollFirst();
                    }else{
                        sb.append("error\n");
                        return;
                    }
                }else{
                    if(!deque.isEmpty()){
                        deque.pollLast();
                    }else{
                        sb.append("error\n");
                        return;
                    }
                }
            }
        }

        //������ String���� ��ȯ
        sb.append("[");

        if(deque.isEmpty()){  //���⼭ null�˻� ���ߴ��� [null]�� ���ͼ� Ʋ�Ƚ��ϴ� ����
            sb.append("]\n");
            return;
        }

        if(start) {  //��->���� �տ��� ����
            sb.append(deque.pollFirst());
        }else{         //��->���̸� �ڿ��� ����
            sb.append(deque.pollLast());
        }

        while(deque.size()>0){
            if(start) {
                sb.append(",").append(deque.pollFirst());
            }else{
                sb.append(",").append(deque.pollLast());
            }
        }
        sb.append("]\n");
    }
}

package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_G5_5430_AC {
    //문자열, deque

    /*
    R : 뒤집기
    D : 버리기
     */

    /*
    1
    RD
    1
    [42]
     */

    static char[] orders;
    static int[] numbers;
    static Deque<Integer> deque;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){

            String str = br.readLine();
            orders = str.toCharArray();

            int n = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine(), "[],");
            deque = new ArrayDeque<>();
            for(int i=0;i<n;i++){
                deque.add(Integer.parseInt(st.nextToken()));
            }

            make();

        }//t

        System.out.println(sb);

    }

    private static void make(){
        boolean start = true; //왼쪽부터 시작

        for(int i=0;i<orders.length;i++){

            if(orders[i]=='R'){  //뒤집기
                start = !start;
                continue;
            }else if(orders[i]=='D'){
                if(start){  //방향이 오른쪽일경우
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

        //남은거 String으로 변환
        sb.append("[");

        if(deque.isEmpty()){
            sb.append("]\n");
            return;
        }

        if(start) {
            sb.append(deque.pollFirst());
        }else{
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

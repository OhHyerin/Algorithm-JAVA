package string;

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
    static Deque<Integer> deque;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){

            String str = br.readLine();
            orders = str.toCharArray();  //명령사항 받기

            int n = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine(), "[],");
            deque = new ArrayDeque<>();
            for(int i=0;i<n;i++){
                deque.add(Integer.parseInt(st.nextToken()));  //덱에 값 넣어주기
            }

            make();

        }//t

        System.out.println(sb);

    }

    private static void make(){
        boolean start = true; //true : 왼->오, false : 오->왼

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

        if(deque.isEmpty()){  //여기서 null검사 안했더니 [null]이 나와서 틀렸습니다 나옴
            sb.append("]\n");
            return;
        }

        if(start) {  //왼->오면 앞에서 삭제
            sb.append(deque.pollFirst());
        }else{         //오->왼이면 뒤에서 삭제
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

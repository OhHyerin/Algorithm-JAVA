package data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_S4_2164_카드2 {
    //백준 실버4
    //큐 + linkedlist + add + poll


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Queue<Integer> list = new LinkedList<>();
        int answer = 0;

        for(int i=0;i<n;i++){
            list.add(i);
        }

        while(!list.isEmpty()){
            if(list.size()==1){
                answer = list.poll();
            }
            else {
                list.poll();
                list.add(list.poll());
//                System.out.println(Arrays.toString(list.toArray()));
            }
        }
        System.out.println(answer+1);
    }
}

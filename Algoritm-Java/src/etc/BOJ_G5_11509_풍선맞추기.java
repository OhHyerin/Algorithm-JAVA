package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G5_11509_풍선맞추기 {
    //그리디

    static int N;
//    static List<Node> balloons;
    static Queue<Node> balloons;
//    static Node[] balloons;
    static int maxHeight = Integer.MIN_VALUE;
    static int maxIndex;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        balloons = new PriorityQueue<>();
//        balloons = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            int temp = Integer.parseInt(st.nextToken());
            balloons.add(new Node(i, temp));
        }

//        balloons.stream().sorted();

        int count = 0;

        while(!balloons.isEmpty()){
//            Node cur = balloons.remove(0);
            Node cur = balloons.poll();
            count++;
            if(balloons.size()==0) break;
//            Node next = balloons.get(0);
            Node next = balloons.peek();

//            for(int i=1;i<balloons.size();i++){
//                if(cur.value== next.value){
//                    next = balloons.get(i);
//                }else{
//                    break;
//                }
//            }
            if(next.value==cur.value-1 && next.idx>cur.idx){
                //다음 값이 현재보다 value는 1작고, idx는 크다면
                count--;
            }
//            else if(next.value==cur.value){
//
//            }
        }

        System.out.println(count);

    }

    static class Node implements Comparable<Node>{
        int idx;
        int value;

        public Node(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            if(value==o.value) return (idx-o.idx);
            return (value-o.value)*-1;
        }
    }
}

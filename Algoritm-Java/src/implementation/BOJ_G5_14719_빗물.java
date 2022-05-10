package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G5_14719_빗물 {
    //시뮬레이션, 구현
    
    //제일 높은 곳 기준으로 왼쪽, 오른쪽 검사

    static int R, C;
    static List<Node> list;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<C;i++){
            int h = Integer.parseInt(st.nextToken());
            list.add(new Node(i, h));
        }

        for(int i=1;i<list.size()-1;i++){
            //그냥 한 칸씩 기준으로 다 찾아보기 (물을 한번에 채우지않고, 각각 한 줄씩 채움)
            int left = 0;
            int right = 0;
            for(int c=i-1;c>=0;c--){
                //왼쪽 큰 수
                left = Math.max(left, list.get(c).height);
            }
            for(int c=i+1;c<list.size();c++){
                //오른쪽 큰 수
                right = Math.max(right, list.get(c).height);
            }

            if(left>list.get(i).height && right>list.get(i).height){  //현재위치가 왼쪽, 오른쪽보다 밑에있으면
                result += Math.min(left, right)-list.get(i).height;  //자기위치 물 채우기
            }

        }

        System.out.println(result);

    }

    private static class Node {
        int index;
        int height;

        public Node(int index, int height) {
            this.index = index;
            this.height = height;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "index=" + index +
                    ", height=" + height +
                    '}';
        }
    }
}

package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G5_11509_풍선맞추기 {
    //그리디

    static int N;
    static List<Integer> balloons;
    static int maxHeight = Integer.MIN_VALUE;
    static int maxIndex;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        balloons = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            int temp = Integer.parseInt(st.nextToken());
            balloons.add(temp);
            if(maxHeight<temp){
                maxIndex = i;
                maxHeight = temp;
            }
        }

//        System.out.println(maxIndex);
        int count = 0;

        while(balloons.size()>0){
//            int idx = maxIndex;
            count++;
            for(int i=maxIndex;i<balloons.size();i++){
                if(balloons.get(i)==maxHeight){
                    balloons.remove(i);
                    maxHeight--;
                    i--;
                }
            }

//            System.out.println(balloons);
//            break;

            maxHeight = 0;
            for(int i=0;i<balloons.size();i++){
                if(maxHeight<balloons.get(i)){
                    maxHeight = balloons.get(i);
                    maxIndex = i;
                }
            }
        }
        System.out.println(count);

    }
}

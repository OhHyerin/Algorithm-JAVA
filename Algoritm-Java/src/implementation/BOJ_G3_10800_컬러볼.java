package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_G3_10800_컬러볼 {
    //구현, 정렬, 누적합
    /*
    문제설명
    자기 공보다 크기가 작고 색이 다른 공을 사로잡아 그 공의 크기만큼 점수를 얻는다.
    다른 공을 사로잡은 이후에도 본인의 공의 색과 크기는 변하지 않는다.
     */
    
    /*
    color배열을 사용하지 않고 공의 크기를 내림차순으로 정렬
    내림차순으로 공을 순회하며 자신보다 크기가 작고, color가 같지 않은 공들 모두 더함
    이런 방식으로 진행했을 때, 시간초과
    
    따라서 color배열을 따로 선언한 뒤,
    공을 오름차순으로 정렬하여 각 크기마다 가질 수 있는 크기의 합을 color배열에 저장함
    현재 공의 color배열의 값 - 같은 색깔의 공 크기를 빼줌
    나온 결과값을 answer배열에 저장
     */
    

    static int N;  // 공의 개수
   static Ball[] balls;
   static int[] color;
   static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        balls = new Ball[N];
        color = new int[N+1];
        answer = new int[N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            balls[i] = new Ball(i, c, v);
        }

//        Arrays.sort(balls);
        Arrays.sort(balls, new Comparator<Ball>() {
            @Override
//            public int compare(Ball o1, Ball o2) {
//                    return (o1.value-o2.value) * -1; //크기가 큰 순서대로 내림차순
//            }
            public int compare(Ball o1, Ball o2) {
                    return (o1.value-o2.value); //크기가 작은 순서대로 오름차순
            }
        });

        int sum = 0;

        for(int i=0, j=0;i<N;i++){
            Ball a = balls[i];
            Ball b = balls[j];

            while(b.value<a.value){
                //b의 크기가 a의 크기보다 작으면
                sum += b.value;  //작은 공의 크기 누적
                color[b.color] += b.value;  //작은 공의 색 인덱스에 작은 공의 크기를 추가

                b = balls[++j];  //다음공
            }
            //현재 공이 잡을 수 있는 공의 수 (크기가 작은 전체 누적 공 크기 - 현재 공과 같은 색의 공 크기 합
            answer[a.index] = sum-color[a.color];

        }

        for(int a : answer){
            sb.append(a).append("\n");
        }


        System.out.println(sb);



    }

    static class Ball{
        int index;
        int color;
        int value;
//        int result;

        public Ball(int index, int color, int value) {
            this.index = index;
            this.color = color;
            this.value = value;
        }

    }
}

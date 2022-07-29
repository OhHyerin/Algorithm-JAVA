package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G3_2528_사다리 {
    //구현

    /*
    방향
    0 : 왼쪽에서 오른쪽
    1 : 오른쪽에서 왼쪽
     */

    static int R, C;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static Ladder[] ladders;
    static boolean isFinish;
    static int curCS;
    static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        ladders = new Ladder[R];

        for(int i=0;i<R;i++){
            st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken());  //막대기 길이
            int dir = Integer.parseInt(st.nextToken());  //막대기 이동 방향

            if(dir==0){
                ladders[i] = new Ladder(0, len, dir);
            }else if(dir==1){
                ladders[i] = new Ladder(C-len, C, dir);
            }
        }

        //----------------입력완료-----------------------

        while(true){
            result++;  //시작하자마자 result를 더하므로 초깃값을 -1
            moveCS();
            moveLadder();
            if(isFinish){
                System.out.println(result);
                break;
            }
        }

    }

    private static void moveLadder(){
       for(int i=0;i<R;i++){
           Ladder cur = ladders[i];
           if(cur.direct==0){
               //왼 -> 오
               cur.left++;
               cur.right++;

               //오른쪽 범위에 닿으면
               if(cur.right==C){
                   cur.direct=1;
               }

           }else if(cur.direct==1){
               //오 -> 왼
               cur.left--;
               cur.right--;

               //왼쪽 범위에 닿으면
               if(cur.left==0){
                   cur.direct=0;
               }
           }


       }

    }

    private static void moveCS(){
        while(true){
            if(curCS==R-1){
                isFinish = true;
                break;
            }

            Ladder cur = ladders[curCS];
            Ladder next = ladders[curCS+1];

//            if((cur.left==next.left || cur.right==next.right || cur.left==next.right || cur.right==next.left)){
            if((cur.left<=next.left&& cur.right>=next.left) || (cur.left<=next.right && cur.right>=next.right) || (cur.left>=next.left
             && cur.right<=next.right)){
                curCS++;
            }else{
                return;
            }
        }
    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }

    private static class Ladder{
        int left;
        int right;
        int direct;

        public Ladder(int left, int right, int direct) {
            this.left = left;
            this.right = right;
            this.direct = direct;
        }
    }

}

package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_15684_사다리조작 {
    //백준 골드4
    //백트래킹

    //사다리에서 가로선을 추가하여 i에서 시작해서 i에서 끝나게하는 가로선개수의 최솟값

    //순열보단 조합으로 풀어보기기


   static int C, R, count;
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        count = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new boolean[R+1][C+1];

        for(int i=0;i<count;i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[r][c] = true;

        }

        //----입력확인---------
//        for(int i=1;i<=R;i++){
//            for(int j=1;j<=C;j++){
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }
        //-------------------

        if(check()){
            //추가 안해도 걍 돌아감
            System.out.println(0);
        } else {
            for(int i=1;i<=3;i++){ //추가 사다리 3개까지만 돌려보면 됨 (문제에서 제시)
                permutation(1, 0, i);
            }
            //프로그램 종료 안됐으면
            System.out.println(-1);
        }

    }

    private static void permutation(int start, int cnt, int finish){
        if(cnt==finish){ //사다리 추가 개수
            if(check()){  //사다리 다 돌았을 때 true면(i==i)
                System.out.println(finish);
                System.exit(0); //프로그램 초기화
            }
            return;
        }

        for(int i = start;i<=R;i++){
            for(int j=1;j<C;j++){
                //map[i][j]가 true면 기존 있던 사다리(그 자리엔 사다리가 또 들어갈 수 없으니까 continue)
                if(map[i][j]) continue;

                //원래 사다리가 있던 위치가 아니면
                map[i][j] = true;
                permutation(i, cnt+1, finish);  //사다리가 들어갈 수 있는 위치에 모두 넣어줌
                map[i][j] = false;
            }
        }

    }

    private static boolean check(){
        for(int i=1;i<=C;i++){ //col만큼 반복
            int x = i; //col위치 설정
            for(int j=1;j<=R;j++){ //row만큼 반복
                if(map[j][x-1]) x--;  //현재 col기준 왼쪽이 true면(사다리있으면) 위치 -1로 이동
                else if(map[j][x]) x++; //현재 col기준 true면 위치 +1로 이동
            }
            if(x != i) return false; //도착위치가 처음 i위치와 다르면 false return
        }
        return true; //for문 다 돌아서 나왔으면 true return
    }

}

package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_G5_20055_컨베이어벨트위의로봇 {
    //시뮬레이션, 구현

    /*
    1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
    2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다
        이동할 수 없다면 가만히 있는다
    3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
    4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
     */

    static int N, K;
    static ArrayList<Pos> belt;
    static boolean[] robot;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

//        belt = new int[N*2];
        belt = new ArrayList<>();
        robot = new boolean[N*2];


        st = new StringTokenizer(br.readLine());
        for(int i=0;i<2*N;i++){
//            belt[i] = Integer.parseInt(st.nextToken());
            belt.add(new Pos(Integer.parseInt(st.nextToken()), false));
        }

//        System.out.println(Arrays.toString(belt));
        //--------입력완료-------------

        int depth = 0;
        count = K;
        while(true){
            depth++;
            rotate();
            //내구도가 0인 칸의 개수 세기
            if(count<=0) break;
        }

        System.out.println(depth);

    }

    private static void rotate(){
        //로봇과 벨트가 함께 회전
        belt.add(0, belt.remove(belt.size()-1));
        belt.get(N).robot = false;
//        System.out.println("벨트회전: " + belt);

        //로봇 이동
        for(int i=N-1;i>=0;i--){
            Pos cur = belt.get(i);
            Pos next = belt.get(i+1);

            if(i==N-1){
                cur.robot =false;
                continue;
            }
            if(cur.robot){
                //현재 위치에 로봇이 있고
                if(!next.robot && next.belt>0){
                    //다음 이동할 위치에 로봇이 없고, 내구성이 1이상이면
                    cur.robot = false;
                    next.robot = true;
                    next.belt -= 1;
                    if(next.belt==0) count--;
                }
            }
        }
//        System.out.println("로봇 회전: " + belt);

        //올리는 위치에 있는 칸의 내구도가 0이 아니라면 올리는 위치에 로봇을 올린다.
        if(belt.get(0).belt>0 && !belt.get(0).robot){
            belt.get(0).robot = true;
            belt.get(0).belt -= 1;
            if(belt.get(0).belt==0) count--;
        }

//        System.out.println("로봇 올리기: "+belt);


    }

    private static class Pos{
        int belt;
        boolean robot;

        public Pos(int belt, boolean robot) {
            this.belt = belt;
            this.robot = robot;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "belt=" + belt +
                    ", robot=" + robot +
                    '}';
        }
    }

}

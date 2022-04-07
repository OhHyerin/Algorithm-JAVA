package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_G4_15685_드래곤커브 {
    //삼성 SW 역량 테스트 기출

    static int N;
    static boolean[][] map;
    /*
    0 : 오, 1 : 위, 2 : 왼, 3 : 아래
     */
    static int[] dc = {1, 0, -1, 0};
    static int[] dr = {0, -1, 0, 1};
    static ArrayList<Integer> direct;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new boolean[102][102];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());  //시작 좌표
            int y = Integer.parseInt(st.nextToken());  //시작 좌표
            int dir = Integer.parseInt(st.nextToken());  //방향
            int gnr = Integer.parseInt(st.nextToken());  //세대

            direct = new ArrayList<>();  //앞으로 갈 direction정보 list
            direct.add(dir);  //맨 처음 시작 방향 add
            direct = makeGnr(direct, gnr);
//            System.out.println(direct);

            //방향 list가 완성되었으면 시작 좌표부터 방문하는 모든 칸을 true로 만들어주기
            //boolean배열을 사용할 수 있는 이유는 방문 했는지 안했는지만 확인하므로
            map[y][x] = true;
            for(int go : direct){
                y += dr[go];
                x += dc[go];
                map[y][x] = true;
            }
        }

        int answer = 0;
        for(int i=0;i<=100;i++){
            for(int j=0;j<=100;j++){
                if(map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1]){
                    if(map[i][j]) answer++;
                }
            }
        }

        System.out.println(answer);

    }

    private static ArrayList<Integer> makeGnr(ArrayList<Integer> dir, int gnr) {
        /*
        만약 처음 방향이 0으로 주어진다면
        0세대 : 0
        1세대 : 0 1
        2세대 : 0 1 2 1
        3세대 : 0 1 2 1 2 3 2 1
        ...
         */

        for (int g = 0; g < gnr; g++) {
            for (int i = dir.size() - 1; i >= 0; i--) {
                dir.add((dir.get(i) + 1)%4);
            }
        }
        return dir;
    }

    public static class BOJ_G4_15961_회전초밥 {

        static int N; // 회전 초밥 벨트에 놓인 접시의 수
        static int D; // 초밥의 가짓수
        static int K; // 연속해서 먹는 접시의 수
        static int C; // 쿠폰 번호
        static ArrayList<Integer> belt;
        static int[] select;
        static int maxCount = Integer.MIN_VALUE;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            StringBuilder sb = new StringBuilder();

            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

    //        belt = new int[N];
            belt = new ArrayList<>();
            select = new int[D+1];

            for (int i = 0; i < N; i++) {
    //            belt[i] = Integer.parseInt(br.readLine());
                belt.add(Integer.parseInt(br.readLine()));
            }
            for(int i=0;i<K-1;i++){
                int temp = belt.get(i);
                belt.add(temp);
            }

    //        System.out.println(belt);

            pick();

            System.out.println(maxCount);
        }


        public static void pick() {
            int count = 0;
            for(int i=0;i<K;i++){
                if(select[belt.get(i)]==0) count++;
                select[belt.get(i)]++;
            }

            maxCount = count;

            for(int i=1;i<N;i++){
                if(maxCount<=count){
                    if(select[C]==0){
                        maxCount = count+1;
                    }else{
                        maxCount = count;
                    }
                }

                select[belt.get(i-1)]--;
                if(select[belt.get(i-1)]==0) count--;
                if(select[belt.get(i+K-1)]==0) count++;
                select[belt.get(i+K-1)]++;
    //            System.out.println("i : "+i+" count : "+count);
            }


        }

    }
}

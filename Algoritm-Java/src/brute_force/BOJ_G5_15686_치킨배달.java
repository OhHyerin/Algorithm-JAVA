package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G5_15686_치킨배달 {
    //백준 골드5
    //완전탐색
    //치킨집을 조합으로 m개 고른 뒤, 모든 customer와 거리 비교
    //치킨집과 customer의 거리는 최솟값
    //그 최솟값의 합들의 최솟값이 구하는 답

    static int n, m;
    static int[][] map;
    static List<Pos> store;
    static List<Pos> customer;
    static int minCost = Integer.MAX_VALUE;
    static boolean[] isOpen;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
//        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n + 1][n + 1];
        store = new ArrayList<>();
        customer = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==1){
                    customer.add(new Pos(i, j));
                } else if(map[i][j]==2){
                    store.add(new Pos(i, j));
                }
            }
        }
        //------------입력 완료----------------
        isOpen = new boolean[store.size()]; //해당 치킨집 뽑혔나 안뽑혔나
        combination(0, 0);
        System.out.println(minCost);



    }

    private static void combination(int start, int select){
        //base part
        if(select==m){
            //치킨 가게 중 m개를 다 뽑았으면
            int distance;
            int temp = 0; //뽑힌 조합의 치킨거리 최솟값
            for(int i=0;i<customer.size();i++){
                //뽑힌 치킨집과 고객의 모든 집 다 비교
                int minCus = Integer.MAX_VALUE;//고객별 가장 가까운 치킨집 찾기
                for(int j=0;j<store.size();j++){
                    if(isOpen[j]){
                        //열기로 한 치킨집이면 거리구함
                        distance = calDist(customer.get(i), store.get(j));
                        minCus = Math.min(minCus, distance);
                    }
                }
                temp += minCus;
            }
            //고객별 가장 가까운 치킨집 골랐으면 치킨거리 최솟값 비교 후 갱신
            minCost = Math.min(minCost, temp);
            return;
        }


        //inductive part
        for(int i=start;i<store.size();i++){  //치킨가게 개수 안에서 뽑음
            isOpen[i] = true;
            combination(i+1, select+1);
            isOpen[i] = false;
        }
    }

    private static int calDist(Pos a, Pos b){
        return Math.abs(a.r-b.r)+Math.abs(a.c-b.c);
    }

    static class Pos {
        int r;
        int c;

        @Override
        public String toString() {
            return "Pos{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }


}

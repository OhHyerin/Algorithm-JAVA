package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_S1_9205_맥주마시면서걸어가기 {
    //최단거리, 플로이드

    static int N;
    static int dist;
    static List<Pos> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        outer : for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());

            list = new ArrayList<>();
            boolean[][] canGo = new boolean[N+2][N+2];

            for(int i=0;i<N+2;i++){
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                list.add(new Pos(r, c));
            }

            //---------------입력완료----------------------------

            for(int i=0;i<N+2;i++){
                for(int j=0;j<N+2;j++){
                    Pos from = list.get(i);
                    Pos to = list.get(j);
                    dist = distance(from, to);

                    if(dist<=50*20){
                        canGo[i][j] = true;
                    }
                }
            }

            for(int k=0;k<N+2;k++){ //경유지
                for(int i=0;i<N+2;i++){  //출발지
                    for(int j=0;j<N+2;j++){  //도착지
                        if(canGo[i][k] && canGo[k][j]){
                            canGo[i][j] = true;
                        }
                    }
                }
            }

            sb.append(canGo[0][N+1]? "happy\n" : "sad\n");

        }//t
        System.out.println(sb);
    }

    private static int distance(Pos a, Pos b){
        return Math.abs(a.r-b.r) + Math.abs(a.c-b.c);
    }

    private static class Pos{
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}

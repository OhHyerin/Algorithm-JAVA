package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_모의SW_4012_요리사 {

    static int N;
    static int [][] food;  //시너지 배열
    static boolean[] isSelected;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            N = Integer.parseInt(br.readLine());
            food = new int[N][N];
            isSelected = new boolean[N];
            min = Integer.MAX_VALUE;
            for(int i=0;i<N;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++){
                    food[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            //-------------입력 완료-----------

            combination(0,0);
            sb.append("#").append(t).append(" ").append(min).append("\n");

        } //t
        System.out.println(sb);
    }

    private static void combination(int cnt, int start){
        //base part
        if(cnt==N/2){
            int[] a = new int[N/2];
            int[] b = new int[N/2];
            int indexA = 0;
            int indexB = 0;

            for(int i=0;i<N;i++){
                if(isSelected[i]){
                    a[indexA++] = i;
                }else{
                    b[indexB++] = i;
                }
            }

            int tastyA = 0;
            int tastyB = 0;

            for(int i=0;i<a.length;i++){
                for(int j=0;j<a.length;j++){
                    tastyA += food[a[i]][a[j]]; //각 재료의 시너지 더하기
                    tastyB += food[b[i]][b[j]];
                }
            }
            min = Math.min(min,Math.abs(tastyA-tastyB));



            return;
        }

        //inductive part
        for(int i = start; i<N;i++){
            isSelected[i] = true;
            combination(cnt+1, i+1);
            isSelected[i] = false;
        }
    }

}

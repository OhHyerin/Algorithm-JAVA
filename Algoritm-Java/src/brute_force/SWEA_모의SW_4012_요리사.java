package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_모의SW_4012_요리사 {
    //조합

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

            combination(N/2,new boolean[N/2], 0);
            sb.append("#").append(t).append(" ").append(min).append("\n");

        } //t
        System.out.println(sb);
    }

    private static void combination(int toChoose, boolean[] choosed, int startIdx){
        if(toChoose==0){
            int sum = 0;
            for(int i=0; i<N;i++){
                for(int j=0;j<N;j++){
                    if(choosed[i]==choosed[j]){
                        //선택된 재료의 조합
                        if(choosed[i]){
                            sum += food[i][j];
                        }
                        //비선택된 재료의 조합
                        else{
                            sum += food[i][j];
                        }
                    }
                }
            }
            min = Math.min(min, Math.abs(sum));

            return;
        }
        for(int i=startIdx; i<N;i++){
            choosed[i] = true;
            combination(toChoose-1, choosed, i+1);
            //원랜 덮어씌워서 원복시키지 않아도 되지만 이 경우엔 해야함
            choosed[i] = false;
        }
    }




    private static int getSatis(int [] comb){
        int satis = 0;
        for(int i=0;i<comb.length;i++){
            for(int j=0;j<comb.length;j++){
                satis += food[comb[i]][comb[i]];
            }
        }
        return satis;
    }




}

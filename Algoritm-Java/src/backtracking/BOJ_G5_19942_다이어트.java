package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G5_19942_다이어트 {
    //완탐
    //백트래킹

    static int N; //식재료의 개수
    static int[] minNut;  //최소 영양성분 (단백질, 지방, 탄수화물, 비타민)
    static int[][] nuts;  //각 영양성분 (단백질, 지방, 탄수화물, 비타민, 가격)
    static int minCost = Integer.MAX_VALUE;
    static List<String> minList = new ArrayList<>();

   public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        minNut = new int[4];
        nuts = new int[N+1][5];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<4;i++){
            minNut[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<5;j++){
                nuts[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        subSet(0, new boolean[N]);

       if(minCost==Integer.MAX_VALUE){
           System.out.println(-1);
       }else{
           System.out.println(minCost);

           //여기서 String으로 바꿔서 사전순정렬하려했더니 안됨
//           String[] result = new String[minList.size()];
//
//           for(int i=0;i<minList.size();i++){
////               System.out.print(minList.get(i)+" ");
//               result[i] = minList.get(i).toString();
//           }
//           Arrays.sort(result);
//           for(int i=0;i<result.length;i++){
//               System.out.print(result[i]+" ");
//           }
           Collections.sort(minList);
           System.out.println(minList.get(0));
       }

    }

    private static void subSet(int cnt, boolean[] isSelected){
       if(cnt==N){
           List<Integer> select = new ArrayList<>();
           for(int i=0;i<N;i++){
               if(isSelected[i]) select.add(i+1);
           }

//           for(int i=0;i<select.size();i++) {
//               System.out.print(select.get(i)+" ");
//           }
//           System.out.println();
//           System.out.println("-------------");

           //최소 영양소 만족하는지 검사
           int cost = 0;
           int cnt1 = 0;
           int cnt2 = 0;
           int cnt3 = 0;
           int cnt4 = 0;
           for(int i=0;i<select.size();i++){
               int num = select.get(i);
               cnt1 += nuts[num][0];
               cnt2 += nuts[num][1];
               cnt3 += nuts[num][2];
               cnt4 += nuts[num][3];
               cost += nuts[num][4];
           }

           if(cnt1<minNut[0] || cnt2<minNut[1] || cnt3<minNut[2] || cnt4<minNut[3]){
               //하나라도 최소영양소 만족 못하면 걍 return
               return;
           }
           //만족 한다면 최소 가격
           //|| (minCost>=cost && mianList.size()>select.size())
           if(minCost>=cost ){
               if(minCost>cost) minList.clear();
               StringBuilder temp = new StringBuilder();
               for(int i=0;i<select.size();i++){
                   temp.append(select.get(i)+" ");
               }
               minList.add(temp.toString());
               minCost = cost;
           }

           return;
       }

       isSelected[cnt] = true;
       subSet(cnt+1, isSelected);
       isSelected[cnt] = false;
       subSet(cnt+1, isSelected);

    }
}

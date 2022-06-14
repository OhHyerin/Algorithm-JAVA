package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G5_19942_���̾�Ʈ {
    //��Ž
    //��Ʈ��ŷ

    static int N; //������� ����
    static int[] minNut;  //�ּ� ���缺�� (�ܹ���, ����, ź��ȭ��, ��Ÿ��)
    static int[][] nuts;  //�� ���缺�� (�ܹ���, ����, ź��ȭ��, ��Ÿ��, ����)
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
            minNut[i] = Integer.parseInt(st.nextToken());  //mp, mf, ms, mv
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

           //���⼭ String���� �ٲ㼭 �����������Ϸ��ߴ��� �ȵ�
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
           Collections.sort(minList);  //�ּ� ���� ����Ʈ���� ����
           System.out.println(minList.get(0));  //�� �� ���������� �� �������°� ���
       }

    }

    private static void subSet(int cnt, boolean[] isSelected){  //�ε��� ��ȣ��
       if(cnt==N){  //������ �Ȼ����� ����Ǿ��ִ� isSelected�� �ϼ�
           List<Integer> select = new ArrayList<>();
           for(int i=0;i<N;i++){
               if(isSelected[i]) select.add(i+1);  //���� �ε����� ����Ʈ select�� ���� ����
           }

//           for(int i=0;i<select.size();i++) {
//               System.out.print(select.get(i)+" ");
//           }
//           System.out.println();
//           System.out.println("-------------");

           //�ּ� ����� �����ϴ��� �˻�
           int cost = 0;  //����
           int cnt1 = 0;  //�ܹ���
           int cnt2 = 0;  //����
           int cnt3 = 0;  //ź��ȭ��
           int cnt4 = 0;  //��Ÿ��
           for(int i=0;i<select.size();i++){  //�� ������ ��� ������
               int num = select.get(i);
               cnt1 += nuts[num][0];
               cnt2 += nuts[num][1];
               cnt3 += nuts[num][2];
               cnt4 += nuts[num][3];
               cost += nuts[num][4];
           }

           if(cnt1<minNut[0] || cnt2<minNut[1] || cnt3<minNut[2] || cnt4<minNut[3]){
               //�ϳ��� �ּҿ���� ���� ���ϸ� �� return
               return;
           }
           //���� �Ѵٸ� �ּ� ����
           //|| (minCost>=cost && mianList.size()>select.size())
           if(minCost>=cost ){  //���� �ּҰ��ݺ��� �ΰų� ���������̶��
               if(minCost>cost) minList.clear();  //���� �ּҰ��ݺ��� ������ minList�� ����
               StringBuilder temp = new StringBuilder();
               for(int i=0;i<select.size();i++){  //���õ� �������� ������ string���� ������
                   temp.append(select.get(i)+" ");
               }
               minList.add(temp.toString());  //����Ʈ�� string ���·� �����
               minCost = cost;  //���� �ּҰ��� ����
           }

           return;
       }

       isSelected[cnt] = true;
       subSet(cnt+1, isSelected);
       isSelected[cnt] = false;
       subSet(cnt+1, isSelected);

    }
}

package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_����SW_4012_�丮�� {
    //����

    static int N;
    static int [][] food;  //�ó��� �迭
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
            //-------------�Է� �Ϸ�-----------

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
                        //���õ� ����� ����
                        if(choosed[i]){
                            sum += food[i][j];
                        }
                        //���õ� ����� ����
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
            //���� ������� ������Ű�� �ʾƵ� ������ �� ��쿣 �ؾ���
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

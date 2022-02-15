package divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_1074_Z {

    static int n;
    static int r, c;
    static int cnt; //�湮 ����
    //���� �� ���� (0,0) (0,1) (1,0) (1,1)  -> Z���
    static int[] dr = {0, 0, 1, 1};
    static int[] dc = {0, 1, 0, 1};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        n = (int)Math.pow(2, N);
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        find(n, 0, 0);
        System.out.println(sb);
    }

    private static void find(int size, int curR, int curC){
        //���� ���������� �����ٰ� size�� 2���Ǹ� Ž�� (ũ�Ⱑ 2�� ����)
        if(size==2){
            for(int d=0;d<4;d++){
                int findR = curR+dr[d];
                int findC = curC+dc[d];

                if(findR==r && findC==c){
                    //Ž���ϴ� ������ ã�� r,c���� ���ٸ�
                    sb.append(cnt);
                    return;
                }
                cnt++;
            }
            return;
        }

        for(int i=0;i<4;i++){
            int dR1 = curR+size/2*dr[i];
            int dR2 = curR+size/2*(dr[i]+1)-1;
            int dC1 = curC+size/2*dc[i];
            int dC2 = curC+size/2*(dc[i]+1)-1;

            if(r>=dR1 && r<=dR2 && c>=dC1 && c<=dC2){
                cnt = cnt+(size/2*size/2*i);
                find(size/2, dR1, dC1);
            }

        }



//        int divideR = curR+size/2;
//        int divideC = curC+size/2;
//
//        if(divideR>r && divideC>c){
//            //����
//            find(size/2, curR, curC);
//        } else if(divideR>r && divideC<c){
//            //����
//            find(size/2, curR, divideC);
//        }else if(divideR<r && divideC>c){
//            //�޾Ʒ�
//            find(size/2, divideR, curC);
//        }else if(divideR<r && divideC<c){
//            //���Ʒ�
//            find(size/2, divideR, divideC);
//        }



    }
}

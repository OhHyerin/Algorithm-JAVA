package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_15684_��ٸ����� {
    //���� ���4
    //��Ʈ��ŷ

    //��ٸ����� ���μ��� �߰��Ͽ� i���� �����ؼ� i���� �������ϴ� ���μ������� �ּڰ�

    //�������� �������� Ǯ����


   static int C, R, count;
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        count = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new boolean[R+1][C+1];

        for(int i=0;i<count;i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[r][c] = true;

        }

        //----�Է�Ȯ��---------
//        for(int i=1;i<=R;i++){
//            for(int j=1;j<=C;j++){
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }
        //-------------------

        if(check()){
            //�߰� ���ص� �� ���ư�
            System.out.println(0);
        } else {
            for(int i=1;i<=3;i++){ //�߰� ��ٸ� 3�������� �������� �� (�������� ����)
                permutation(1, 0, i);
            }
            //���α׷� ���� �ȵ�����
            System.out.println(-1);
        }

    }

    private static void permutation(int start, int cnt, int finish){
        if(cnt==finish){ //��ٸ� �߰� ����
            if(check()){  //��ٸ� �� ������ �� true��(i==i)
                System.out.println(finish);
                System.exit(0); //���α׷� �ʱ�ȭ
            }
            return;
        }

        for(int i = start;i<=R;i++){
            for(int j=1;j<C;j++){
                //map[i][j]�� true�� ���� �ִ� ��ٸ�(�� �ڸ��� ��ٸ��� �� �� �� �����ϱ� continue)
                if(map[i][j]) continue;

                //���� ��ٸ��� �ִ� ��ġ�� �ƴϸ�
                map[i][j] = true;
                permutation(i, cnt+1, finish);  //��ٸ��� �� �� �ִ� ��ġ�� ��� �־���
                map[i][j] = false;
            }
        }

    }

    private static boolean check(){
        for(int i=1;i<=C;i++){ //col��ŭ �ݺ�
            int x = i; //col��ġ ����
            for(int j=1;j<=R;j++){ //row��ŭ �ݺ�
                if(map[j][x-1]) x--;  //���� col���� ������ true��(��ٸ�������) ��ġ -1�� �̵�
                else if(map[j][x]) x++; //���� col���� true�� ��ġ +1�� �̵�
            }
            if(x != i) return false; //������ġ�� ó�� i��ġ�� �ٸ��� false return
        }
        return true; //for�� �� ���Ƽ� �������� true return
    }

}

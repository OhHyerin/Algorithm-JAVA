package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G5_15686_ġŲ��� {
    //���� ���5
    //����Ž��
    //ġŲ���� �������� m�� �� ��, ��� customer�� �Ÿ� ��
    //ġŲ���� customer�� �Ÿ��� �ּڰ�
    //�� �ּڰ��� �յ��� �ּڰ��� ���ϴ� ��

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
        //------------�Է� �Ϸ�----------------
        isOpen = new boolean[store.size()]; //�ش� ġŲ�� ������ �Ȼ�����
        combination(0, 0);
        System.out.println(minCost);



    }

    private static void combination(int start, int select){
        //base part
        if(select==m){
            //ġŲ ���� �� m���� �� �̾�����
            int distance;
            int temp = 0; //���� ������ ġŲ�Ÿ� �ּڰ�
            for(int i=0;i<customer.size();i++){
                //���� ġŲ���� ���� ��� �� �� ��
                int minCus = Integer.MAX_VALUE;//���� ���� ����� ġŲ�� ã��
                for(int j=0;j<store.size();j++){
                    if(isOpen[j]){
                        //����� �� ġŲ���̸� �Ÿ�����
                        distance = calDist(customer.get(i), store.get(j));
                        minCus = Math.min(minCus, distance);
                    }
                }
                temp += minCus;
            }
            //���� ���� ����� ġŲ�� ������� ġŲ�Ÿ� �ּڰ� �� �� ����
            minCost = Math.min(minCost, temp);
            return;
        }


        //inductive part
        for(int i=start;i<store.size();i++){  //ġŲ���� ���� �ȿ��� ����
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

package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_1025_������ã�� {
    //���� ���5
    //��Ž?

    static int n, m;
    static int[][] grid;
    static double make_num;
    static double max = Double.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        grid = new int[n][m];

        for(int r=0;r<n;r++){
            String str = br.readLine();
            for(int c=0;c<m;c++){
                grid[r][c] = str.charAt(c)-'0';
            }
        }
        //----------------�Է¿Ϸ�------------------

    }
    private static void search(int dr, int dc){
        //(i,j) : �������� ������
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){

            }
        }
    }

    private static void isSquared(double square){
        //pow(����, �ŵ����� Ƚ��) : �ŵ������� ������ִ� �Լ�
        //sqrt(����) : �������� �����ִ� �Լ�
        double sqrt = (double)Math.sqrt(square);
        if(square==Math.pow(sqrt, 2)){
            max = Math.max(max, square);
        }
    }
}

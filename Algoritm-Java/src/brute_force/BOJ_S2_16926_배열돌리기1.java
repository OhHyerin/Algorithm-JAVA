package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S2_16926_�迭������1 {
    //�ε��� ����Ͽ� �迭�� ������ ����

    // N,M : �迭�� ũ��, R : ȸ�� ��
    static int N, M, R;
    static int [][] grid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        grid = new int[N][M];

        for(int r=0;r<N;r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0;c<M;c++){
                grid[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        //---------------- �Է¿Ϸ� ---------------------
        int rotation_cnt = Math.min(N, M) / 2;
        for(int i=0;i<R;i++) {
            rotate(rotation_cnt);
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                sb.append(grid[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    public static void rotate(int r){
        for(int depth = 0; depth<r;depth++){
            int temp = grid[depth][depth];

            //�� ��������
            for(int i=depth+1;i<M-depth;i++){
                grid[depth][i-1] = grid[depth][i];
            }

            //���� ����
            for(int i=depth+1;i<N-depth;i++){
                grid[i-1][M-1-depth] = grid[i][M-1-depth];
            }

            //�Ʒ� ����������
            for(int i=M-2-depth;i>=depth;i--){
                grid[N-1-depth][i+1] = grid[N-1-depth][i];
            }

            //�� �Ʒ���
            for(int i=N-2-depth; i>=depth; i--){
                grid[i+1][depth] = grid[i][depth];
            }

            grid[depth+1][depth] = temp;
        }
    }


}

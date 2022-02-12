package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_16927_�迭������2 {
    //�ε��� ����Ͽ� �迭�� ������ ����
    //�迭������1�� �ٸ����� R�� ũ�Ⱑ �ſ� ũ��.
    //�ð� �ʰ� ����

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
        int depth = Math.min(N, M) / 2;
        
        //depth�� ���缭 ������
        for(int d=0;d<depth;d++){
            //R�� �ſ� ŭ! -> �ѹ��� ���� ������ �����ϱ� ���¼�ġ��
            int round = (N-1-2*d)*2 + (M-1-2*d)*2;
            int cnt = R%round;
            for(int r=0;r<cnt;r++){
                rotate(d);
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                sb.append(grid[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    public static void rotate(int d){
        int keep = grid[d][d];
        // ���ʶ���, �������� ��ĭ��
        for(int c=d+1 ; c<M-d;c++){
            grid[d][c-1] = grid[d][c];
        }

        //������ ���� ��ĭ��
        for(int r=d+1;r<N-d;r++){
            grid[r-1][M-1-d] = grid[r][M-1-d];
        }

        //�Ʒ�����, ���������� ��ĭ��
        for(int c=M-d-2;c>=d;c--){
            grid[N-d-1][c+1] = grid[N-d-1][c];
        }
        
        //���� �Ʒ��� ��ĭ��
        for(int r=N-d-2;r>=d;r--){
            grid[r+1][d] = grid[r][d];
        }
        
        //keep��״��� �־��ֱ�
        grid[d+1][d] = keep;

    }
}

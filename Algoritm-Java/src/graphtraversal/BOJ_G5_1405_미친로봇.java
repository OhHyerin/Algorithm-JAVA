package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_1405_��ģ�κ� {
    //dfs, ���
    //��� ��츦 Ž���ؾ� �ϴ� ��� dfs���
    //������ �����ϰ�, ��ǥ ��°͸� ���ϸ� dfs�� ����� ����

    static int N;  //1~14
    //��>��>��>��
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    /*
    �κ��� ������ �� �ִ� ����
    �κ��� �ִ� ������ �� �ִ� Ƚ���� 14�̹Ƿ�
    �κ��� ��ġ�� (15,15)�� �� 1~29���� ������ �� ����
     */
    static boolean[][] visited = new boolean[30][30];
    static double[] direct = new double[4]; //��,��,��,��
    static double answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        for(int i=0;i<4;i++){
            direct[i] = Double.parseDouble(st.nextToken())/100.0;
        }

        visited[15][15] = true;  //�κ��� ������

        dfs(15, 15, 1, 0);
        System.out.println(answer);

    }

    private static void dfs(int r, int c, double per, int count){

        if(count==N){
            //�κ��� N�� �� �������ٸ�
            answer += per;
            return;
        }

        for(int d=0;d<4;d++){
            int nr = r+dr[d];
            int nc = c+dc[d];

            if(!visited[nr][nc] && per!=0){
                //�湮���� �ʾҰ�, Ȯ���� 0�� �ƴ϶�� > �̵��� �� ����
                visited[nr][nc] = true;
                dfs(nr, nc, per*direct[d], count+1); //Ȯ�� : per*direct[d]
                visited[nr][nc] = false;  //�����·� ��������
            }
        }


    }

}

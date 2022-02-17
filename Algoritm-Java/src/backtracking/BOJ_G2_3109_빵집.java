package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G2_3109_���� {
    //���� ���2
    //flag������ backtracking

    static int R, C;
    static char[][] map;
    static int[] dr = {-1, 0, 1};
    static int[] dc = {1, 1, 1};
    static int answer = 0;
    static boolean arrived = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for(int r=0;r<R;r++){
            String str = br.readLine();
            for(int c=0;c<C;c++){
                map[r][c] = str.charAt(c);
            }
        }
        //----------------�Է¿Ϸ�-------------------
        for(int r=0;r<R;r++){
            if(map[r][0]=='.'){ //col�� 0 �� row���� .�� ������ ���
                arrived = false;
                setPipe(r, 0);
            }
        }
        System.out.println(answer);

    }
    private static void setPipe(int r, int c){
        if(c==C-1){
            map[r][c]='x'; //�湮ó��
            arrived = true; //�ش� ��� �̹� ����
            answer++; //����++
            return;
        }
        for(int i=0;i<3;i++){
            int nextR = r+dr[i];
            int nextC = c+dc[i];
            if(isIn(nextR,nextC) && map[nextR][nextC]=='.'){
                map[nextR][nextC] = 'x'; //�湮ó��
                setPipe(nextR, nextC);
                if(arrived) return;
            }
        }

    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }

}

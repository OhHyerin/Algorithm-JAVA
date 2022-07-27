package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_9207_��׼ָ��׾� {
    //����, ��Ʈ��ŷ

    static char[][] map;
    static int R = 5;
    static int C = 9;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int cntPin;
    static int leftPin;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){

            map = new char[R][C];
            leftPin = Integer.MAX_VALUE;
            cntPin = 0;

            int cnt = 0;

            for(int i=0;i<R;i++){
                String str = br.readLine();
                for(int j=0;j<C;j++){
                    map[i][j] = str.charAt(j);
                    if(map[i][j]=='o'){
                        cnt++;
                    }
                }
            }

            br.readLine();  //����

            cntPin = cnt;  //���� pin�� ����
            leftPin = cnt; //���� pin�� ���� �� �ּ�

            for(int i=0;i<R;i++){
                for(int j=0;j<C;j++){
                    if(map[i][j]=='o'){
                        dfs(i, j);
                    }
                }
            }

            int answer = cnt-leftPin; //ó�� pin�� �������� ���� pin�� ������ ���� �ּ� �̵� Ƚ�� (�����̸� ���� �������ٴ� �Ŵϱ�!)


            System.out.println(leftPin +" "+answer);

        }

    }
    private static void dfs(int r, int c){
        for(int d=0;d<4;d++){
            // pin1 (������ ��)
            int nr1 = r+dr[d];
            int nc1 = c+dc[d];
            
            if(!isIn(nr1, nc1) || map[nr1][nc1]!='o') continue;  //���� ������ ���� ������ ����ų�, o�� �ƴ϶�� continue
            
            
            // pin2 (���� �Ѿ ��ġ)
            int nr2 = nr1+dr[d];
            int nc2 = nc1+dc[d];

            if(!isIn(nr2, nc2) || map[nr2][nc2]!='.') continue;  //�Ѿ ��ġ�� ������ ����ų� �� ���� �ƴ϶�� continue

            map[r][c] = '.';  //���� �ڸ� ��������� ����
            map[nr1][nc1] = '.';  //�� �ϳ� ���� �ִ� �ڸ� �� �������� ���� (������)
            map[nr2][nc2] = 'o';  //�Ѿ �ڸ� ������ ����

            cntPin--;  //�� ���� --

            for(int i=0;i<R;i++){
                for(int j=0;j<C;j++){
                    if(map[i][j]=='o')
                        dfs(i, j);  //���� �� ã�ư�
                }
            }

            leftPin = Math.min(leftPin, cntPin);   //���� ���� �ּ� ����
            //���� dfs�� ���� ���� ��������
            map[r][c] = 'o';
            map[nr1][nc1] = 'o';
            map[nr2][nc2] = '.';

            cntPin++;  //�ȿ����ΰŷ�~

        }

    }

    private static boolean isIn(int r, int c){
        return r>=0 && c>=0 && r<R && c<C;
    }
}

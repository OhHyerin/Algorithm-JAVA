package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G3_1826_����ä��� {
    //�׸���

    static int N; //�������� ����
    static Oil[] oils;  //������ ����
    static Oil end;  //��������

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        oils = new Oil[N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            oils[i] = new Oil(l, a);
        }

        st = new StringTokenizer(br.readLine());
        end = new Oil(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));



    }

    public static class Oil{
        int loc;
        int amount;

        public Oil(int loc, int amount) {
            this.loc = loc;
            this.amount = amount;
        }
    }
}

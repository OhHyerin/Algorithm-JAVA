package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G2_11780_�÷��̵�2 {
    //���� ���2
    //�ִܰ��
    //�÷��̵� ����

    static int n, m;
    static List<Pos> list;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

    }


    static class Pos{
        int r;
        int c;
        int cost;

        Pos(int r, int c, int cost){
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }
}

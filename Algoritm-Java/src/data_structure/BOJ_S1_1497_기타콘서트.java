package data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_1497_��Ÿ�ܼ�Ʈ {
    //�κ�����

    static int N, M;
    static String[] yns;
    static int MAX_MUSIC_CNT = Integer.MIN_VALUE;
    static int MIN_GUITAR = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        yns = new String[N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            st.nextToken(); //�̸� ����
            yns[i] = st.nextToken();
        }

        subSetByRecur2(N, 0, new int[M]);
        System.out.println(MAX_MUSIC_CNT==0 ? -1 : MIN_GUITAR);
    }

    public static void subSetByRecur2(int toCheck, int guitar, int[] songs){
        //base part
        if(toCheck==0){
            int musicCnt = 0;
            for(int i=0;i<songs.length;i++){
                if(songs[i]>0){
                    musicCnt++;
                }
            }
            if(musicCnt >= MAX_MUSIC_CNT && MIN_GUITAR > guitar){
                //���� ���� �ִ� ����, ��Ÿ ���� �ּҰ� ����
                MAX_MUSIC_CNT = musicCnt;
                MIN_GUITAR = guitar;
            }
            return;
        }


        //inductive part
        String yn = yns[N-toCheck];
        //���õǸ� guitar���� ����, ���õ� guitar�� ���� ���� ���� �� ��� ����
        for(int i=0;i<yn.length();i++){
            if(yn.charAt(i)=='Y'){
                songs[i]++;
            }
        }

        //��Ÿ�� ���� ���� ��,
        subSetByRecur2(toCheck-1, guitar+1, songs);

        //�ٽ� songsũ�� �ٿ��ְ� ���
        for(int i=0;i<yn.length();i++){
            if(yn.charAt(i)=='Y'){
                songs[i]--;
            }
        }
        //��Ÿ ���� �ȵ��� ��
        subSetByRecur2(toCheck-1, guitar, songs);
    }
}

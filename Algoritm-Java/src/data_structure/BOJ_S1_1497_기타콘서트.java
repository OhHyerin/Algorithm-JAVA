package data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_1497_기타콘서트 {
    //부분집합

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
            st.nextToken(); //이름 버림
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
                //음악 개수 최댓값 갱신, 기타 개수 최소값 갱신
                MAX_MUSIC_CNT = musicCnt;
                MIN_GUITAR = guitar;
            }
            return;
        }


        //inductive part
        String yn = yns[N-toCheck];
        //선택되면 guitar개수 증가, 선택된 guitar로 인한 연주 가능 곡 목록 변경
        for(int i=0;i<yn.length();i++){
            if(yn.charAt(i)=='Y'){
                songs[i]++;
            }
        }

        //기타가 선택 됐을 때,
        subSetByRecur2(toCheck-1, guitar+1, songs);

        //다시 songs크기 줄여주고 재귀
        for(int i=0;i<yn.length();i++){
            if(yn.charAt(i)=='Y'){
                songs[i]--;
            }
        }
        //기타 선택 안됐을 때
        subSetByRecur2(toCheck-1, guitar, songs);
    }
}

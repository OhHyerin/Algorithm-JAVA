package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ_11000_강의실배정 {
    //백준 골드5

    static int n;
    static ArrayList<int[]> lecture;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        lecture = new ArrayList<int[]>();

        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lecture.add(new int[]{start, end});
        }

        lecture.sort(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                //배열끼리 비교니까 기준점을 start로
                if(o1[0]==o2[0]){
                    //만약에 시작 시간이 똑같으면
                    return o1[1]-o2[1];
                }
                return o1[0]-o2[0];
            }
        });



    }
}

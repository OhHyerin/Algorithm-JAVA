package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_S2_2304_창고다각형 {
    //IM대비

    static int n;
    static ArrayList<Top> top;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        top = new ArrayList<>();
        int result = 0;

        int maxHeight = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            top.add(new Top(x, h));
            maxHeight = Math.max(maxHeight, h);
        }

        int firstIdx = -1;
        int lastIdx = -1;
        int firstX = -1;
        int lastX = -1;

        for(int i=0;i<top.size();i++){
            Top cur = top.get(i);
            if(maxHeight==cur.h){
                if(firstIdx==-1){
                    firstIdx = i;
                    firstX = cur.x;
                }
                lastIdx = i;
                lastX = cur.x;
            }
        }

        //가장 높은 탑들 미리 계산
        result += (lastX-firstX+1)*maxHeight;

        //왼쪽에서 maxHeight까지
        Top pre = new Top(0, 0);
        for(int i=0;i<=firstIdx;i++){
            Top cur = top.get(i);
            if(pre.h<cur.h){
                result += pre.h*(cur.x-pre.x);
                pre = cur;
            }
        }

        //오른쪽에서 maxHeight까지
        pre = new Top(0, 0);
        for(int i=top.size()-1;i>=lastIdx;i--){
            Top cur = top.get(i);
            if(pre.h<cur.h){
                result += pre.h*(pre.x-cur.x);
                pre = cur;
            }
        }

        System.out.println(result);


    }

    static class Top implements Comparator<Top> {
        int x;
        int h;

        public Top(int x, int h) {
            this.x = x;
            this.h = h;
        }

        @Override
        public int compare(Top o1, Top o2) {
            return Integer.compare(o1.x, o2.x);
        }
    }
}

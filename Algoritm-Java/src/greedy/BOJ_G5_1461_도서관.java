package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G5_1461_도서관 {
    //정렬, 그리디 알고리즘

    static int N; // 책의 개수
    static int M; // 한 번에 들 수 있는 책의 개수
    static List<Integer> plist;
    static List<Integer> mlist;
    static int maxNum;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        plist = new ArrayList<>();  //양수인 위치 리스트
        mlist = new ArrayList<>();  //음수인 위치 리스트

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            int a = Integer.parseInt(st.nextToken());
            maxNum = Math.max(maxNum, Math.abs(a));
            if(a>=0) plist.add(a);
            else mlist.add(Math.abs(a));  //입력 받으면서
        }

        Collections.sort(plist, Collections.reverseOrder());  //내림차순 정렬
        Collections.sort(mlist, Collections.reverseOrder());

//        System.out.println(plist);
//        System.out.println(mlist);

        int idx = 0;
        outer : while(!plist.isEmpty()){
            answer += plist.get(0);  //리스트의 첫 번째 더하기
//            System.out.println("plist : "+plist.get(0));
            plist.remove(0);  //지우기
            for(int i=0;i<M-1;i++){  //책을 들 수 있는 개수만큼 돌면서
                if(plist.isEmpty()) break outer;  //리스트 비었으면 리턴
                plist.remove(0);  //지움 (더 먼 거리 가면서 가져다 놓을 수 있으니까)
            }
        }

//        System.out.println(answer);
//        System.out.println(plist);

        outer : while(!mlist.isEmpty()){
            answer += mlist.get(0);
//            System.out.println("mlist : "+mlist.get(0));
            mlist.remove(0);
            for(int i=0;i<M-1;i++){
                if(mlist.isEmpty()) break outer;
                mlist.remove(0);
            }
        }

//        System.out.println(answer);
//        System.out.println(mlist);

        answer *= 2;  //왔다갔다 하는 거리이므로 2배 해준 뒤,
        answer -= maxNum; //가장 먼 거리는 한번 빼주기 (마지막 책 가져다놓고 다시 원점으로 안와도 된다고 했으므로)

        System.out.println(answer);

    }

}

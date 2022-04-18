package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_G5_1092_배 {
    // 그리디, 정렬

    static int N;
    static int M;
    static ArrayList<Integer> crane;
    static ArrayList<Integer> box;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        crane = new ArrayList<>();
        box = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            crane.add(Integer.parseInt(st.nextToken()));
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++){
            box.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(crane, Collections.reverseOrder());
        Collections.sort(box, Collections.reverseOrder());

        int answer = 0;

        if(crane.get(0)>=box.get(0)) {
            while (!box.isEmpty()) {
                int idx = 0;
                for(int i=0;i<crane.size();){
                    if(idx==box.size()) break;
                    else if(crane.get(i)>=box.get(idx)){
                        //크레인값보다 박스 값이 작으면 가능
                        box.remove(idx);
                        i++; //다음 크레인값 탐색
                    }
                    else idx++; //크레인값보다 박스값이 크면 다음 박스 탐색
                }
                answer++;  //배 옮기는 시간 증가
            }
        }else{  //첫 번째 값부터 박스값이 크레인값보다 크면 아예 옮길 수 없는 경우이므로
            answer = -1;  //answer = -1;
        }
        System.out.println(answer);
    }
}

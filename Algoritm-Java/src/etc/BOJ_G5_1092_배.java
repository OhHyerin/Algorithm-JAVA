package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_G5_1092_�� {
    // �׸���, ����

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
                        //ũ���ΰ����� �ڽ� ���� ������ ����
                        box.remove(idx);
                        i++; //���� ũ���ΰ� Ž��
                    }
                    else idx++; //ũ���ΰ����� �ڽ����� ũ�� ���� �ڽ� Ž��
                }
                answer++;  //�� �ű�� �ð� ����
            }
        }else{  //ù ��° ������ �ڽ����� ũ���ΰ����� ũ�� �ƿ� �ű� �� ���� ����̹Ƿ�
            answer = -1;  //answer = -1;
        }
        System.out.println(answer);
    }
}

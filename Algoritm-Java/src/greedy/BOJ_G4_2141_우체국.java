package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G4_2141_��ü�� {
    //�׸���, ����

    /*
    �ݷ�
    2
    1 1
    2 2
     */

    static int N;
    static List<Pos> list;
    static long sumPeople;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        list = new ArrayList<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            sumPeople += p;
            list.add(new Pos(l, p));

        }

        Collections.sort(list);  //��ġ������ ����

        long sum = 0;
        int cur = 0;
        for(int i=0;i<list.size();i++){
            sum += list.get(i).people;
            if(sum>=((sumPeople/2)+(sumPeople%2))){  //�α��� �߰������� �Ǿ��� �� ��ġ ���
                //�׳� sumPeople/2���ϸ� �߰������� ã������
                cur = list.get(i).location;  //���⼭ i+1�� �ؼ� Ʋ�Ƚ��ϴ� ����
                break;
            }
        }

        System.out.println(cur);


    }

    private static class Pos implements Comparable<Pos>{
        int location;
        int people;

        public Pos(int location, int people) {
            this.location = location;
            this.people = people;
        }


        @Override
        public int compareTo(Pos o) {
            return (int)(location-o.location);
        }
    }
}

package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G3_2457_���ִ������� {
    //�׸���

    static int N;  //�ɵ��� �� ����
    static List<Flower> flowers;
    static int S = 301;
    static int E = 1201;  //���� ���� �Ϸ� ��

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        flowers = new ArrayList<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int sm = Integer.parseInt(st.nextToken());
            int sd = Integer.parseInt(st.nextToken());
            int em = Integer.parseInt(st.nextToken());
            int ed = Integer.parseInt(st.nextToken());

            flowers.add(new Flower(sm*100+sd, em*100+ed));
        }

        Collections.sort(flowers);

//        System.out.println(flowers);

        int cnt = 0;  //�� �� �� ����
        int startDate = S;  //���� ���� ����
        int max = 0;  //�ִ� ������ ��
        int idx = 0;

        while(startDate<E) {
            max = 0;
            boolean flag = false;  //������ �Ǿ��� Ȯ��

            for(int i=idx; i<N;i++){
                Flower cur = flowers.get(i);

                if(cur.start<=startDate && cur.end>max){  //���� �����ϴ� ���� �� ���� ���� ������ ���̰�, ���� ������ ���� max���� �ʰԶ��
                    max = cur.end;  //max�� ����
                    idx = i+1;  //������ i�������� Ȯ��
                    flag = true;  //���� �Ǿ����� �ǹ�
                }
            }

            if (flag) {  //���� ������
                startDate = max;  //startDate�� ���� ������ ���� �����ϰ�
                cnt++;  //���� ���� ++
            }else break;
        }

//        List<Flower> temp = new ArrayList<>();
//        for(int i=0;i<flowers.size();i++){
//            if(flowers.get(i).end<S) continue;  //���� ��¥�� S���� ������ continue
//            if(flowers.get(i).start>E) continue;
//
//            if(flowers.get(i).start<=endDate && flowers.get(i).end>endDate){
//
//                for(int j=0;j<temp.size();j++){
//                    if(temp.get(j).start<curDate && temp.get(j).end<flowers.get(i).end){
//                        System.out.println(temp.get(j));
//                        temp.remove(j);
//                    }
//                }
//
//                temp.add(new Flower(flowers.get(i).start, flowers.get(i).end));
//                System.out.println("temp����Ʈ : "+temp);
//
//                curDate = Math.max(endDate, flowers.get(i).start);
//                endDate = flowers.get(i).end;
//
//                System.out.println("curDate : "+curDate+"   endDate : "+endDate);
//            }
//        }

//        System.out.println(temp.size());

        if(max<1201){
            System.out.println(0);
        }else{
            System.out.println(cnt);
        }

    }

    private static class Flower implements Comparable<Flower>{
        int start;
        int end;

        public Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Flower o) {
            if(start==o.start){  //�������� ������ �������� �� ������ ����
                return (end-o.end)*-1;
            }
            return start-o.start;  //������ ���� ����
        }

        @Override
        public String toString() {
            return "Flower{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}

package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G2_5214_ȯ�� {
    //�׷��� Ž��, bfs

    /*
    ���� ���� :
    ������Ʃ�� �ϳ��� �� K���� ���� �����Ѵ�.
    1�������� N�������� ���µ� �湮�ϴ� �ּ� ���� ���� �� ���ϱ�?
     */

    static int N;  //���� ��
    static int K;  //�� ������Ʃ�갡 ���� �����ϴ� ���� ����
    static int M;  //������Ʃ���� ����
    static List<Integer>[] list;
    static Tube[] tube;
    static int answer = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new List[N+1];
        tube = new Tube[M];

        for(int i=1;i<=N;i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            //������Ʃ���� ����

            tube[i] = new Tube(i);  //tube ����

            st = new StringTokenizer(br.readLine());
            for(int j=0;j<K;j++){
                int station = Integer.parseInt(st.nextToken());
                tube[i].stations.add(station);  //tubeŬ���� HashSet�� �߰�
                list[station].add(i);  //���� ������Ʃ�� �߰�
            }
        }

        //-----------�Է� �Ϸ�--------------------------

        bfs();

        System.out.println(answer);



    }

    private static void bfs(){
        Queue<Tube> queue = new LinkedList<>();
        int[] visitTube = new int[tube.length];  //tube �湮üũ
        boolean[] visitStation = new boolean[N+1]; //�� �湮 üũ

        //1�� station�� ���Ե� Ʃ����� ť�� ���
        for(int i=0;i<tube.length;i++) {
            if (tube[i].stations.contains(1)) {
                //tube�� ����Ǿ��ִ� �� �� i�� �ִٸ�
                queue.add(tube[i]); //�ش� Ʃ��� ����
                visitTube[i] = 1;  //Ʃ�� �湮ó��
            }
        }
        visitStation[1] = true;  //�� �湮ó��

        while(!queue.isEmpty()){
            Tube cur = queue.poll();

            for(int station : cur.stations){  //���� Ʃ�갡 ����Ǿ��ִ� ��� station�� Ž��
                if(visitStation[station]){  //�̹� �湮�ߴ� ���̸� continue
                    continue;
                }

                visitStation[station] = true;

                if(station == N){
                    //�������� ����Ǿ�������
                    queue.clear();
                    answer = visitTube[cur.idx]+1;  //���� �湮Ƚ�� + 1 ��ȯ
                }

                //�ش� ���� ����� ������Ʃ�� Ȯ���Ͽ� �湮���� �ʾ����� ť�� �ֱ�
                for(int i=0;i<list[station].size();i++){
                    int next = list[station].get(i);
                    if(visitTube[next]==0){
                        queue.add(tube[next]);
                        visitTube[next] = visitTube[cur.idx]+1;
                    }
                }

            }
        }




    }

    private static class Tube{
        int idx;
        HashSet<Integer> stations;

        public Tube(int idx) {
            this.idx = idx;
            this.stations = new HashSet<>();
        }
    }

}

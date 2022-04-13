package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_4013_Ư�����ڼ� {

    static int K;
    static List<Integer> [] gears = new List[5];
    //������ �� ��� ȸ���ߴ��� ���� ����
    static int[] turnMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            sb.append("#").append(t).append(" ");

            K = Integer.parseInt(br.readLine());
            for(int i=1;i<gears.length;i++){
                gears[i] = new LinkedList<>(); //�Ź� �� �������� ����/���� �߻�
                st = new StringTokenizer(br.readLine());
                while(st.hasMoreTokens()){
                    gears[i].add(Integer.parseInt(st.nextToken()));
                }
            }  //��� ���� �Է� �Ϸ�
            for(int k=0;k<K;k++){
                st = new StringTokenizer(br.readLine());
                int gearno = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                turnMap = new int[5];
                turnMap[gearno] = dir;

                //�� �־��� �������� �� �̵����� ��
                effectLeft(gearno, dir);
                effectRight(gearno, dir);

                //turnMap�� �̿��ؼ� ����� �̵��� �ݿ��Ѵ�
                for(int i=1;i<turnMap.length;i++){
                    int turnDir = turnMap[i];
                    //�ð� ����
                    if(turnDir==1) {
                        gears[i].add(0, gears[i].remove(7));
                    }
                    //�ݽð� ����
                    else if(turnDir==-1){
                        gears[i].add(gears[i].remove(0));
                    }
                }

            }
            //�������� ��� ���¿��� ������ ����Ѵ�
            int answer = 0;
            for(int i=1;i<gears.length;i++){
                answer += gears[i].get(0)*Math.pow(2, i-1);
            }
            sb.append(answer).append("\n");

        }//t
        System.out.println(sb);
    }

    private static void effectRight(int gearNo, int dir){
        if(gearNo==4){
            return;
        }
        if(gears[gearNo].get(2)!=gears[gearNo+1].get(6)){
            turnMap[gearNo+1] = dir * -1 ;
            effectRight(gearNo+1, dir*-1);
        }
    }

    private static void effectLeft(int gearNo, int dir){
        if(gearNo==1){
            return;
        }
        if(gears[gearNo].get(6)!=gears[gearNo-1].get(2)){
            turnMap[gearNo-1] = dir * -1 ;
            effectLeft(gearNo-1, dir*-1);
        }
    }
}

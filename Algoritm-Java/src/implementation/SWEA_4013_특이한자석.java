package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_4013_특이한자석 {

    static int K;
    static List<Integer> [] gears = new List[5];
    //각각의 기어가 어떻게 회전했는지 정보 저장
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
                gears[i] = new LinkedList<>(); //매번 비 순차적인 삽입/삭제 발생
                st = new StringTokenizer(br.readLine());
                while(st.hasMoreTokens()){
                    gears[i].add(Integer.parseInt(st.nextToken()));
                }
            }  //기어 정보 입력 완료
            for(int k=0;k<K;k++){
                st = new StringTokenizer(br.readLine());
                int gearno = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                turnMap = new int[5];
                turnMap[gearno] = dir;

                //기어를 주어진 방향으로 쭉 이동시켜 봄
                effectLeft(gearno, dir);
                effectRight(gearno, dir);

                //turnMap을 이용해서 기어의 이동을 반영한다
                for(int i=1;i<turnMap.length;i++){
                    int turnDir = turnMap[i];
                    //시계 방향
                    if(turnDir==1) {
                        gears[i].add(0, gears[i].remove(7));
                    }
                    //반시계 방향
                    else if(turnDir==-1){
                        gears[i].add(gears[i].remove(0));
                    }
                }

            }
            //최종적인 기어 상태에서 점수를 출력한다
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

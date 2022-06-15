package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_G4_2617_����ã�� {
    //�׷���Ž��, dfs, �÷��̵����
    //9�ۿ��� �ڲ� Ʋ��,,

    //�ڽź��� ū�� (N/2+1)�� �̻� �ְų�, �ڽź��� ������ (N/2+1)�� �̻� �ִ� ������ ������ ���ϱ��

    /*
    �ݷ�
    5 4
    2 1
    2 1
    2 1
    2 1

    �� ������ ���� List���� HashSet���� �����ؼ� �ߺ� �����ϴ� �ذ��
     */

   static int N; //������ ����
    static int M; //���￡ �÷��� Ƚ��
    static HashSet<Integer>[] heavy;
    static HashSet<Integer>[] light;
    static int[] cHeavy;  //������ ���ſ� ���� ����
    static int[] cLight;  //������ ������ ���� ����

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        heavy = new HashSet[N+1];
        light = new HashSet[N+1];
        cHeavy = new int[N+1];
        cLight = new int[N+1];

        for(int i=0;i<=N;i++){
            heavy[i] = new HashSet<>();
            light[i] = new HashSet<>();
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

//            value[a][b]++;
            light[a].add(b);
            heavy[b].add(a);
        }

//        for(int i=1;i<=N;i++){
//            for(int j=0;j<light[i].size();j++){
////                if(!light[i].contains(light[light[i].get(j)])){
//                    light[i].addAll(light[light[i].get(j)]);
////                }
//            }
//            for(int j=0;j<heavy[i].size();j++){
////                if(!heavy[i].contains(heavy[heavy[i].get(j)])) {
//                    heavy[i].addAll(heavy[heavy[i].get(j)]);
////                }
//            }
//        }
        for(int i=1;i<=N;i++){
           cHeavy[i] = heavy[i].size();
           HashSet<Integer> temp = new HashSet<>();
           for(Integer hash:heavy[i]){
//               cHeavy[i]+=heavy[hash].size();
               Collections.addAll(temp, hash);
           }
           cHeavy[i] += temp.size();
        }

        for(int i=1;i<=N;i++){
            cLight[i] = light[i].size();
            HashSet<Integer> temp = new HashSet<>();
            for(Integer hash:light[i]){
//               cHeavy[i]+=heavy[hash].size();
                Collections.addAll(temp, hash);
            }
            cLight[i] += temp.size();
        }

//        for(int i=1;i<=N;i++){
//            cLight[i] = light[i].size();
//            for(Integer hash:light[i]){
//                cLight[i]+=light[hash].size();
//            }
//        }

        //���� list�� ���� ��
//        for(int i=1;i<=N;i++){
//            cLight[i] = light[i].size();
//            for(int j=0;j<light[i].size();j++){
//                cLight[i] += light[light[i].get(j)].size();
//            }
//        }

//        int mid = (N+1)/2;
        int mid = (N/2)+1;
        int count = 0;

        for(int i=1;i<=N;i++){
            if(cHeavy[i]>=mid) count++;
            else if(cLight[i]>=mid) count++;
        }


//        int count = 0;
//        int mid = (N/2)+1;
//        for(int i=1;i<=N;i++){
//            if(light[i].size()>=mid){
////                System.out.println("ū��!");
//                count++;
//            }
//            else if(heavy[i].size()>=mid){
////                System.out.println("������!");
//                count++;
//            }
//        }

        System.out.println(count);



    }
}

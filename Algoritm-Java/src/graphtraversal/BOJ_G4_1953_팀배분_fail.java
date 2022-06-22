package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G4_1953_�����_fail {
    //�׷���Ž��

    static int N;  //�л����� ��
    static List<Integer>[] list;
    static List<Integer> blue;
    static List<Integer> white;
    static StringBuilder sb = new StringBuilder();
    static int start;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        list = new List[N+1];
        for(int i=1;i<=N;i++){
            list[i] = new ArrayList<>();
        }


        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            for(int j=0;j<n;j++){
                list[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        subSet(1, new boolean[N+1]);

        System.out.println(sb.toString());

    }

    static boolean isPossible;

    private static void subSet(int cnt, boolean[] selected){
        if(!sb.toString().equals("")){
//            System.out.println("��������");
            return;
        }

        if(cnt==N+1){
//            System.out.println(Arrays.toString(selected));
            blue = new ArrayList<>();
            white = new ArrayList<>();

            for(int i=1;i<=N;i++){
                if(selected[i]) blue.add(i);
                else white.add(i);
            }


            if(blue.size()==0 || white.size()==0) return;  //�ּ� ���� �Ѹ��̻��̾����
//            System.out.println("blue : "+blue);
//            System.out.println("white : "+white);

            isPossible = false;
            for(int i=0;i<blue.size();i++){
                start = blue.get(i);
                dfs(blue.get(i), 0, new boolean[N+1]);
//                System.out.println();
            }
            if(!isPossible) return;
            for(int i=0;i<white.size();i++){
                dfs(white.get(i), 1, new boolean[N+1]);
//                System.out.println();
            }
            if(!isPossible) return;

//            dfs(blue.get(0), 0,0);
//            if(!isPossible) return;
//            dfs(white.get(0), 0,1);
//            if(!isPossible) return;

            //�� �����ϸ�
            Collections.sort(blue);
            Collections.sort(white);

            sb.append(blue.size()).append("\n");
            for(int i=0;i<blue.size();i++){
                sb.append(blue.get(i)+" ");
            }
            sb.append("\n").append(white.size()).append("\n");
            for(int i=0;i<white.size();i++){
                sb.append(white.get(i)+" ");
            }

            return;
        }

        selected[cnt] = true;
        subSet(cnt+1, selected);
        selected[cnt] = false;
        subSet(cnt+1, selected);
    }

    private static void dfs(int cur, int team, boolean[] visited){
        if(visited[cur]) return;

        visited[cur] = true;

//        System.out.println("dfs - cur : "+cur+" team : "+team);
        for(int i=0;i<list[cur].size();i++){
            int nr = list[cur].get(i);
//            System.out.println("nr : "+nr);

            if(team==0){
                //cur�� û��
                if(blue.contains(nr) && !visited[nr]){
                    isPossible = false;  //nr�� û���̸� �Ұ���
//                    System.out.println("team�� û���ε� nr�� û��");
                    return;
                }
            }else if(team==1){
                //cur�� ����
                if(white.contains(nr) && !visited[nr]) {
                    isPossible = false;  //nr�� �����̸� �Ұ���
//                    System.out.println("team�� �����ε� nr�� ����");
                    return;
                }
            }

//            System.out.println("isPossible�� true �� �Ǵ� ��");
            isPossible = true;
            dfs(nr, team, visited);

        }
    }
}

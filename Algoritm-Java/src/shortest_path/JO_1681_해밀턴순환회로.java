package shortest_path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO_1681_�ع��ϼ�ȯȸ�� {
    //���� ����Ʈ
    //�ִܰ��

    static int N; //N:����ؾ��ϴ� ����� ����
    static int[][] adjMatrix;
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        adjMatrix = new int[N][N];
        visited = new boolean[N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited[0] = true;
        delivery(0, 0, 0);
        sb.append(min);
        System.out.println(sb);

    }

    static void delivery(int current, int count, int distance){
        if(distance>min){
            return;
        }
        if(count==N-1){
            //�ٵ�������
            if(adjMatrix[current][0]==0){
                return;
            }
            distance += adjMatrix[current][0];
            min = Math.min(min, distance);
            return;
        }


        for(int i=0;i<N;i++){
            if(visited[i]) continue;  //�湮������ �˻�x
            if(adjMatrix[current][i]==0) continue; //���� ������ �˻� x

            visited[i] = true;
            delivery(i, count+1, distance+adjMatrix[current][i]);
            visited[i] = false;
        }
    }
}

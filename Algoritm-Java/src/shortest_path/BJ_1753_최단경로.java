package shortest_path;

import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_1753_�ִܰ�� {

    static int v, e; //v:������ ����, e:������ ����
    static int k; //k:���� ������ ��ȣ
    static final int INF = Integer.MAX_VALUE;
    static ArrayList<Node>[] list;
    static int[] distance;
    static int start;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(br.readLine());

        list = new ArrayList[v+1]; //���� ������ŭ �ʱ�ȭ
        distance = new int[v+1]; //���� ������ŭ �ʱ�ȭ
        for(int i=1;i<=v;i++){
            list[i] = new ArrayList<>();
        }
        Arrays.fill(distance, INF); //�ִ����� �ʱ�ȭ

        distance[k] = 0;
        //����Ʈ�� �׷��� ������ �ʱ�ȭ
        for(int i=0;i<e;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            //start���� end�� ���� �Ÿ�
            list[start].add(new Node(end, distance));
        }
       dijkstra(k);
        for(int i=1;i<=v;i++){
            //dist�� �ʱⰪ �״�� INF��� "INF"���
            if(distance[i]==INF) {
                System.out.println("INF");
            }
            else System.out.println(distance[i]);
        }



    }

    private static void dijkstra(int index){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        boolean [] check = new boolean[v+1];
        queue.add(new Node(index, 0));
        distance[index]=0;

        while(!queue.isEmpty()){
            //ť�� �� ������
            //��� poll
            Node curNode = queue.poll();
            int cur = curNode.index;

            if(check[cur]==true){
                //�湮�� �� ������
                continue;
            }
            check[cur] = true;

            for(Node node:list[cur]){
                if(distance[node.index]>distance[cur]+node.distance){
                    //���� ��尡 ����Ǿ��ִ� ������ ������
                    distance[node.index] = distance[cur]+node.distance;
                    queue.add(new Node(node.index, distance[node.index]));
                }
            }
        }
    }

static class Node implements Comparable<Node>{
     int index;
     int distance;

    public Node(int index, int distance){
        this.index = index;
        this.distance = distance;
    }
    @Override
    public int compareTo(Node o) {
        return distance-o.distance;
    }
}

}



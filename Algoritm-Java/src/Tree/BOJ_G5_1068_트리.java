package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_1068_트리 {
    //백준 골드5
    //리프노드의 개수 찾기 (부모노드를 지우면 자식노드 모두 remove)

    static int n; //n:노드의 개수
    static ArrayList<Integer>[] tree; //각 parent로부터 연결된 자식노드
//    static int[] leaf;  //leaf 노드 저장할 배열
    //leaf노드를 따로 저장할 필요는 없음
    static int r; //r:지울 노드의 번호
    static int root; //root노드 번호
    static int count; //answer

    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n];  //노드 개수만큼 선언
//        leaf = new int[n];

        //tree setting
        for(int i=0;i<n;i++) {
            tree[i] = new ArrayList<>();  //노드마다 list선언
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            int parent = Integer.parseInt(st.nextToken());
            if(parent==-1){  //입력값이 -1이면 루트노드
                root = i; //root변수에 인덱스 저장
            }
            else{
                tree[parent].add(i); //각 노드의 자식들 저장
            }
        }
        r = Integer.parseInt(br.readLine());
        //---------------------입력 완료-------------------
        remove(r);  //전체 조회 후 r 노드부터 leaf노드까지 삭제

        //루트노드가 삭제할 노드일 때 1이 아닌 0이 나와야 함
        if(r==root){
            System.out.println(0);
        } else{
            countLeaf();
            System.out.println(count);
        }
//        countLeaf();
//        System.out.println(count);

    }
    static void remove(int node){
        //tree 조회
        if(tree[node].size()>0){
            //리프노드가 아니면
            int size = tree[node].size();
            while(size>0){
                //leaf노드까지 반복
                remove(tree[node].get(--size)); //size를 감소하면서 leaf노드 찾기
            }
        }

        //tree에서 삭제
        for(int i=0;i<n;i++) {
            if (tree[i].contains(node)) {
                //리스트 안에 node가 포함되어있으면
                for (int j = 0; j < tree[i].size(); j++) {
                    //leaf노드까지 반복
                    if (tree[i].get(j) == node) {
                        tree[i].remove(j); //노드 삭제
                    }
                }
            }
        }
    }

    static void countLeaf(){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);  //queue에 루트 노드 추가

        while(!queue.isEmpty()){
            //큐가 빌 때 까지 bfs
            int cur = queue.poll();
            if(tree[cur].size()==0){
                //리프노드면
                count++;
            } else{
                for(int next:tree[cur]){
                    queue.add(next);
                }
            }
        }

    }


}

package minimum_spanning_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D4_7465_창용마을무리의개수 {

    static int n, m;
    static int[] arr;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            sb.append("#").append(t).append(" ");
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            arr = new int[n+1];
            check = new boolean[n+1];

            makeSet();

            for(int i=0;i<m;i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }

            int result = 0;
            for(int i=1;i<=n;i++){
                if(!check[i]) result++;
            }
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static void makeSet(){
        for(int i=1;i<=n;i++){
            arr[i] = i;
        }
    }

    private static int find(int a){
        if(arr[a]==a) return a;
        return find(arr[a]);
    }

    private static void union(int a, int b){
        int rA = find(a);
        int rB = find(b);

        if(rA==rB) return;

        arr[rB] = rA;
        check[rA] = false;
        check[rB] = true;
        return;

    }


}

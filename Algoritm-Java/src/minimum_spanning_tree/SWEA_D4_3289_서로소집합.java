package minimum_spanning_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D4_3289_서로소집합 {
    //Union Find
    //operator가 0이면 합집합, 1이면 두 원소 같은 집합에 있는지 확인

    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            arr = new int[n+1];

            for(int i=1;i<=n;i++) {
                arr[i] = i;
            }

            sb.append("#").append(t).append(" ");
            for(int i=0;i<m;i++) {
                st = new StringTokenizer(br.readLine());
                int oper = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if(oper==0) {
                    union(a, b);
                }
                else {
                    if(findSet(a)==findSet(b)) sb.append(1);
                    else sb.append(0);
                }

            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int findSet(int a){
        if(a==arr[a]) return a;
        return findSet(arr[a]);
    }

    static boolean union(int a, int b){
        int rA = findSet(a);
        int rB = findSet(b);

        if(rA==rB) return false;
        else {
            arr[rB] = arr[rA];
            return true;
        }
    }
}

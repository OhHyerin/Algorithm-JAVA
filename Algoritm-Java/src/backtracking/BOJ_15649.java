package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_15649 {
    //백준
    //N과 M(1)(실버3)

    static int n,m;
    static int[] seq;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line[] = br.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);

       seq = new int[m];
       visited = new boolean[n];

       dfs(0);
    }

    public static void dfs(int depth){
        if(depth==m) {
            for (int val : seq) {
                System.out.print(val + " ");
            }
            System.out.println();
            return;
        }
        for(int i=0;i<n;i++){
            if(!visited[i]){
                visited[i] = true;
                seq[depth] = i+1;
                dfs(depth+1);
                visited[i] = false;
            }

        }
    }
}

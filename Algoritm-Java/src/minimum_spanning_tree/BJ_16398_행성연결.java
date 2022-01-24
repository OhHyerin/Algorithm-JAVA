package minimum_spanning_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_16398_青己楷搬 {

    static class Node implements Comparable<Node>{
       private int start;
       private int end;
       private int distance;

        @Override
        public int compareTo(Node o) {
            return distance-o.distance;
        }
    }

    static int n; //n:青己狼 荐
    static ArrayList<Node> planet;
    static int[] firstPlanet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
    }

}

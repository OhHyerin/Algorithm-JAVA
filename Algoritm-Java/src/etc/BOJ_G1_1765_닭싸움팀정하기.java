package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_G1_1765_´ß½Î¿òÆÀÁ¤ÇÏ±â {
    //¹®Á¦Áý - ÄÚÅ×±¤Å»

    static int N; //ÇÐ»ý ¼ö
    static int M;
    static ArrayList<Node>[] students;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        students = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            students[i] = new ArrayList<>();
        }


        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            String rel = st.nextToken();
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            students[a].add(new Node(b, rel));
            students[b].add(new Node(a, rel));
        }

        for(int i=1;i<=N;i++){
            System.out.println(students[i]);
        }
//        friendInEnemy();
        System.out.println("-----------");
        for(int i=1;i<=N;i++){
            System.out.println(students[i]);
        }

    }

    static void friendInEnemy(Node cur, Node E){


    }

    static class Node{
        int who;
        String relation;

        public Node(int who, String relation) {
            this.who = who;
            this.relation = relation;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "who=" + who +
                    ", relation='" + relation + '\'' +
                    '}';
        }
    }
}

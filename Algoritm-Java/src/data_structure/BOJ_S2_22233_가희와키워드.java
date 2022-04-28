package data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_S2_22233_�����Ű���� {
    //HashSet, ���ڿ�

    static int N; //�޸��忡 ���� Ű���� ����
    static int M; //��α׿� �� ���� ����
    static HashSet<String> memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        memo = new HashSet<>();

        for(int i=0;i<N;i++){
            String keyword = br.readLine();

            memo.add(keyword);
        }

        for(int i=0;i<M;i++){
            String[] input = br.readLine().split(",");
//            System.out.println(Arrays.toString(input));

            for(int j=0;j<input.length;j++){
                if(memo.contains(input[j])){
                    memo.remove(input[j]);
                }
            }
            sb.append(memo.size()).append("\n");
        }

        System.out.println(sb);

    }
}

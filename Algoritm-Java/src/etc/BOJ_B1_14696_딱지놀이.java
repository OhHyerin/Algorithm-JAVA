package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_B1_14696_딱지놀이 {
    //백준 브론즈1
    // IM대비

    static int T;
    static StringBuilder sb = new StringBuilder();
    static int[] a;
    static int[] b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            a = new int[5];
            for(int i=0;i<n;i++){
                a[Integer.parseInt(st.nextToken())] ++;
            }
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            b = new int[5];
            for(int i=0;i<n;i++){
                b[Integer.parseInt(st.nextToken())]++;
            }

//            System.out.println(Arrays.toString(a));
//            System.out.println(Arrays.toString(b));

            boolean isSame = true;
            for(int i=4;i>0;i--){
                if(a[i]>b[i]){
                    sb.append("A").append("\n");
                    isSame = false;
                    break;
                } else if(a[i]<b[i]){
                    sb.append("B").append("\n");
                    isSame = false;
                    break;
                }
            }
            if(isSame) sb.append("D").append("\n");

        }//t

        System.out.println(sb);



    }
}

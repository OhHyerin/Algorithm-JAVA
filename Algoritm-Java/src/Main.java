import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int count = 0;
        int copy = n;

        do{
            n = ((n%10*10)+(((n/10)+(n%10)))%10);
            count++;
        }while(copy != n);

        System.out.println(count);


//        String str;
//        while((str=br.readLine())!=null){
////            if(br.readLine()==null) break;
//            st = new StringTokenizer(str, " ");
//            int a = Integer.parseInt(st.nextToken());
//            int b = Integer.parseInt(st.nextToken());
//            sb.append(a+b).append("\n");
////            if(!br.ready()) break;
//        }
//        System.out.println(sb);
    }
}

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        String [] participant = {};
        String [] completion = {};

        String answer = "";

        answer = "12345";
        System.out.println(answer.length());

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        System.out.println(list.size());

        HashSet<String> hashSet = new HashSet<>();

        for(int i=0;i<completion.length;i++){
            hashSet.add(completion[i]);
        }

        for(int i=0;i<participant.length;i++){
            if(!hashSet.contains(participant[i])){
                answer = participant[i];
                break;
            }
        }


        System.out.println(answer);

    }
}

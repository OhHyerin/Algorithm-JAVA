import java.io.IOException;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {

        String [] participant = {};
        String [] completion = {};

        String answer = "";


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

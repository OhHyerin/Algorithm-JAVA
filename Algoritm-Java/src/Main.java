import java.io.IOException;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {

        Random rand = new Random();
        for(int i=0;i<7;i++){
            System.out.println(rand.nextInt(1000)+1);
        }
    }
}

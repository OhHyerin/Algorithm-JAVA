import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Integer> list = new ArrayList<>();
        Date today = new Date();
        SimpleDateFormat MONTH = new SimpleDateFormat("MM");
        SimpleDateFormat YEAR = new SimpleDateFormat("yyyy");
        int month = Integer.parseInt(MONTH.format(today));
        int year = Integer.parseInt(YEAR.format(today));

        for(int i=1;i<=12;i++){
            if(i==2){
                if(year%4==0 && year%100 !=0 || year%400==0){
                    list.add(29);
                }else{
                    list.add(28);
                }
            }else if(i<=7 && i%2==1){
                //1, 3, 5, 7¿ù
                list.add(31);
            }else if(i<=7){
                //4, 6¿ù
                list.add(30);
            }else if(i%2==0){
                //8, 10, 12¿ù
                list.add(31);
            }else{
                list.add(30);
            }
        }

        System.out.println(list.get(month-1)+" days for "+year+"-"+month);

    }
}

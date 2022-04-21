import java.util.Scanner;

public class ExCal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("더할 두 수를 입력한다 >>");
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        Cal cal = new Cal(a, b);
        int sum = cal.plus();

        System.out.printf("작성자 이름 : %s 작성자, 학번 : %d 두 수의 합은 : %d", "오혜성", 2100104, sum);
    }
}

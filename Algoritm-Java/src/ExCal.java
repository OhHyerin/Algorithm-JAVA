import java.util.Scanner;

public class ExCal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("���� �� ���� �Է��Ѵ� >>");
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        Cal cal = new Cal(a, b);
        int sum = cal.plus();

        System.out.printf("�ۼ��� �̸� : %s �ۼ���, �й� : %d �� ���� ���� : %d", "������", 2100104, sum);
    }
}

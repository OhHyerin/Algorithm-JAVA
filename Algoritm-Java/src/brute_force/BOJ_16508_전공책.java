package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ_16508_����å {
    //���� �ǹ�3
    /*
1. ���ڵ��� �������� ���ڿ� T�� ������ �ϹǷ�, T�� ���ĺ����� ������ �����Ѵ�.
2. ������ �̿��� 0��å�� �����ϴ� ���, 0�� å, 1�� å�� �����ϴ� ���, ��� �����ϴ� ��� ���� ��� ��츦 Ž���Ѵ�.
3. å�� �����ϴ� ����, �ش� å�� ���ĺ����� selectedChar[26]�� å�� �ִ� ���ĺ����� ������ �����ϴ� ������ ǥ���Ѵ�.
4. ������ å���� ���ĺ� ������ T�� ���ĺ� ������ ���Ͽ�, ������ å��� T�� ���� �� �ִٸ� result�� ������ �ش�.
�̶�, result�� �� ���� ���� �־��ش�.
     */

    static char[] word;
    static int N;
    static ArrayList<Book> books;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String temp = br.readLine();
        word = temp.toCharArray();
        N = Integer.parseInt(br.readLine());
        books = new ArrayList<>();

        for(int i=0;i<N;i++){
            String[] str = br.readLine().split(" ");
            books.add(new Book(Integer.parseInt(str[0]), str[1], 0));
        }

    }

    static class Book{
        int price;
        String title;
        int count;
        Book(int price, String title, int count){
            this.price = price;
            this.title = title;
            this.count = count;
        }
    }
}

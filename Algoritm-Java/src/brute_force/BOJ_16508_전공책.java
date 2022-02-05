package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ_16508_전공책 {
    //백준 실버3
    /*
1. 글자들의 조합으로 문자열 T를 만들어야 하므로, T의 알파벳들의 개수를 저장한다.
2. 조합을 이용해 0번책만 선택하는 경우, 0번 책, 1번 책을 선택하는 경우, 모두 선택하는 경우 등의 모든 경우를 탐색한다.
3. 책을 선택하는 것은, 해당 책의 알파벳들을 selectedChar[26]에 책에 있는 알파벳들의 개수를 저장하는 것으로 표현한다.
4. 선택한 책들의 알파벳 개수와 T의 알파벳 개수를 비교하여, 선택한 책들로 T를 만들 수 있다면 result를 갱신해 준다.
이때, result는 더 작은 값만 넣어준다.
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

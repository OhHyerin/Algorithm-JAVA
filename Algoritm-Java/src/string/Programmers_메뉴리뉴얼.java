package string;

public class Programmers_메뉴리뉴얼 {

    public static void main(String[] args) {
        String[] order = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course={2, 3, 4};
        String[] result = solution(order, course);

        for(int i=0;i<result.length;i++){
            System.out.println(result[i]);
        }
    }

    public static String[] solution(String[] orders, int[] course){
        String[] answer = {};

        int[][] map = new int[26][26];

        for(int i=0;i<orders.length;i++){
            String str = orders[i];
            for(int j=0;j<str.length();j++){
                char ch1= str.charAt(j);
                for(int c=j+1;c<str.length();c++){
                    char ch2 = str.charAt(c);
                    map[ch1-'A'][ch2-'A']++;
                    map[ch2-'A'][ch1-'A']++;
                }
            }
        }

        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }




        return answer;
    }


}

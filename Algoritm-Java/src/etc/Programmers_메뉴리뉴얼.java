package etc;

public class Programmers_메뉴리뉴얼 {

    public static void main(String[] args) {
        String[] order = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course={2, 3, 4};
        String[] result = solution(order, course);

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(result[0]);
        for(int i=1;i<result.length;i++){
            sb.append("\",");
            sb.append(result[i]).append("\"");
        }
        System.out.println(sb);
    }

    public static String[] solution(String[] orders, int[] course){
        String[] answer = {};





        return answer;
    }


}

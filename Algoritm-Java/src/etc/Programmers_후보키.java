package etc;

import java.util.*;

public class Programmers_후보키 {
    //완탐
    //유일성 -> 최소성

    public static void main(String[] args) {
        String[][] relation = {
                {"100", "ryan", "music", "2"},
                {"200", "apeach", "math", "2"},
                {"300", "tube", "computer", "3"},
                {"400", "con", "computer", "4"},
                {"500", "muzi", "music", "3"},
                {"600", "apeach", "music", "2"}
        };

        System.out.println(solution(relation));

    }

    static public int solution(String[][] relation){
        int answer = 0 ;

        int colSize = relation[0].length;
        int rowSize = relation.length;


//        subSet(colSize, 0, new boolean[colSize]);

        Set<String> firstSet = new HashSet<>();
        List<Integer> noAtom = new ArrayList<>();
        outer : for(int i=0;i<colSize;i++){
            int colNum = i;
            firstSet = new HashSet<>();
            for(int j=0;j<rowSize;j++){
                if(firstSet.contains(relation[j][colNum])){
                    noAtom.add(colNum);
                    continue outer;
                }else{
                    firstSet.add(relation[j][colNum]);
                }
            }
            answer++;
        }


        return answer;
    }

    static void subSet(int colSize, int cnt, boolean[] isSelected){
        if(cnt==colSize){
            System.out.println(Arrays.toString(isSelected));
            return;
        }

        isSelected[cnt] = true;
        subSet(colSize, cnt+1, isSelected);
        isSelected[cnt] = false;
        subSet(colSize, cnt+1, isSelected);

    }
}

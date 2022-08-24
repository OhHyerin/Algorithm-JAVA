package etc;

import java.util.*;

public class Programmers_후보키 {
    //완탐, 비트마스킹
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

        int rowSize = relation.length;
        int colSize = relation[0].length;

        List<Integer> list = new ArrayList<>();  //비트 담을 list

        //컬럼 개수가 테케와같이 4라면
        //i는 처음엔 0001
        //i가 1111이 될 때 까지 반복
        for(int i=1;i< 1<<colSize ; i++){
            Set<String> hashSet = new HashSet<>();

            //배열 전체 탐색
            for(int r=0;r<rowSize;r++){
                String tmp = "";

                //배열의 col 탐색
                for(int c=0;c<colSize;c++){
                    if((i&1 << c) > 0){  //현재 탐색하고있는 컬럼이 i에 포함되면
                        tmp += relation[r][c];  //컬럼 그냥 다 이어붙임
                        //ex. 현재 선택된 컬럼이 0,1열일 때
                        //100ryan  <- 이로케
                    }
                }

                hashSet.add(tmp);  //붙인 컬럼을 hashSet에 넣어서 중복 제거

            }

            //hashSet의 크기가 전체 row의 크기와 같으면 유일성 만족
            //check통해서 최소성도 만족하면
            if(hashSet.size()==rowSize && check(i, list)){
                list.add(i);  //해당 컬럼 조합을 list에 추가
            }
        }

        answer = list.size();

        return answer;
    }

    static boolean check(int i, List<Integer> list){
        for(int j:list){
            if((i&j)==j) return false;  //i에 j가 모두 포함되어있으면 j는 i의 부분집합
            //따라서 최소성 만족 못함
        }
        return true;  //부분집합 아니라면 최소성 만족
    }
}

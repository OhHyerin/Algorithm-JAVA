package etc;

import java.util.*;

public class Programmers_뉴스클러스터링 {
    //2018 카카오 블라인드 채용

    /*
    J(A, B) : 두 집합의 교집합 크기를 두 집합의 합집합 크기로 나눈 값
    A와 B가 둘 다 공집합일 경우 나눗셈이 정의되지 않으니 J(A, B) = 1로 정의한다.

     */
    public static void main(String[] args) {
        System.out.println(solution("FRANCE", "french"));
        System.out.println(solution("handshake", "shake hands"));
        System.out.println(solution("aa1+aa2", "AAAA12"));
        System.out.println(solution("E=M*C^2", "e=m*c^2"));
        System.out.println(solution("A+C", "DEF"));
    }

    static public int solution(String str1, String str2){
        int answer = 0;
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();

        HashMap<String, Integer> hashMap1 = new HashMap<>();
        HashMap<String, Integer> hashMap2 = new HashMap<>();

        for(int i=0;i<str1.length()-1;i++){
            if(str1.charAt(i) < 'A' || str1.charAt(i) >'Z') continue;
            if(str1.charAt(i+1) < 'A' || str1.charAt(i+1) >'Z') continue;
            String tmp = str1.charAt(i)+""+str1.charAt(i+1);

            if(hashMap1.containsKey(tmp)){
                hashMap1.put(tmp, hashMap1.get(tmp)+1);
            }else{
                hashMap1.put(tmp, 1);
            }
        }

        for(int i=0;i<str2.length()-1;i++){
            if(str2.charAt(i) < 'A' || str2.charAt(i) > 'Z') continue;
            if(str2.charAt(i+1) < 'A' || str2.charAt(i+1) > 'Z') continue;
            String tmp = str2.charAt(i)+""+str2.charAt(i+1);

            if(hashMap2.containsKey(tmp)){
                hashMap2.put(tmp, hashMap2.get(tmp)+1);
            }else{
                hashMap2.put(tmp, 1);
            }
        }

//        Set<String> keySet = hashMap1.keySet();
//        for(String key : keySet){
//            System.out.println(key+" : "+hashMap1.get(key));
//        }

        int min = 0;
        int max = 0;

        Iterator<Map.Entry<String, Integer>> it = hashMap1.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Integer> entrySet = (Map.Entry<String, Integer>) it.next();
            String key = entrySet.getKey();
            int value = entrySet.getValue();

            if(hashMap2.containsKey(key)){
                min += Math.min(hashMap2.get(key), value);
            }
            max += Math.max(value, hashMap2.get(key));  //max(3, 5)
        }

        it = hashMap2.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Integer> entrySet = (Map.Entry<String, Integer>) it.next();
            max += entrySet.getValue();
        }

        max -= min;

//        System.out.println();
//        System.out.println(min);
//        System.out.println(max);

        if(min==0 && max==0) return 65536;
        if(min==0 && max!=0) min = 0;  //<- 사실 필요 X



        double result = (double)min/(double)max;
        answer = (int)Math.floor(result*65536);



        return answer;
    }


}

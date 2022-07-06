package data_structure;

import java.util.HashMap;

public class Programmers_캐시 {
    //2018 카카오 BLIND RECRUITMENT
    //자료구조 (HashMap의 key와 value를 사용한 문제)

    /*
    LRU 알고리즘?
    - Least Recently Used
    - 가장 오랫동안 사용하지 않은 페이지를 교체
     */

    public static void main(String[] args) {
        int cacheSize = 3;
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
//        String[] cities = {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};
//        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
//        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
//        String[] cities = {"Jeju", "Pangyo", "NewYork", "newyork"};
//        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};

        System.out.println(solution(cacheSize, cities));
    }

    static public int solution(int cacheSize, String[] cities){
        int answer = 0;

        int time = 0;

        HashMap<String, Integer> cache = new HashMap<>();  //도시, 검색한 이후로 지난 시간


        for(int i=0;i<cities.length;i++){
            String city = cities[i].toUpperCase(); //대소문자 구분 안하므로 모두 대문자로 변경

            //17번 테케
            if(cacheSize==0){  //캐시사이즈가 이미 0이면
                time += 5;  //cache miss이므로 실행시간 5를 더하고
                continue;  //continue;
            }

            if(cache.containsKey(city)){  //city가 포함되어있으면 hit
                time += 1; //cache hit일 경우 실행시간은 1이다.
                cache.put(city, 0);  //시간을 0으로 초기화해준 후 넣어줌 (어짜피 중복 제거 되니까!)
            }else{
                time += 5; //cache miss일 경우 실행시간은 5이다.
                if(cache.size() == cacheSize){  //cacheSize만큼 cache에 모두 들어있으면
                    //가장 오래된 검색어 지우고 새로 넣어줌
                    int old = 0;
                    String oldKey = "";
                    for(String key : cache.keySet()){
                        if(old<cache.get(key)){
                            old = cache.get(key);  //old 갱신
                            oldKey = key;
                        }
                    }

                    cache.remove(oldKey);
                }
                cache.put(city, 0);
            }

            //value값 1씩 증가
            for(String key : cache.keySet()){
                cache.put(key, cache.get(key)+1);
            }

        }

        answer = time;

        return answer;
    }
}

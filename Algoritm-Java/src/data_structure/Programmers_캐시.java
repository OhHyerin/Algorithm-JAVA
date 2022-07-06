package data_structure;

import java.util.HashMap;

public class Programmers_ĳ�� {
    //2018 īī�� BLIND RECRUITMENT
    //�ڷᱸ�� (HashMap�� key�� value�� ����� ����)

    /*
    LRU �˰���?
    - Least Recently Used
    - ���� �������� ������� ���� �������� ��ü
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

        HashMap<String, Integer> cache = new HashMap<>();  //����, �˻��� ���ķ� ���� �ð�


        for(int i=0;i<cities.length;i++){
            String city = cities[i].toUpperCase(); //��ҹ��� ���� ���ϹǷ� ��� �빮�ڷ� ����

            //17�� ����
            if(cacheSize==0){  //ĳ�û���� �̹� 0�̸�
                time += 5;  //cache miss�̹Ƿ� ����ð� 5�� ���ϰ�
                continue;  //continue;
            }

            if(cache.containsKey(city)){  //city�� ���ԵǾ������� hit
                time += 1; //cache hit�� ��� ����ð��� 1�̴�.
                cache.put(city, 0);  //�ð��� 0���� �ʱ�ȭ���� �� �־��� (��¥�� �ߺ� ���� �Ǵϱ�!)
            }else{
                time += 5; //cache miss�� ��� ����ð��� 5�̴�.
                if(cache.size() == cacheSize){  //cacheSize��ŭ cache�� ��� ���������
                    //���� ������ �˻��� ����� ���� �־���
                    int old = 0;
                    String oldKey = "";
                    for(String key : cache.keySet()){
                        if(old<cache.get(key)){
                            old = cache.get(key);  //old ����
                            oldKey = key;
                        }
                    }

                    cache.remove(oldKey);
                }
                cache.put(city, 0);
            }

            //value�� 1�� ����
            for(String key : cache.keySet()){
                cache.put(key, cache.get(key)+1);
            }

        }

        answer = time;

        return answer;
    }
}

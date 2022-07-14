package etc;

import java.util.HashMap;

public class Programmers_오픈채팅방 {
    //

    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};

        String[] answer = solution(record);
        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);
        }
    }

    static public String[] solution(String[] record) {
        String[] answer = {};
        HashMap<String, String> hashMap = new HashMap<>();

        int indexSize = 0;

        for (int i = 0; i < record.length; i++) {
            String st = record[i];
            String[] temp = st.split(" ");
            String inout = temp[0];
            String uid = temp[1];
            String name = "";
            if (!temp[0].equals("Leave")) {
                name = temp[2];
            }

            if (inout.equals("Enter")) {
                hashMap.put(uid, name);
                indexSize++;
            } else if (inout.equals("Leave")) {
                indexSize++;
                continue;
            } else if (inout.equals("Change")) {
                hashMap.put(uid, name);  //uid중복으로 기존것 제거됨
            }
        }

        answer = new String[indexSize];
        int idx = 0;
        for (int i = 0; i < record.length; i++) {
            String st = record[i];
            String[] temp = st.split(" ");
            if(temp[0].equals("Enter")){
                StringBuilder sb = new StringBuilder();
                sb.append(hashMap.get(temp[1])).append("님이 들어왔습니다.");
                answer[idx] = sb.toString();
                idx++;
            }else if(temp[0].equals("Leave")){
                StringBuilder sb = new StringBuilder();
                sb.append(hashMap.get(temp[1])).append("님이 나갔습니다.");
                answer[idx] = sb.toString();
                idx++;
            }
        }


        return answer;
    }
}

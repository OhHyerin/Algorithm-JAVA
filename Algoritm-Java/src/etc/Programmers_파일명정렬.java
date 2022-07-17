package etc;

import java.util.PriorityQueue;
import java.util.Queue;

public class Programmers_파일명정렬 {
    //2018 카카오 BLIND RECRUITMENT

    public static void main(String[] args) {
        String[] input = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};

        System.out.println(solution(input));
    }

    static public String[] solution(String[] files){
        String[] answer = new String[files.length];

        Queue<File> queue = new PriorityQueue<>();
        for(int i=0;i<files.length;i++){
            queue.add(new File(files[i]));
        }

        int idx = 0;
        while(!queue.isEmpty()){
            File cur = queue.poll();
            StringBuilder sb = new StringBuilder();
            sb.append(cur.head).append(cur.number).append(cur.tail);
            answer[idx] = sb.toString();
            idx++;
        }

        return answer;
    }

    static class File implements Comparable<File>{
        String head = "";
        String number = "";
        String tail ="";

        public File(String tmp) {
            int startIdx = 0;
            int endIdx = 0;
            boolean isNum = false;

            for(int i=0;i<tmp.length();i++){
                char temp = tmp.charAt(i);

                if(!isNum && temp>='0' && temp<='9'){
                    startIdx = i;
                    isNum = true;
                }
                if(isNum && (temp<'0' || temp>'9')){
                    endIdx = i;
                }
            }

            this.head = tmp.substring(0, startIdx).toUpperCase();
            this.number = tmp.substring(startIdx, endIdx);
            this.tail = tmp.substring(endIdx, tmp.length());

        }

        @Override
        public int compareTo(File o) {
            int n = Integer.parseInt(this.number);
            int m = Integer.parseInt(o.number);

            if(n==m){
                return tail.compareTo(o.tail);
            }else if(head.equals(o.head)){
                return n-m;
            }else {
                return head.compareTo(o.head);
            }
        }
    }
}

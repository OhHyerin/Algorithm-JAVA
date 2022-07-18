package etc;

import java.util.PriorityQueue;
import java.util.Queue;

public class Programmers_파일명정렬 {
    //2018 카카오 BLIND RECRUITMENT
    //test 4부터 런타임에러

    public static void main(String[] args) {
        String[] input = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};

        String[] answer = solution(input);
        for(int i=0;i<answer.length;i++){
            System.out.println(answer[i]);
        }
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
            answer[idx] = cur.result;
            idx++;
        }

        return answer;
    }

    static class File implements Comparable<File>{
        String head = "";
        String number = "";
        String tail ="";
        String result = "";

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
                    break;
                }
            }

            this.head = tmp.substring(0, startIdx).toUpperCase();
            this.number = tmp.substring(startIdx, endIdx);
            this.tail = tmp.substring(endIdx, tmp.length());
//            System.out.println("number : "+this.number);

            this.result = tmp;
        }

        @Override
        public int compareTo(File o) {
            if(this.head.compareTo(o.head)==0){
                return Integer.parseInt(this.number)-Integer.parseInt(o.number);
            }
            return this.head.compareTo(o.head);
//            if(n==m){
//                return tail.compareTo(o.tail);
//            }else if(head.equals(o.head)){
//                return n-m;
//            }else {
//                return head.compareTo(o.head);
//            }
        }
    }
}

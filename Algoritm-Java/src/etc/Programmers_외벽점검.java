package etc;

import java.util.Arrays;

public class Programmers_�ܺ����� {
    //2020 īī�� ����ε� ä��
    //��Ž

    public static void main(String[] args) {

        int[] weak = {1, 5, 6, 10};
        int[] dist = {1, 2, 3, 4};

        System.out.println(solution(12, weak, dist));

    }

    static public int solution(int n, int[] weak, int[] dist){
        int answer = 0;

        int[][] newWeak = new int[weak.length][weak.length];

        //��� weak�迭 ����
        int idx = 0;
        for(int i=0;i<weak.length;i++){
            for(int j=0;j<weak.length;j++){
                idx = i+j;
                if(idx>=weak.length) idx-=weak.length;
                newWeak[i][j] = weak[idx];
                idx++;
            }
        }

        for(int i=0;i<weak.length;i++){
            for(int j=0;j<weak.length;j++){
                System.out.print(newWeak[i][j]+" ");
            }
            System.out.println();
        }

        for(int i=0;i<weak.length;i++){
            permutation(dist, 0, new boolean[dist.length], new int[dist.length], newWeak[i]);
        }




        return answer;
    }

    static private void permutation(int[] dist, int cnt, boolean[] isSelected, int[] selected, int[] weak){
        if(cnt==dist.length){
            System.out.println(Arrays.toString(selected));
            return;
        }
        for(int i=0;i<dist.length;i++){
            if(isSelected[i]) continue;

            isSelected[i] = true;
            selected[cnt] = dist[i];
            permutation(dist, cnt+1, isSelected, selected, weak);
            isSelected[i] =false;
        }

    }
}

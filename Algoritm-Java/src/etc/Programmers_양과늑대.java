package etc;

import java.util.ArrayList;

public class Programmers_������� {




    private static class Node{
        int index;
        int sheep;
        int wolf;
        ArrayList<Integer> list;

        public Node(int index, int sheep, int wolf, ArrayList<Integer> list) {
            this.index = index;
            this.sheep = sheep;
            this.wolf = wolf;
            this.list = list;
        }
    }
}

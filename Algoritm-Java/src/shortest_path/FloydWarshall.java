package shortest_path;

import java.util.Scanner;

public class FloydWarshall {
    static final int INF = 9999999;
    static int N, adjMatrix[][];

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        adjMatrix = new int[N][N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                adjMatrix[i][j] = sc.nextInt();
                if (i != j && adjMatrix[i][j] == 0) {//�ڱ��ڽ������� ���� ������ �ƴϰ� ���������� �ʴٸ� INF�� ä���
                    adjMatrix[i][j] = INF;
                }
            }
        }
        System.out.println("===========�Է�==========");
        print();
        // �����-->������-->�������� 3�� ���� ������ ����
        // ������-->�����-->�������� 3�� ���� ������ ����
        for (int k = 0; k < N; ++k) {
            for (int i = 0; i < N; ++i) {
                if (i == k) continue; // ������� �������� ���ٸ� ���� �����
                for (int j = 0; j < N; ++j) {
                    if (i == j || k == j) continue; // �������� �������� ���ų� ������� �� ��������� �н�
                    if (adjMatrix[i][j] > adjMatrix[i][k] + adjMatrix[k][j]) {
                        adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
                    }
                }
            }
            print();
        }

    }

    private static void print() {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                System.out.print(adjMatrix[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("=====================================");

    }

}

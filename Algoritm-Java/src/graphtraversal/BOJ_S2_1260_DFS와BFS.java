package graphtraversal;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_S2_1260_DFS��BFS {
	//����
	//DFS�� BFS
	
	static int n, m;
	static int[][] edge;
	static boolean[] visited;
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		n = in.nextInt(); //������ ����
		m = in.nextInt(); //������ ����
		int v = in.nextInt(); //Ž���� ������ ������ ��ȣ
		
		edge = new int[n+1][n+1]; //����� ���� 2�����迭
		visited = new boolean[n+1];
		Arrays.fill(visited, false);
		
		for(int i=0;i<m;i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			
			edge[x][y] = 1;
			edge[y][x] = 1; //�ֹ��� ������� ����
		}
		
		dfs(v); //v���� ���̿켱Ž��
		System.out.println();
		
		Arrays.fill(visited, false); //�湮�迭 �ʱ�ȭ
		bfs(v); //v���� �ʺ�켱Ž��
	}
	
	//����������� ������ DFS
	static void dfs(int v) {
		visited[v] = true; //v�� �湮ó��
		System.out.print(v+" ");
		
		for(int i=1;i<=n;i++) {
			if(edge[v][i]==1 && !visited[i]) {
				//�� ������ ����Ǿ��ְ�, ���� �湮 ��������
				dfs(i);
			}
		}		
	}
	
	static void bfs(int v) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(v); //v�� ť�� �߰�
		visited[v] = true; //v�� �湮ó��
		
		while(!queue.isEmpty()) { //ť�� �� ������ �ݺ�
			v = queue.poll(); //ť���� �ϳ��� ���Ҹ� �̾�
			System.out.print(v+" "); //���

			for(int i=1;i<=n;i++) {
				if(edge[v][i]==1 && !visited[i]) {
					//�� ������ ����Ǿ��ְ�, ���� �湮 ��������
					queue.offer(i); //ť�� ����
					visited[i]=true; //�湮ó��
				}
			}
		}
		
	}
}

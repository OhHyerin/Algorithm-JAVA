package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2178_�̷�Ž��Ǯ��2 {
	//����
	//�̷�Ž��
	//BFS
	//int[]�� Ǫ�� ��� (Node class X)
	
	static int[][] graph;
	static int n,m;
	static int[][] count;
	
	//�̵��� �� ���� ���� ����(��,��,��,��)
	public static int dx[] = {0, 0, -1, 1};
	public static int dy[] = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		graph = new int[n][m];
		count = new int[n][m];
		
		for(int i=0;i<n;i++) {
			String str = br.readLine();
			for(int j=0;j<m;j++) {
				graph[i][j] = str.charAt(j)-'0';
				//graph[i][j] = Integer.parseInt(str.split("")[j-1]);
			}
		}
		
		bfs(); //BFS�ǽ�
		System.out.println(count[n-1][m-1]);
	}
	
	public static void bfs() {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[]{0,0});
		count[0][0] = 1;
		
		//������ġ�� ������ġ ������ �迭 ����
		//int[0] = x��, int[1] = y��
		int[] current = new int[2];
		int[] next = new int[2];
		
		//queue�� �� �� ���� �ݺ�
		while(!queue.isEmpty()) {
			current = queue.poll();
			
			for(int i=0;i<4;i++) {
				next[0] = current[0] + dx[i];
				next[1] = current[1] + dy[i];
				
				//�̷� Ž�� ������ ��� ��� ����
				if(next[0]<0 || next[0]>=n || next[1]<0 || next[1]>=m) {
					continue;
				}
				
				//���� ��� ����
				if(graph[next[0]][next[1]] == 0) {
					continue;
				}
				
				//�湮�� ���� ���� �� ����
				if(count[next[0]][next[1]] != 0) {
					continue;
				}
				
				//��� ���� �����ϸ�
				//queue�� �߰� �� count 1 ����
				queue.offer(new int[] {next[0],next[1]});
				count[next[0]][next[1]] = count[current[0]][current[1]] + 1;
				
			}
		}
	}

}

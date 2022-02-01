package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ_2178 {
	//����
	//�̷�Ž��
	//BFS
	//node class �����ؼ� Ǫ�� ���
	
	static int[][] graph;
	static int n,m;
	
	//�̵��� �� ���� ���� ����(��,��,��,��)
	public static int dx[] = {-1, 1, 0, 0};
	public static int dy[] = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		graph = new int[n][m];
		
		
		for(int i=0;i<n;i++) {
			//�� �پ� �Է¹��� string �� str
			String str = br.readLine();
			for(int j=0;j<m;j++) {
				//String > char > int
				graph[i][j] = str.charAt(j)-'0';
			}
		}
		
		System.out.println(bfs(0,0));

	}
	
	public static int bfs(int x, int y) {
		//ť ������ ���� queue���̺귯�� ���
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(x,y));
		
		while(!queue.isEmpty()) {
			//ť�� �� �� ���� �ݺ�
			
			Node node = queue.poll(); //queue���� pop
			//x,y ���� ��ġ ������ ����
			x = node.getX();
			y = node.getY();
			
			//���� ��ġ���� 4���� �������� ��ġ Ȯ��
			for(int i=0;i<4;i++) {
				//���� ��ġ ����
				int next_x = x + dx[i]; 
				int next_y = y + dy[i];
				
				//�̷� Ž�� ������ ��� ��� ����
				if(next_x<0 || next_x>=n || next_y<0 || next_y>=m ) {
					continue;
				}
				//���� ��� ����
				if(graph[next_x][next_y] == 0) {
					continue;
				}
				//�ش� ��带 ó�� �湮�ϴ� ��쿡�� �ִ� �Ÿ� ���
				if(graph[next_x][next_y] == 1) {
					graph[next_x][next_y] = graph[x][y] + 1;
					queue.offer(new Node(next_x, next_y));
				}
			}
		}
		//���� ������ �Ʒ������� �ִ� �Ÿ� ��ȯ
		return graph[n-1][m-1];
	}

} 

class Node{
	private int x;
	private int y;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
}

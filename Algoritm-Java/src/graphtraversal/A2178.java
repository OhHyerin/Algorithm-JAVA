package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class A2178 {
//미로탐색 
	//BFS
	//node class 생성해서 푸는 방법
	
	static int[][] graph;
	static int n,m;
	
	//이동할 네 가지 방향 정의(상,하,좌,우)
	public static int dx[] = {-1, 1, 0, 0};
	public static int dy[] = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		graph = new int[n][m];
		
		
		for(int i=0;i<n;i++) {
			//한 줄씩 입력받은 string 값 str
			String str = br.readLine();
			for(int j=0;j<m;j++) {
				//String > char > int
				graph[i][j] = str.charAt(j)-'0';
			}
		}
		
		System.out.println(bfs(0,0));

	}
	
	public static int bfs(int x, int y) {
		//큐 구현을 위해 queue라이브러리 사용
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(x,y));
		
		while(!queue.isEmpty()) {
			//큐가 빌 때 까지 반복
			
			Node node = queue.poll(); //queue에서 pop
			//x,y 현재 위치 값으로 설정
			x = node.getX();
			y = node.getY();
			
			//현재 위치에서 4가지 방향으로 위치 확인
			for(int i=0;i<4;i++) {
				//다음 위치 설정
				int next_x = x + dx[i]; 
				int next_y = y + dy[i];
				
				//미로 탐색 공간을 벗어난 경우 무시
				if(next_x<0 || next_x>=n || next_y<0 || next_y>=m ) {
					continue;
				}
				//벽인 경우 무시
				if(graph[next_x][next_y] == 0) {
					continue;
				}
				//해당 노드를 처음 방문하는 경우에만 최단 거리 기록
				if(graph[next_x][next_y] == 1) {
					graph[next_x][next_y] = graph[x][y] + 1;
					queue.offer(new Node(next_x, next_y));
				}
			}
		}
		//가장 오른쪽 아래까지의 최단 거리 반환
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

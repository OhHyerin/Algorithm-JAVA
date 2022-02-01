package graphtraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2178_미로탐색풀이2 {
	//백준
	//미로탐색
	//BFS
	//int[]로 푸는 방법 (Node class X)
	
	static int[][] graph;
	static int n,m;
	static int[][] count;
	
	//이동할 네 가지 방향 정의(상,하,좌,우)
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
		
		bfs(); //BFS실시
		System.out.println(count[n-1][m-1]);
	}
	
	public static void bfs() {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[]{0,0});
		count[0][0] = 1;
		
		//현재위치와 다음위치 저장할 배열 생성
		//int[0] = x값, int[1] = y값
		int[] current = new int[2];
		int[] next = new int[2];
		
		//queue가 빌 때 까지 반복
		while(!queue.isEmpty()) {
			current = queue.poll();
			
			for(int i=0;i<4;i++) {
				next[0] = current[0] + dx[i];
				next[1] = current[1] + dy[i];
				
				//미로 탐색 공간을 벗어난 경우 무시
				if(next[0]<0 || next[0]>=n || next[1]<0 || next[1]>=m) {
					continue;
				}
				
				//벽인 경우 무시
				if(graph[next[0]][next[1]] == 0) {
					continue;
				}
				
				//방문한 적이 있을 때 무시
				if(count[next[0]][next[1]] != 0) {
					continue;
				}
				
				//모든 조건 만족하면
				//queue에 추가 및 count 1 증가
				queue.offer(new int[] {next[0],next[1]});
				count[next[0]][next[1]] = count[current[0]][current[1]] + 1;
				
			}
		}
	}

}

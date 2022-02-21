package graphtraversal;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_S2_1260_DFS와BFS {
	//백준
	//DFS와 BFS
	
	static int n, m;
	static int[][] edge;
	static boolean[] visited;
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		n = in.nextInt(); //정점의 개수
		m = in.nextInt(); //간선의 개수
		int v = in.nextInt(); //탐색을 시작할 정점의 번호
		
		edge = new int[n+1][n+1]; //연결된 간선 2차원배열
		visited = new boolean[n+1];
		Arrays.fill(visited, false);
		
		for(int i=0;i<m;i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			
			edge[x][y] = 1;
			edge[y][x] = 1; //쌍방향 연결상태 저장
		}
		
		dfs(v); //v부터 깊이우선탐색
		System.out.println();
		
		Arrays.fill(visited, false); //방문배열 초기화
		bfs(v); //v부터 너비우선탐색
	}
	
	//재귀형식으로 구현한 DFS
	static void dfs(int v) {
		visited[v] = true; //v를 방문처리
		System.out.print(v+" ");
		
		for(int i=1;i<=n;i++) {
			if(edge[v][i]==1 && !visited[i]) {
				//두 정점이 연결되어있고, 아직 방문 안했으면
				dfs(i);
			}
		}		
	}
	
	static void bfs(int v) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(v); //v를 큐에 추가
		visited[v] = true; //v를 방문처리
		
		while(!queue.isEmpty()) { //큐가 빌 때까지 반복
			v = queue.poll(); //큐에서 하나의 원소를 뽑아
			System.out.print(v+" "); //출력

			for(int i=1;i<=n;i++) {
				if(edge[v][i]==1 && !visited[i]) {
					//두 정점이 연결되어있고, 아직 방문 안했으면
					queue.offer(i); //큐에 삽입
					visited[i]=true; //방문처리
				}
			}
		}
		
	}
}

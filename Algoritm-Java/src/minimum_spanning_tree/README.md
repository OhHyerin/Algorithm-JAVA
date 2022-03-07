##최소신장트리 (MST)
- 그래프에서 최소 비용 문제
  - 모든 정점을 연결하는 간선들의 가중치의 합이 최소가 되는 트리
  - 두 정점 사이의 최소 비용의 경로 찾기
- 신장 트리
  - n개의 정점으로 이루어진 무향그래프에서 n개의 정점과 n-1개의 간선으로 이루어진 트리
- 최소신장트리
  - 무향가중치 그래프에서 신장 트리를 구성하는 간선들의 가중치의 합이 최소인 신장트리


  *간선이 적으면 : 크루스칼*

  *간선이 많으면 : 프림*

-------------

###크루스칼 (Kruskal)
- 간선을 하나씩 선택해서 MST를 찾는 알고리즘
  1. 최초, 모든 간선을 가중치에 따라 오름차순으로 정렬
  2. 가중치가 가장 낮은 간선부터 선택하면서 트리를 증가시킴
     1. 사이클이 존재하면 다음으로 가중치가 낮은 간선 선택
  3. n-1개의 간선이 선택될때까지 2를 반복
```aidl
// G.V : 그래프의 정점 집합
// G.E : 그래프의 간선 집합
MST-KRUSKAL (G, w)
    FOR vertex v in G.V
        Make-Set(v)
        
    G.E에 포함된 간선들을 가중치 w를 이용한 오름차순 정렬
    
    FOR 가중치가 가장 낮은 간선(u, v) ∈ G.E 선택 (n-1개)
        IF Find-Set(u) ≠ Find-Set(v)
            Union(u, v)
```

###프림 (Prim)
- 하나의 정점에서 연결된 간선들 중에 하나씩 선택하면서 MST를 만들어가는 방식
  1. 임의 정점을 하나 선택해서 시작
  2. 선택한 정점과 인접하는 정점들 중의 최소 비용의 간선이 존재하는 정점을 선택
  3. 모든 정점이 선택될 때 까지 1, 2 과정을 반복
- 정점 중심으로 해결하므로, 인접행렬이나 인접리스트를 만들어 사용한다.
- 서로소인 2개의 집합(2 disjoint-sets) 정보를 유지
  - 트리 정점들(tree vertices) : MST를 만들기 위해 선택된 정점들
  - 비트리 정점들(non-tree vertices) : 선택되지 않은 정점들
```aidl
MST-PRIM(G, r)  //G : 그래프, r : 시작정점, minEdge[] : 각 정점기준으로 타 정점과의 최소 간선 비용
    result <- 0, cnt <- 0  //result : MST비용, cnt : 처리한 정점수, visited[] : MST에 포함된 정점 여부
    FOR u in G.V
        minEdge[u] <- ∞
    minEdge[r] <- 0  //시작정점 r의 최소비용 0 처리
    WHILE true
        u <- Extract-MIN()  //방문하지 않은(MST에 포함되지 않은 정점) 최소비용 정점 찾기
        visited[u] <- true  //방문처리
        result <- result + minEdge[u]  //비용누적
        if(++cnt == N) break;  //모든 정점이 다 연결되었으면 MST완성
        FOR v in G.Adj[u]  //u의 인접 정점들
            IF visited[v] == false AND w(u, v) < minEdge[v] // u->v 비용이 v의 최소비용보다 작다면 갱신
                minEdge[v] <- w(u, v)
    return result
end MST-PRIM
```
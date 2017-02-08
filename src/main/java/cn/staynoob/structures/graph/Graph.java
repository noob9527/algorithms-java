package cn.staynoob.structures.graph;

import java.util.LinkedList;

/**
 * Created by xy on 16-6-1.
 */
public class Graph {
    private final int vertices;
    private int edges=0;
    private LinkedList<Integer>[] adj;

    public Graph(int vertices) {
        this.vertices = vertices;
        adj=(LinkedList<Integer>[]) new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i]=new LinkedList<>();
        }
    }

    public void addEdge(int v,int w){
        adj[v].add(w);
        adj[w].add(v);
        edges++;
    }

    /**
     * 返回与指定顶点相邻的顶点集合
     * @param vertex
     * @return
     */
    public Iterable<Integer> adj(int vertex){
        return adj[vertex];
    }

    public int getVertices() {
        return vertices;
    }

    public int getEdges() {
        return edges;
    }
}

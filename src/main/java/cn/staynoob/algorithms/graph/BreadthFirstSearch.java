package cn.staynoob.algorithms.graph;

import cn.staynoob.structures.graph.Graph;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by xy on 16-6-1.
 */
public class BreadthFirstSearch extends AbstractSearcher {
    private boolean[] marked;
    private int[] parent;
    private int count=0;

    public BreadthFirstSearch(Graph graph, int startVertex) {
        super(graph, startVertex);
        marked=new boolean[graph.getVertices()];
        parent=new int[graph.getVertices()];
        bfs();
    }

    private void bfs(){
        Queue<Integer> queue=new LinkedBlockingQueue<>();
        queue.add(startVertex);
        while (!queue.isEmpty()){
            int v=queue.poll();
            for (int i : graph.adj(v)) {
                if (!marked[i]) {
                    count++;
                    marked[i]=true;
                    parent[i]=v;
                    queue.add(i);
                }
            }
        }
    }

    @Override
    public boolean connected(int v) {
        return marked[v];
    }

    @Override
    public Iterable<Integer> pathTo(int v) {
        if(!connected(v)) return null;
        Stack<Integer> stack=new Stack<>();
        while (v!=startVertex){
            stack.push(v);
            v=parent[v];
        }
        stack.push(startVertex);
        return stack;
    }

    @Override
    public int count() {
        return count;
    }
}

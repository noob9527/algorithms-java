package cn.staynoob.algorithms.graph;

import cn.staynoob.structures.graph.Graph;

import java.util.Stack;

/**
 * Created by xy on 16-6-1.
 */
public class DepthFirstSearch extends AbstractSearcher {

    private boolean[] marked;
    private int[] parent;
    private int count=0;

    public DepthFirstSearch(Graph graph, int startVertex) {
        super(graph, startVertex);
        marked=new boolean[graph.getVertices()];
        parent=new int[graph.getVertices()];
        //dfs(startVertex);
        dfs();
    }

    /**
     * 递归
     * @param v
     */
    private void dfs(int v){
        marked[v]=true;
        count++;

        for (int i : graph.adj(v)) {
            if (!marked[i]) {
                parent[i]=v;
                dfs(i);
            }
        }
    }

    /**
     * 循环
     */
    private void dfs() {
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);
        while (!stack.isEmpty()) {
            int v = stack.pop();
            marked[v]=true;
            count++;
            for (int i : graph.adj(v)) {
                if (!marked[i]) {
                    stack.push(i);
                    parent[i]=v;
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

package cn.staynoob.algorithms.graph;

import cn.staynoob.structures.graph.Graph;

/**
 * Created by xy on 16-6-1.
 */
public abstract class AbstractSearcher {
    protected Graph graph;
    protected int startVertex;

    public AbstractSearcher(Graph graph, int startVertex) {
        this.graph = graph;
        this.startVertex = startVertex;
    }

    /**
     * startVertex与v是否连通
     * @param v
     * @return
     */
    public abstract boolean connected(int v);

    /**
     * startVertex到v的路径
     * @param v
     * @return
     */
    public abstract Iterable<Integer> pathTo(int v);

    /**
     * 与startVertex连通的顶点总数
     * @return
     */
    public abstract int count();

}

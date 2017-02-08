package cn.staynoob.algorithms.unionfind;

/**
 * Created by xy on 16-4-25.
 */
public interface UnionFind {
    UnionFind union(int p,int q);
    int find(int p);
    boolean isConnected(int p,int q);
    int count();
}

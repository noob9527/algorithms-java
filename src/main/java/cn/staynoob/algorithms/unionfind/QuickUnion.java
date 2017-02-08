package cn.staynoob.algorithms.unionfind;

/**
 * Created by xy on 16-4-26.
 */
public class QuickUnion implements UnionFind{
    private int[] id;
    private int count;

    public QuickUnion(int N) {
        count=N;
        id=new int[N];
        for (int i = 0; i < id.length; i++)
            id[i]=i;
    }

    @Override
    public UnionFind union(int p, int q) {
        int pRoot=find(p);
        int qRoot=find(q);
        if (pRoot==qRoot) return this;
        id[pRoot]=id[qRoot];
        count--;
        return this;
    }

    @Override
    public int find(int p) {
        while (id[p]!=p) p=id[p];
        return p;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p)==find(q);
    }

    @Override
    public int count() {
        return count;
    }
}

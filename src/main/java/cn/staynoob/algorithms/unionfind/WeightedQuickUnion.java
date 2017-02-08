package cn.staynoob.algorithms.unionfind;

/**
 * 依照algs4,这是该问题的最优解(O(NlogN))
 * Created by xy on 16-4-26.
 */
public class WeightedQuickUnion implements UnionFind{
    private int[] id;
    private int[] size;
    private int count;

    public WeightedQuickUnion(int N) {
        count=N;
        id=new int[N];
        size=new int[N];
        for (int i = 0; i < id.length; i++) {
            id[i]=i;
            size[i]=1;
        }
    }

    @Override
    public UnionFind union(int p, int q) {
        int pRoot=find(p);
        int qRoot=find(q);
        if (pRoot==qRoot) return this;
        if(size[pRoot]<size[qRoot]) {
            id[pRoot] = id[qRoot];
            size[qRoot]+=size[pRoot];
        } else{
            id[qRoot]=id[pRoot];
            size[pRoot]+=size[qRoot];
        }
        count--;
        return this;
    }

    @Override
    public int find(int p) {
        while(p!=id[p]){
            id[p]=id[id[p]];    //路径压缩
            p=id[p];
        }
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

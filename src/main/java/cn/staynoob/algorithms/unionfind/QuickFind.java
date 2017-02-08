package cn.staynoob.algorithms.unionfind;

/**
 * Created by xy on 16-4-25.
 */
public class QuickFind implements UnionFind{
    private int[] id;
    private int count;

    public QuickFind(int N) {
        count=N;
        id=new int[N];
        for (int i = 0; i < id.length; i++)
            id[i]=i;
    }

    public UnionFind union(int p, int q) {
        if(id[p]==id[q]) return this;
        for (int i = 0; i < id.length; i++) {
            if(i==q) continue;
            if(id[i]==id[q]) id[i]=id[p];
        }
        id[q]=id[p];
        count--;
        return this;
    }

    public int find(int p) {
        return id[p];
    }

    public boolean isConnected(int p, int q) {
        return find(p)==find(q);
    }

    public int count() {
        return count;
    }

}

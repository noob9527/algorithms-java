package cn.staynoob.algorithms.graph;

import cn.staynoob.structures.graph.Graph;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by xy on 16-6-1.
 */
public class AbstractSearcherTest {
    private AbstractSearcher searcher;
    private Scanner scanner;
    private Graph graph;
    private static final int startVertex=0;
    private static final int destVertex=5;

    @Before
    public void setUp() throws Exception {
        scanner=new Scanner(new File(this.getClass().getResource("/algs4-data/tinyG.txt").getFile()));
        int vertices=scanner.nextInt();
        int edges=scanner.nextInt();

        //build graph
        graph=new Graph(vertices);
        for (int i = 0; i < edges; i++) {
            int v=scanner.nextInt();
            int w=scanner.nextInt();
            graph.addEdge(v,w);
        }
    }

    @Test
    public void DepthFirstSearchTest() throws Exception {
        searcher=new DepthFirstSearch(graph,startVertex);
        test();
    }

    @Test
    public void BreadthFirstSearchTest() throws Exception {
        searcher=new BreadthFirstSearch(graph,startVertex);
        test();
    }

    private void test(){
        for (int i = 0; i < graph.getVertices(); i++) {
            if(searcher.connected(i))
                System.out.print(i+" ");
        }
        System.out.println();
        if(searcher.count()!=graph.getVertices())
            System.out.println("not connected");
        else
            System.out.println("connected");
        System.out.println(searcher.pathTo(destVertex));
    }
}
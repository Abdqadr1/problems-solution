import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

import java.util.ArrayList;
import java.util.Scanner;

enum Color {
    RED, GREEN
}

abstract class Tree {

    private int value;
    private Color color;
    private int depth;

    public Tree(int value, Color color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public int getDepth() {
        return depth;
    }

    public abstract void accept(TreeVis visitor);
}

class TreeNode extends Tree {

    private ArrayList<Tree> children = new ArrayList<>();

    public TreeNode(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitNode(this);

        for (Tree child : children) {
            child.accept(visitor);
        }
    }

    public void addChild(Tree child) {
        children.add(child);
    }
}

class TreeLeaf extends Tree {

    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}

abstract class TreeVis
{
    public abstract int getResult();
    public abstract void visitNode(TreeNode node);
    public abstract void visitLeaf(TreeLeaf leaf);

}

class SumInLeavesVisitor extends TreeVis {
    private int result = 0;
    public int getResult() {
      	//implement this
        return this.result;
    }

    public void visitNode(TreeNode node) {
      	//implement this
          
    }

    public void visitLeaf(TreeLeaf leaf) {
      	//implement this
          this.result+=leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
    private long result = 1;
    public int  getResult() {
      	//implement this
        return ((int)this.result);
    }

    public void visitNode(TreeNode node) {
      	//implement this
          if(node.getColor() == Color.RED){
              this.result = (this.result * node.getValue()) % 1000000007;
          }
    }

    public void visitLeaf(TreeLeaf leaf) {
      	//implement this
          if(leaf.getColor() == Color.RED){
              this.result = (this.result * leaf.getValue()) % 1000000007;
          }
    }
}
class FancyVisitor extends TreeVis {
    private int sumNonLeaf = 0, sumGreen = 0;
    public int getResult() {
      	//implement this
        return Math.abs(this.sumNonLeaf - this.sumGreen);
    }

    public void visitNode(TreeNode node) {
    	//implement this
        if((node.getDepth() % 2) == 0){
            this.sumNonLeaf += node.getValue();
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
    	//implement this
        if(leaf.getColor() == Color.GREEN){
            this.sumGreen += leaf.getValue();
        }
    }
}

public class VistiorPattern {
    static TreeNode root;
    public static Set<Integer> checkedNode = new HashSet<>();
   public static void addNode(int node, Map<Integer, ArrayList<Object>> valColors, Map<Integer, ArrayList<Integer>> edges, TreeNode parent){
        checkedNode.add(node);
        ArrayList<Integer> children = edges.get(node);
        if(children.size() == 1 && node != 1){
            int val = ((Integer) valColors.get(node).get(0)).intValue();
            Color color = (Color) valColors.get(node).get(1);
            parent.addChild(new TreeLeaf(val,color,parent.getDepth()+1));
//             System.out.println(node + " "+val + " "+color +" "+parent.getDepth()+1);
            return;
        } 
        if(node != 1){
            int value1 = ((Integer) valColors.get(node).get(0)).intValue();
            Color color1 = (Color) valColors.get(node).get(1);
            TreeNode treeNode = new TreeNode(value1, color1, parent.getDepth()+1);
            parent.addChild(treeNode); 
//            System.out.println(node + " "+value1 + " "+color1 +" "+parent.getDepth()+1);
            for(Integer child : children){
                if(!checkedNode.contains(child)) addNode(child,valColors,edges,treeNode);
            }
        } else {
            for(Integer child : children){
                if(!checkedNode.contains(child)) addNode(child,valColors,edges,parent);
            }
        }
        
    }
  
    public static Tree solve() {
        //read the tree from STDIN and return its root as a return value of this function
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Map<Integer, ArrayList<Integer>> edges = new HashMap<>();
        Map<Integer, ArrayList<Object>> valColors = new HashMap<>();
        // initialize values & colors, and edges
        for(int u=1;u<=n;u++) {
            valColors.put(u, new ArrayList<>());
            edges.put(u, new ArrayList<Integer>());
        }
        // System.out.println(edges.toString());
        for(int x=1;x<=n;x++) {
            int v = sc.nextInt();
            Integer val = Integer.valueOf(v);
            valColors.get(x).add(val);
        }
        for(int x=1;x<=n;x++) {
            int c = sc.nextInt();
            Color col = Integer.valueOf(c) == 1?Color.GREEN:Color.RED;
            valColors.get(x).add(col);
        }
        for(int i=1;i<n;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            edges.get(u).add(v);
            edges.get(v).add(u);
        }
        int rootVal = ((Integer)valColors.get(1).get(0)).intValue();
        Color rootColor = (Color) valColors.get(1).get(1);
        root = new TreeNode(rootVal,rootColor,0);
        addNode(1,valColors,edges,root);
        sc.close();
        return root;
    }


    public static void main(String[] args) {
      	Tree root = solve();
		SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
      	ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
      	FancyVisitor vis3 = new FancyVisitor();

      	root.accept(vis1);
      	root.accept(vis2);
      	root.accept(vis3);

      	int res1 = vis1.getResult();
      	int res2 = vis2.getResult();
      	int res3 = vis3.getResult();

      	System.out.println(res1);
     	System.out.println(res2);
    	System.out.println(res3);
	}
}